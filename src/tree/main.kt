package tree

import example

fun main() {
    val hot = TreeNode("Hot")
    val cold = TreeNode("Cold")

    val tree = makeBeverageTree()
    tree.forEachDepthFirst ({ println(it.value)})



}
