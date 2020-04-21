package queues

//turn based game, remove a player and add at the end on queue
fun <T> Queue<T>.nextPlayer(): T? {
    val person = this.dequeue() ?: return null
    this.enqueue(person)
    return person
}