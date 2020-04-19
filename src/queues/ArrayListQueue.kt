package queues

class ArrayListQueue<T> : Queue<T> {

    override val count: Int
        get() = list.size

    override fun peek(): T? = list.getOrNull(0)

    private val list = arrayListOf<T>()

    override fun enqueue(element: T) {
        list.add(element)
    }

    override fun dequeue(): T? =
        if (isEmpty) null else list.removeAt(0)

    override fun toString(): String = list.toString()
}