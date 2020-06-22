package trie

class Trie<Key> {

    private val root = TrieNode<Key>(key = null, parent = null)
    private val storedLists: MutableSet<List<Key>> = mutableSetOf()

    val lists: List<List<Key>>
        get() = storedLists.toList()

    val count: Int
        get() = storedLists.count()

    val isEmpty: Boolean
        get() = storedLists.isEmpty()

    fun insert(list: List<Key>) {
        var current = root

        list.forEach { element ->
            //To prevent creating duplicated children
            if (current.children[element] == null) {
                current.children[element] = TrieNode(element, current)
            }
            //child inserted/already exists, move pointer to it
            current = current.children[element]!!
        }

        current.isTerminating = true
        storedLists.add(list)
    }

    fun contains(list: List<Key>): Boolean {
        var current = root
        //all elements should exist
        list.forEach { element ->
            val child = current.children[element] ?: return false
            current = child
        }
        //if the items all exist but last node isn't terminating, return false
        return current.isTerminating
    }

    fun remove(list: List<Key>) {
        var current = root

        list.forEach {
            val child = current.children[it] ?: return
            current = child
        }

        if (!current.isTerminating) return

        current.isTerminating = false
        storedLists.remove(list)
        // if the node still has children that means there is other
        // words longer than the word we are trying to remove.
        // marking current as isTerminating = false is enough to delete it
        // else, loop back to root and delete all items
        val parent = current.parent
        while (current.children.isEmpty() && !current.isTerminating) {
            parent?.let {
                it.children[current.key!!] = null
                current = it
            }
        }
    }


    fun collections(prefix: List<Key>): List<List<Key>> {
        var current = root
        // make sure all items of prefix exists
        prefix.forEach { element ->
            val child = current.children[element] ?: return emptyList()
            current = child
        }
        //pass terminating node
        return collections(prefix, current)
    }

    private fun collections(prefix: List<Key>, node: TrieNode<Key>?): List<List<Key>> {
        val results = mutableListOf<List<Key>>()
        println("Checking prefix: $prefix, with node ${node?.key}")
        // Each new isTerminating node shows a new word
        if (node?.isTerminating == true) {
            results.add(prefix)
        }
        // recursive call, by passing prefix+key as new prefix, and current node as next
        node?.children?.forEach { (key, node) ->
            results.addAll(collections(prefix + key, node))
        }

        return results
    }
}
