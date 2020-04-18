package linkedlist

import example

fun main() {

    "push" example {
        val list = LinkedList<Int>()
        list.push(3).push(2).push(1)
        println(list)
    }

    "append" example {
        val list = LinkedList<Int>()
        list.append(1).append(2).append(3)
        println(list)
    }

    "inserting at a particular index" example {
        val list = LinkedList<Int>()
        list.push(2).push(1).append(3)

        println("Before inserting: $list")
        var middleNode = list.nodeAt(1)!!
        for (i in 1..3) {
            middleNode = list.insert(-1 * i, middleNode)
        }
        println("After inserting: $list")
    }

    "pop" example {
        val list = LinkedList<Int>()
        list.push(2).push(1).append(3)

        println("Before popping list: $list")
        val poppedValue = list.pop()
        println("After popping list: $list")
        println("Popped value: $poppedValue")
    }

    "removing the last node" example {
        val list = LinkedList<Int>()
        list.push(2).push(1).append(3)

        println("Before removing last node: $list")
        val removedValue = list.removeLast()

        println("After removing last node: $list")
        println("Removed value: $removedValue")
    }


    "removing a node after a particular node" example {
        val list = LinkedList<Int>()
        list.push(2).push(1).append(3)

        println("Before removing at particular index: $list")
        val index = 1
        val node = list.nodeAt(1)!!
        val removedValue = list.removeAfter(node)

        println("After removing at index $index: $list")
        println("Removed value: $removedValue")
    }

    "printing doubles" example {
        val list = LinkedList<Int>()
        list.push(2).push(1).append(3)
        println(list)
        for (item in list) {
            println("Double: ${item * 2}")
        }
    }

    "removing elements" example {
        val list: MutableCollection<Int> = LinkedList()
        list.add(3)
        list.add(2)
        list.add(1)

        println(list)
        list.remove(1)
        println(list)
    }

    "retaining elements" example {
        val list: MutableCollection<Int> = LinkedList()
        list.add(3)
        list.add(2)
        list.add(1)
        list.add(4)
        list.add(5)

        println(list)
        list.retainAll(listOf(3, 4, 5))
        println(list)
    }

    "remove all elements" example {
        val list: MutableCollection<Int> = LinkedList()
        list.add(3)
        list.add(2)
        list.add(1)
        list.add(4)
        list.add(5)

        println(list)
        list.removeAll(listOf(3, 4, 5))
        println(list)
    }

    "print in reverse recursively" example {
        val list = LinkedList<Int>()
        list.add(3)
        list.add(2)
        list.add(1)
        list.add(4)
        list.add(5)

        println(list)
        list.printInReverseRecursively()
    }

    "print middle" example {
        val list = LinkedList<Int>()
        list.add(3)
        list.add(2)
        list.add(1)
        list.add(4)
        list.add(5)

        println(list)
        println(list.getMiddle()?.value)
    }

    "reverse list" example {
        val list = LinkedList<Int>()
        list.add(3)
        list.add(2)
        list.add(1)
        list.add(4)
        list.add(5)

        println("Original: $list")
        println("Reversed: ${list.reversed()}")
    }


    "merge lists" example {
        val list = LinkedList<Int>()
        list.add(1)
        list.add(2)
        list.add(3)
        list.add(4)
        list.add(5)

        val other = LinkedList<Int>()
        other.add(-1)
        other.add(0)
        other.add(2)
        other.add(2)
        other.add(7)

        println("Left: $list")
        println("Right: $other")
        println("Merged: ${list.mergeSorted(other)}")
    }

    "print in reverse without recursion" example {
        val list = LinkedList<Int>()
        list.add(3)
        list.add(2)
        list.add(1)
        list.add(4)
        list.add(5)

        println(list)
        list.printInReverse()
    }


}