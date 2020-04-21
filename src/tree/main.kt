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
}