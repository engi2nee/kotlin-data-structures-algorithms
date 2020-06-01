package tree

import com.sun.source.tree.BinaryTree
import queues.ArrayListQueue
import kotlin.math.max

fun <T> TreeNode<T>.printEachLevel() {
    val queue = ArrayListQueue<TreeNode<T>>()
    var nodesLeftInCurrentLevel = 0

    queue.enqueue(this)
    while (queue.isEmpty.not()) {
        nodesLeftInCurrentLevel = queue.count
        while (nodesLeftInCurrentLevel > 0) {
            val node = queue.dequeue()
            node?.let {
                print("${node.value} ")
                node.children.forEach { queue.enqueue(it) }
                nodesLeftInCurrentLevel--
            } ?: break
        }
        println()
    }
}

fun <T> BinaryNode<T>.height(node: BinaryNode<T>? = this): Int {
    return node?.let {
        1 + max(
            height(node.leftChild),
            height(node.rightChild)
        )
    } ?: -1
}

fun <T> BinaryNode<T>.serialize(node: BinaryNode<T> = this): MutableList<T?> {
    val list = mutableListOf<T?>()
    node.traversePreOrderWithNull { list.add(it) }
    //reversing because if not, we will be removing list.removeAt(0) in deserialize function,
    //which means shift all items, after each recursion so O(n^2)
    //reversing then removing from end means o(n) for this operation
    return list.asReversed()
}

fun <T> BinaryNode<T>.deserialize(list: MutableList<T?>): BinaryNode<T?>? {
    val rootValue = list.removeAt(list.size - 1) ?: return null
    val root = BinaryNode<T?>(rootValue)
    root.leftChild = deserialize(list)
    root.rightChild = deserialize(list)
    return root
}

fun <T : Comparable<T>> BinaryNode<T>.isBinarySearchTree(): Boolean{
    return isBST(this, min = null, max = null)
}

fun <T> BinaryNode<T>.isBST(tree: BinaryNode<T>?, min: T?, max: T?): Boolean
        where T : Comparable<T>{
    println("Tree Node is : ${tree?.value} and min= $min, max=$max")
    tree ?: return true
    if (min != null && tree.value <= min) {
        return false
    } else if (max != null && tree.value > max) {
        return false
    }
    return isBST(tree.leftChild, min, tree.value) && isBST(tree.rightChild, tree.value, max)
}