package queues

import StackQueue
import example

fun main() {
    "Queue with ArrayList" example {
        val queue = ArrayListQueue<String>().apply {
            enqueue("Ray")
            enqueue("Brian")
            enqueue("Eric")
        }
        println(queue)
        queue.dequeue()
        println(queue)
        println("Next up: ${queue.peek()}")
    }

    "Queue with Doubly Linked List" example {
        val queue = LinkedListQueue<String>().apply {
            enqueue("Ray")
            enqueue("Brian")
            enqueue("Eric")
        }
        println(queue)
        queue.dequeue()
        println(queue)
        println("Next up: ${queue.peek()}")
    }

    "Queue with Ring Buffer" example {
        val queue = RingBufferQueue<String>(10).apply {
            enqueue("Ray")
            enqueue("Brian")
            enqueue("Eric")
        }
        println(queue)
        queue.dequeue()
        println(queue)
        println("Next up: ${queue.peek()}")

    }

    "Queue with Double Stack " example {
        val queue = StackQueue<String>().apply {
            enqueue("Ray")
            enqueue("Brian")
            enqueue("Eric")
        }
        println("Initial")
        println(queue)
        println("Dequeue")
        queue.dequeue()
        println(queue)
        println("Enqueue")
        queue.enqueue("Bob")
        println(queue)
        println("Dequeue")
        queue.dequeue()
        println(queue)
        println("Next up: ${queue.peek()}")
    }

    "Boardgame manager with Queue" example {
        val queue = ArrayListQueue<String>().apply {
            enqueue("Vincent")
            enqueue("Remel")
            enqueue("Lukiih")
            enqueue("Allison")
        }
        println(queue)

        println("===== boardgame =======")
        queue.nextPlayer()
        println(queue)
        queue.nextPlayer()
        println(queue)
        queue.nextPlayer()
        println(queue)
        queue.nextPlayer()
        println(queue)
    }

}