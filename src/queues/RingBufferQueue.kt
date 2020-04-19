package queues

class RingBufferQueue<E>(private val capacity: Int) : Queue<E> {

    private var front = -1
    private var rear = -1
    private val elements: Array<Any?> = arrayOfNulls(capacity)

    override val count: Int
        get() = capacity

    override fun enqueue(element: E) {
        if (isFull())  throw QueueOverflowException()
        rear = (rear + 1) % capacity
        elements[rear] = element
        if (front == -1 ) front = rear
    }

    override fun dequeue(): E {
        if (count == 0) throw QueueUnderflowException()
        val oldVal = elements[front]
        elements[front] = null
        if (front == rear) {
            front = -1
            rear = -1
        } else front = (front + 1) % capacity
        return oldVal as E
    }

    override fun peek() = try {
        elements[front] as E
    } catch (e: IndexOutOfBoundsException) {
        throw QueueUnderflowException();
    }

    fun rear() = try {
        elements[rear] as E
    } catch (e: IndexOutOfBoundsException) {
        throw QueueUnderflowException();
    }

    private fun isFull() = (rear + 1) % capacity == front

    override fun toString(): String {
        return elements.contentToString()
    }
}

class QueueUnderflowException : RuntimeException()

class QueueOverflowException : RuntimeException()
