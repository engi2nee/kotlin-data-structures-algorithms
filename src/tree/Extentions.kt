package tree

import apple.laf.JRSUIUtils
import queues.ArrayListQueue

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