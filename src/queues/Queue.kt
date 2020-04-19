package queues

interface Queue<T> {

    fun enqueue(element: T)

    fun dequeue(): T?

    val count: Int
        get

    val isEmpty: Boolean
        get() = count == 0

    fun peek(): T?
}