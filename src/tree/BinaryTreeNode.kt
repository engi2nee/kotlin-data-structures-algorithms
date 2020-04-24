package tree

typealias BinaryVisitor<T> = (T) -> Unit

class BinaryNode<T>(var value: T) {

    var leftChild: BinaryNode<T>? = null
    var rightChild: BinaryNode<T>? = null
    val min: BinaryNode<T>?
        get() = leftChild?.min ?: this

    override fun toString() = diagram(this)

    //https://www.objc.io/books/optimizing-collections/
    private fun diagram(
        node: BinaryNode<T>?,
        top: String = "",
        root: String = "",
        bottom: String = ""
    ): String {
        return node?.let {
            if (node.leftChild == null && node.rightChild == null) {
                "$root${node.value}\n"
            } else {
                diagram(node.rightChild, "$top ", "$top┌──", "$top│ ") +
                        root + "${node.value}\n" + diagram(node.leftChild, "$bottom│ ", "$bottom└──", "$bottom ")
            }
        } ?: "${root}null\n"
    }

    fun traverseInOrder(visit: BinaryVisitor<T>) {
        //If the current node has a left child, recursively visit this child first.
        leftChild?.traverseInOrder(visit)
        //Then visit the node itself.
        visit(value)
        //If the current node has a right child, recursively visit this child.
        rightChild?.traverseInOrder(visit)
    }

    fun traversePreOrder(visit: BinaryVisitor<T>) {
        //Visits the current node first.
        visit(value)
        //Recursively visits the left and right child\
        leftChild?.traversePreOrder(visit)
        rightChild?.traversePreOrder(visit)
    }

    //given any node, visit its children before visiting itself.
    //Note: root node is always visited last.
    fun traversePostOrder(visit: BinaryVisitor<T>) {
        leftChild?.traversePostOrder(visit)
        rightChild?.traversePostOrder(visit)
        visit(value)
    }

    // Same as pre order function above, just returns the null children as well
    // Used in serialization/deserialization.
    fun traversePreOrderWithNull(visit: BinaryVisitor<T?>) {
        visit(value)
        leftChild?.traversePreOrderWithNull(visit) ?: visit(null)
        rightChild?.traversePreOrderWithNull(visit) ?: visit(null)
    }

}

fun makeBinaryTree():BinaryNode<Int> {
    val zero = BinaryNode(0)
    val one = BinaryNode(1)
    val five = BinaryNode(5)
    val seven = BinaryNode(7)
    val eight = BinaryNode(8)
    val nine = BinaryNode(9)
    val six = BinaryNode(6)
    val three = BinaryNode(3)
    val two = BinaryNode(2)

    seven.leftChild = one
    one.leftChild = zero
    one.rightChild = five
    seven.rightChild = nine
    nine.leftChild = eight
    eight.leftChild = six
    eight.rightChild = three
    three.rightChild = two
    return seven
}
