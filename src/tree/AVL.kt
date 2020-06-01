package tree

import kotlin.math.max

class AVL<T : Comparable<T>> {

    var root: AVLBinaryNode<T>? = null

    override fun toString() = root?.toString() ?: "empty tree"

    fun insert(value: T) {
        root = insert(root, value)
    }

    private fun insert(node: AVLBinaryNode<T>?, value: T): AVLBinaryNode<T>? {
        node ?: return AVLBinaryNode(value)
        if (value < node.value) {
            node.leftChild = insert(node.leftChild, value)
        } else {
            node.rightChild = insert(node.rightChild, value)
        }
        val balancedNode = balanced(node)
        balancedNode.height = max(balancedNode.leftHeight ?: 0, balancedNode.rightHeight ?: 0) + 1
        return balancedNode
    }

    fun contains(value: T): Boolean {
        var current = root
        while (current != null) {
            if (current.value == value) {
                return true
            }
            current = if (value < current.value) {
                current.leftChild
            } else {
                current.rightChild
            }
        }

        return false

    }


    fun remove(value: T) {
        root = remove(root, value)
    }

    private fun remove(
        node: AVLBinaryNode<T>?,
        value: T
    ): AVLBinaryNode<T>? {
        node ?: return null

        when {
            value == node.value -> {
                if (node.leftChild == null && node.rightChild == null) {
                    return null
                }
                if (node.leftChild == null) {
                    return node.rightChild
                }
                if (node.rightChild == null) {
                    return node.leftChild
                }
                node.rightChild?.min?.value?.let {
                    node.value = it
                }

                node.rightChild = remove(node.rightChild, node.value)

            }
            value < node.value -> node.leftChild = remove(node.leftChild, value)
            else -> node.rightChild = remove(node.rightChild, value)
        }
        val balancedNode = balanced(node)

        balancedNode.height = max(
            balancedNode.leftHeight,
            balancedNode.rightHeight
        ) + 1

        return balancedNode

    }

    fun contains(subtree: AVL<T>): Boolean {
        val set = mutableSetOf<T>()
        root?.traverseInOrder {
            set.add(it)
        }
        var isEqual = true
        subtree.root?.traverseInOrder {
            isEqual = isEqual && set.contains(it)
        }
        return isEqual
    }

    private fun leftRotate(node:AVLBinaryNode<T>) :AVLBinaryNode<T>{
        var pivot = node.rightChild!!
        node.rightChild = pivot.leftChild
        pivot.leftChild = node
        node.height = max(node.leftHeight,node.rightHeight) +1
        pivot.height = max(pivot.leftHeight,pivot.rightHeight) +1
        return pivot
    }

    private fun rightRotate(node: AVLBinaryNode<T>): AVLBinaryNode<T> {
        val pivot = node.leftChild!!
        node.leftChild = pivot.rightChild
        pivot.rightChild = node
        node.height = max(node.leftHeight, node.rightHeight) + 1
        pivot.height = max(pivot.leftHeight, pivot.rightHeight) + 1
        return pivot
    }

    private fun rightLeftRotate(node: AVLBinaryNode<T>): AVLBinaryNode<T> {
        val rightChild = node.rightChild ?: return node
        node.rightChild = rightRotate(rightChild)
        return leftRotate(node)
    }


    private fun leftRightRotate(node: AVLBinaryNode<T>): AVLBinaryNode<T> {
        val leftChild = node.leftChild ?: return node
        node.leftChild = rightRotate(leftChild)
        return rightRotate(node)
    }

    private fun balanced(node: AVLBinaryNode<T>): AVLBinaryNode<T> {
        return when (node.balanceFactor) {
            2 -> {
                if (node.leftChild?.balanceFactor == -1) {
                    leftRightRotate(node)
                } else {
                    rightRotate(node)
                }
            }
            -2 -> {
                if (node.rightChild?.balanceFactor == 1) {
                    rightLeftRotate(node)
                } else {
                    leftRotate(node)
                }
            }
            else -> node
        }
    }

}