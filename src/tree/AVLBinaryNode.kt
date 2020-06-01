package tree

class AVLBinaryNode<T>(var value: T) {

    var leftChild: AVLBinaryNode<T>? = null
    var rightChild: AVLBinaryNode<T>? = null
    val min: AVLBinaryNode<T>?
        get() = leftChild?.min ?: this

    override fun toString() = diagram(this)

    //https://www.objc.io/books/optimizing-collections/
    private fun diagram(
        node: AVLBinaryNode<T>?,
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

    override fun equals(other: Any?): Boolean {
        return if (other != null && other is AVLBinaryNode<*>) {
            this.value == other.value &&
                    this.leftChild == other.leftChild &&
                    this.rightChild == other.rightChild
        } else {
            false
        }
    }

}