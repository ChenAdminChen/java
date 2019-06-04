package com.chen.annotation.annotationdemo


import org.junit.Before
import org.junit.Test
import java.util.*

class TravelTree {

    private val names = arrayOf(
        "Posie",
        "Isla",
        "Olivia",
        "Aurora",
        "Maeve",
        "Cora",
        "Amara",
        "Ada",
        "Amelia",
        "Charlotte",
        "Genevieve",
        "Ophelia",
        "Ava",
        "Rose",
        "Eleanor",
        "Lucy",
        "Astrid",
        "Freya",
        "Elodie",
        "Luna",
        "Adelaide",
        "Eloise",
        "Anna",
        "Adah",
        "Iris",
        "Violet",
        "Thea",
        "Adeline",
        "Evelyn",
        "Alice",
        "Jane",
        "Aurelia",
        "Elizabeth",
        "Nora",
        "Ivy",
        "Elsie",
        "Maia",
        "Imogen",
        "Hazel",
        "Esme",
        "Arabella",
        "Penelope",
        "Maisie",
        "Eliza",
        "Josephine",
        "Clara",
        "Lyra",
        "Willa",
        "Chloe",
        "Phoebe",
        "Milo",
        "Jasper",
        "Atticus",
        "Theodore",
        "Asher",
        "Silas",
        "Jack",
        "Finn",
        "Henry",
        "Felix",
        "Aarav",
        "Wyatt",
        "Aryan",
        "Oliver",
        "Oscar",
        "Leo",
        "Bodhi",
        "Arthur",
        "Julian",
        "Louis",
        "Levi",
        "Ethan",
        "Soren",
        "Harry",
        "Eli",
        "Theo",
        "James",
        "Charles",
        "Jude",
        "Cassius",
        "Ezra",
        "Axel",
        "Otis",
        "Liam",
        "Emmett",
        "Charlie",
        "Aaron",
        "Sebastian",
        "Declan",
        "Elio",
        "William",
        "Thomas",
        "Caleb",
        "Lucas",
        "Benjamin",
        "Kai",
        "Alexander",
        "Elijah",
        "Owen",
        "August"
    )

    private val maps: MutableMap<String, Node> = HashMap()

    @Before
    fun setUp() {
        val random = Random(System.currentTimeMillis())

        //
        maps[names[0]] = Node(names[0])

        for (i in 1 until names.size) {
            val name = names[i]

            if (random.nextFloat() <= 0.1) {
                maps[name] = Node(name, null, null)
            } else {
                val p1 = random.nextInt(maps.size)
                maps[name] = Node(name, names[p1], null)
            }
        }
    }

    @Test
    fun travel() {
        maps.values

            // resolve
            .map {
                resolve(it)
                it
            }

            // sort by value
            .sortedBy { it.value }

            // print tree out
            .forEach {
                for (i in 1..it.level)
//                for (i in 2..it.value!!.split(".").size)
                    print("  ")

                println(it.name)
            }

        print("$counter")
    }

    var counter: Int = 0

    //find names value
    private fun resolve(node: Node) {

        ++counter
        // if node resolved, do nothing
        if (node.value != null)
            return

        // if node is top level node (parent is null), assign value with it's name
        if (node.parent == null) {
            node.value = node.name
            return
        }

        // if parent is not resolved, resolve it
        val parent = maps[node.parent]!!
        if (parent.value == null)
            resolve(parent)

        // assign value with parent's value + current node's name
        node.value = parent.value + '.' + node.name
        node.level = parent.level + 1
    }
}

class Node(
    val name: String,

    val parent: String? = null,

    var value: String? = null

    , var level: Int = 0
)
