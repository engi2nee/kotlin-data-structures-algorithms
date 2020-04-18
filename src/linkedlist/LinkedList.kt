package linkedlist
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