package com.example.demo.utils

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption

@ShellComponent
class ShellCommend {

    /**
     * 使用方法  add 1 2 3
     */
    @ShellMethod("add int")
    fun add(a: Int, b: Int, c: Int) {
        println("a:$a b:$b c:$c ")
        print("Sum{$a+$b+$c}")
    }

    @ShellMethod("test shellOption")
    fun sum(a: Int, @ShellOption("---b") b: Int, @ShellOption(defaultValue = "World") content: String) {
        val c = a + b
        println(c)

        println(content)
    }

    @ShellMethod("Add Numbers.")
    fun arrays(@ShellOption(arity = 3) numbers: FloatArray): Float {
        return numbers[0] + numbers[1] + numbers[2]
    }

    @ShellMethod(key= arrayOf("shell"),value = "Add Numbers.")
    fun shellTest(@ShellOption(arity = 3) numbers: FloatArray): Float {
        return numbers[0] + numbers[1] + numbers[2]
    }
}