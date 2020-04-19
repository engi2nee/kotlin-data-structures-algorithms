package queues

import java.util.*

class LinkedListQueue<T> : Queue<T> {

    private val list = LinkedList<T>()

    private var size = 0

    override val count: Int
        get() = size

    override fun enqueue(element: T) {
        list.add(element)
        size++
    }

    override fun dequeue(): T? {
        size--
        return list.removeFirst()
    }

    override fun peek(): T? {
        return if (size > 0)
            list.first
        else null
    }

    override fun toString(): String = list.toString()
}
