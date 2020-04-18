package stack

fun <T> stackOf(vararg elements: T): Stack<T> {
    return StackImpl.create(elements.asList())
}


fun String.checkParentheses(): Boolean {
    val stack = StackImpl<Char>()

    for (character in this) {
        when (character) {
            '(' -> stack.push(character)
            ')' -> if (stack.isEmpty) {
                return false
            } else {
                stack.pop()
            }
        }
    }
    return stack.isEmpty
}