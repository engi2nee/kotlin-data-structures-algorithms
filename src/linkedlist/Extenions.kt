package linkedlist

import stack.StackImpl

//Both print in reverse is O(N) for time complexity,
//but this method has O(N) for space complexity also.
fun <T> LinkedList<T>.printInReverse() {
    val stack = StackImpl<T>()

    for (node in this) {
        stack.push(node)
    }

    var node = stack.pop()
    while (node != null) {
        println(node)
        node = stack.pop()
    }
}

fun <T> LinkedList<T>.printInReverseRecursively() {
    this.nodeAt(0)?.printInReverseRecursively()
}

//This will keep calling the printInReverse until it
//reaches the last item, which wwill then excute in reverse order.
fun <T> Node<T>.printInReverseRecursively() {
    this.next?.printInReverseRecursively()
    if (this.next != null) {
        kotlin.io.print(" -> ")
    }
    print(this.value.toString())
}

//This method gets the middle item in a linkedlist, it works by increasing the fast twice and slow once,
//When fast reaches null (end of linked list) slow will have been reached the middle item, this is called the runner technique.
//The time complexity of this algorithm is O(n) since you traversed the list in a single pass.
fun <T> LinkedList<T>.getMiddle(): Node<T>? {
    var slow = this.nodeAt(0)
    var fast = this.nodeAt(0)

    while (fast != null) {
        fast = fast.next
        if (fast != null) {
            fast = fast.next
            slow = slow?.next
        }
    }

    return slow
}

//reverse a linked list
fun <T> LinkedList<T>.reversed(): LinkedList<T> {
    val result = LinkedList<T>()
    val head = this.nodeAt(0)
    if (head != null) {
        addInReverse(result, head)
    }
    return result
}

private fun <T> addInReverse(list: LinkedList<T>, node: Node<T>) {
    val next = node.next
    if (next != null) {
        addInReverse(list, next)
    }
    list.append(node.value)
}

//To merge 2 sorted linkedlists
fun <T : Comparable<T>> LinkedList<T>.mergeSorted(
    otherList: LinkedList<T>
): LinkedList<T> {
    if (this.isEmpty()) return otherList
    if (otherList.isEmpty()) return this

    val result = LinkedList<T>()

    var left = nodeAt(0)
    var right = otherList.nodeAt(0)
    //keep comparing an item till you find an item bigger than it, add the small then increase .
    while (left != null && right != null) {
        kotlin.io.println("comparing ${left.value} in left with ${right.value} in right")
        if (left.value < right.value) {
            left = append(result, left)
        } else {
            right = append(result, right)
        }
    }

    //the while loop abve depends on both left and right,
    // if one of them is smaller in size than the other, some items might be left out
    while (left != null) {
        left = append(result, left)
    }

    while (right != null) {
        right = append(result, right)
    }

    return result
}

private fun <T : Comparable<T>> append(
    result: LinkedList<T>,
    node: Node<T>
): Node<T>? {
    result.append(node.value)
    return node.next
}