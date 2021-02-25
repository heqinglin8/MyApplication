package com.example.myapplication.`class`

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

enum class Color(var argb:Int){

    RED(0xFF0000) {
        override fun print() {
            println("红色")
        }
    },
    WHITE(0xFFFFFF) {
        override fun print() {
            println("白色")
        }
    },
    BLACK(0x000000) {
        override fun print() {
            println("黑色")
        }
    },
    GREEN(0x00FF00) {
        override fun print() {
            println("绿色")
        }
    };


    abstract fun print()

}

class ColorTest{
    @Test
    fun main() {
        Color.BLACK.print()
    }
}
