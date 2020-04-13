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

class LinkedList<T> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var size = 0

    private fun isEmpty(): Boolean {
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

}