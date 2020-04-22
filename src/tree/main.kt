package tree

import example

fun main() {
    val tree = makeBeverageTree()
    "Tree Depth First traversal" example {
        tree.forEachDepthFirst { println(it.value) }
    }

    "Tree Level-order traversal" example {
        tree.forEachLevelOrder { println(it.value) }
    }

    "Tree search" example {
        tree.search("ginger ale")?.let {
            println("Found node: ${it.value}")
        }

        tree.search("WKD Blue")?.let {
            println(it.value)
        } ?: println("Couldn't find WKD Blue")
    }

    "Tree Level-order print each level per line" example {
        tree.printEachLevel()
    }


    val binaryTree = makeBinaryTree()

    "Binary printing algorithm" example {
        println(binaryTree)
    }

    "Binary Tree in-order traversal" example {
        binaryTree.traverseInOrder { println(it) }
    }

    "Binary Tree pre-order traversal" example {
        binaryTree.traversePreOrder { println(it) }
    }

    "Binary Tree post-order traversal" example {
        binaryTree.traversePostOrder { println(it) }
    }

    "Binary Tree height calculation" example {
        println(binaryTree.height())
    }

    "Binary Tree serialization and deserialization" example {
        println(binaryTree)
        val array = binaryTree.serialize()
        println("serialized => $array")
        print("deserialized =>")
        val newTree = binaryTree.deserialize(array)
        println(newTree)
    }


}