package trie

import example

fun main() {

    "Trie, insert and contains" example {
        val trie = Trie<Char>()
        trie.insert("engi2nee")
        if (trie.contains("engi2nee")) {
            println("engi2nee is in the trie")
        }
    }

    "Trie, remove" example {
        val trie = Trie<Char>()

        trie.insert("gym")
        trie.insert("gyms")
        trie.insert("miss")

        println("\n*** Before removing ***")
        assert(trie.contains("gym"))
        println("gym is in the trie")
        assert(trie.contains("gyms"))
        println("gyms is in the trie")
        assert(trie.contains("miss"))
        println("miss is in the trie")

        println("\n*** After removing gym ***")
        trie.remove("gym")
        assert(!trie.contains("gym"))
        assert(trie.contains("gyms"))
        assert(trie.contains("miss"))
        println("gyms is still in the trie")
    }

    "Tri, prefix matching" example {
        val trie = Trie<Char>().apply {
            insert("car")
            insert("card")
            insert("care")
            insert("cared")
            insert("cars")
            insert("carbs")
            insert("carapace")
            insert("cargo")
        }

        println("\nCollections starting with \"car\"")
        val prefixedWithCar = trie.collections("car")
        println(prefixedWithCar)

        println("\nCollections starting with \"care\"")
        val prefixedWithCare = trie.collections("care")
        println(prefixedWithCare)
    }

}