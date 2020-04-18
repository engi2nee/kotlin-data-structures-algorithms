package stack

class StackImpl<T> : Stack<T> {
    private val storage = arrayListOf<T>()

    override fun toString() = buildString {
        appendln("----top----")
        storage.asReversed().forEach {
            appendln("$it")
        }
        appendln("-----------")
    }

    override fun push(element: T) {
        storage.add(element)
    }

    override fun pop(): T? {
        if (isEmpty) {
            return null
        }
        return storage.removeAt(count - 1)
    }

    override fun peek(): T? {
        return storage.lastOrNull()
    }

    override val count: Int
        get() = storage.size



    companion object {
        fun <T> create(items: Iterable<T>): Stack<T> {
            val stack = StackImpl<T>()
            for (item in items) {
                stack.push(item)
            }
            return stack
        }
    }
}


interface Stack<T> {

    fun push(element: T)

    fun pop(): T?

    fun peek(): T?

    val count: Int

    val isEmpty: Boolean
        get() = count == 0

}