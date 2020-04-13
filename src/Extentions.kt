inline infix fun  String.example(code: () -> Unit) {
    println("---Example of ${this}---")
    code()
}