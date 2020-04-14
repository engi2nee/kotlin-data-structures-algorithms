fun main() {

    "push" example {
        val list = LinkedList<Int>()
        list.push(3).push(2).push(1)
        println(list)
    }

    "append" example {
        val list = LinkedList<Int>()
        list.append(1).append(2).append(3)
        println(list)
    }

    "inserting at a particular index" example {
        val list = LinkedList<Int>()
        list.push(2).push(1).append(3)

        println("Before inserting: $list")
        var middleNode = list.nodeAt(1)!!
        for (i in 1..3) {
            middleNode = list.insert(-1 * i, middleNode)
        }
        println("After inserting: $list")
    }

    "pop" example {
        val list = LinkedList<Int>()
        list.push(2).push(1).append(3)

        println("Before popping list: $list")
        val poppedValue = list.pop()
        println("After popping list: $list")
        println("Popped value: $poppedValue")
    }

    "removing the last node" example {
        val list = LinkedList<Int>()
        list.push(2).push(1).append(3)

        println("Before removing last node: $list")
        val removedValue = list.removeLast()

        println("After removing last node: $list")
        println("Removed value: $removedValue")
    }


    "removing a node after a particular node" example {
        val list = LinkedList<Int>()
        list.push(2).push(1).append(3)

        println("Before removing at particular index: $list")
        val index = 1
        val node = list.nodeAt(1)!!
        val removedValue = list.removeAfter(node)

        println("After removing at index $index: $list")
        println("Removed value: $removedValue")
    }

    "printing doubles" example {
        val list = LinkedList<Int>()
        list.push(2).push(1).append(3)
        println(list)
        for (item in list) {
            println("Double: ${item * 2}")
        }
    }

    "removing elements" example {
        val list: MutableCollection<Int> = LinkedList()
        list.add(3)
        list.add(2)
        list.add(1)

        println(list)
        list.remove(1)
        println(list)
    }

    "retaining elements" example {
        val list: MutableCollection<Int> = LinkedList()
        list.add(3)
        list.add(2)
        list.add(1)
        list.add(4)
        list.add(5)

        println(list)
        list.retainAll(listOf(3, 4, 5))
        println(list)
    }

    "remove all elements" example {
        val list: MutableCollection<Int> = LinkedList()
        list.add(3)
        list.add(2)
        list.add(1)
        list.add(4)
        list.add(5)

        println(list)
        list.removeAll(listOf(3, 4, 5))
        println(list)
    }

}

data class Node<T>(var value: T, var next: Node<T>? = null) {
    override fun toString(): String {
        return if (next != null) {
            "$value -> ${next.toString()}"
        } else {
            "$value"
        }
    }
}

class LinkedList<T> : MutableIterable<T>, MutableCollection<T> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    override var size = 0

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun toString(): String {
        return if (isEmpty()) {
            "Empty list"
        } else {
            head.toString()
        }
    }

    fun push(value: T): LinkedList<T> {
        head = Node(value = value, next = head)
        if (tail == null) {
            tail = head
        }
        size++
        return this
    }

    fun append(value: T): LinkedList<T> {
        if (isEmpty()) {
            push(value)
            return this
        }
        // (1) -> (2 : Tail) -> (null)
        // (1) -> (2: Tail) -> (3) -> (null)
        tail?.next = Node(value = value)
        // (1) -> (2) -> (3 : Tail) -> (null)
        tail = tail?.next
        size++
        return this
    }

    fun nodeAt(index: Int): Node<T>? {
        var currentNode = head
        var currentIndex = 0
        //check all items till you reach the right index,
        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next
            currentIndex++
        }

        return currentNode
    }

    fun insert(value: T, afterNode: Node<T>): Node<T> {
        //if after node is already last node, just append
        if (tail == afterNode) {
            append(value)
            return tail!!
        }
        //Create a new node with the new value but copy the after node next to it
        val newNode = Node(value = value, next = afterNode.next)
        // now we have both new node and after node pointing to same next, so let after node point to new node instead
        afterNode.next = newNode
        size++
        return newNode
    }

    fun pop(): T? {
        if (!isEmpty()) size--
        val result = head?.value
        head = head?.next
        if (isEmpty()) {
            tail = null
        }

        return result
    }

    fun removeLast(): T? {
        //if head is null, then list is empty, return null
        val head = head ?: return null
        //if there is only 1 item, just use pop()
        if (head.next == null) return pop()

        size--
        var prev = head
        var current = head

        var next = current.next
        //loop on all items till you current.next is null, which means last node
        while (next != null) {
            prev = current
            current = next
            next = current.next
        }
        //Since current is the last node, you disconnect it using the prev.next reference. You also make sure to update the tail reference.
        prev.next = null
        //set last item as prev
        tail = prev
        return current.value
    }

    fun removeAfter(node: Node<T>): T? {
        val result = node.next?.value
        //if you are deleting the last item, update tail
        if (node.next == tail) {
            tail = node
        }

        if (node.next != null) {
            size--
        }
        //set the next node as 2 nodes ahead, if you are deleting last item already, 2 nodes ahead is null
        node.next = node.next?.next
        return result
    }

    override fun iterator(): MutableIterator<T> {
        return LinkedListIterator(this)
    }

    override fun contains(element: T): Boolean {
        //O(n)
        for (item in this) {
            if (item == element) return true
        }
        return false
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        //O(n^2)
        elements.forEach {
            if (this.contains(it)) return true
        }
        return false
    }

    override fun add(element: T): Boolean {
        append(element)
        return true
    }

    override fun addAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            append(element)
        }
        return true
    }

    override fun clear() {
        head = null
        tail = null
        size = 0
    }

    override fun remove(element: T): Boolean {
        val iterator = this.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item == element) {
                iterator.remove()
                return true
            }
        }
        return false
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        var result = false
        for (item in elements) {
            result = remove(item) || result
        }
        return result
    }

    //Keep those items only
    override fun retainAll(elements: Collection<T>): Boolean {
        var result = false
        val iterator = this.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (!elements.contains(item)) {
                iterator.remove()
                result = true
            }
        }
        return result
    }

}

class LinkedListIterator<T>(private val list: LinkedList<T>) : MutableIterator<T> {
    private var index = 0
    private var lastNode: Node<T>? = null

    override fun next(): T {
        if (index >= list.size) throw IndexOutOfBoundsException()
        lastNode = if (index == 0) {
            list.nodeAt(0)
        } else {
            lastNode?.next
        }
        index++
        return lastNode!!.value

    }

    override fun hasNext(): Boolean {
        return index < list.size
    }

    override fun remove() {
        if (index == 1) {
            list.pop()
        } else {
            //we do -2 because iterator next does an index++ before checking for has next again
            val prevNode = list.nodeAt(index - 2) ?: return
            list.removeAfter(prevNode)
            lastNode = prevNode
        }
        index--
    }
}