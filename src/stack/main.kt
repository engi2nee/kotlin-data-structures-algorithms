package stack

import example


fun main() {
    "using a stack" example {
        val stack = StackImpl<Int>().apply {
            push(1)
            push(2)
            push(3)
            push( 4)
        }
        print(stack)
        val poppedElement = stack.pop()
        if (poppedElement != null) {
            println("Popped: $poppedElement")
        }
        print(stack)
    }

    "initializing a stack from a list" example {
        val list = listOf("A", "B", "C", "D")
        val stack = StackImpl.create(list)
        print(stack)
        println("Popped: ${stack.pop()}")
    }

    "initializing a stack from an array literal" example {
        val stack = stackOf(1.0, 2.0, 3.0, 4.0)
        print(stack)
        println("Popped: ${stack.pop()}")
    }

    "check parentheses in a string if balanced" example {
        val isBalanced1 = "h((e))llo(world)()".checkParentheses()
        val isBalanced2 = "hello(()".checkParentheses()
        println(isBalanced1)
        println(isBalanced2)
    }
}