package com.chen.demo.entity

class State(val name: String) {

    var states: MutableMap<String, State> = mutableMapOf()

    fun addState(event: String, state: State): State {
        states[event] = state
        return this
    }

    //
    fun handleState(event: String): State? {
        val state = states[event]

        if (state == null) {
            println(" event: $event")
            return null
        }

        println("state.name:${state.name}, event: $event")
        return state
    }
}


fun main(args: Array<String>) {
    val init = State("init")

    val one = State("one")

    val two = State("two")

    val three = State("three")

    val four = State("four")

    init.addState("init", one)

    one
        .addState("two", two)
        .addState("three", three)

    two
        .addState("four", four)

    three.addState("clear", one)

    three.addState("clear", one)

    init.handleState("init")!!
        .handleState("two")!!
        .handleState("four")
}
