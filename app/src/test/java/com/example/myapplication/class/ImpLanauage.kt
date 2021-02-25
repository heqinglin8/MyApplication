package com.example.myapplication.`class`

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Test

class ImpLanauage : Lanauage(){

    override var name: String
        get() = "Kotlin" //To change initializer of created properties use File | Settings | File Templates.
        set(value) {
        }

    override fun init() {
        println("我是$name")
    }

    @Test
   fun main(){
        var l = ImpLanauage()
        l.init()
        GlobalScope.launch() {
            delay(1000)
            println("Hello")
        }
        for (i in 1 until 10) {
            GlobalScope.launch {
                delay((100*i).toLong());
                print("❤️$i ")
            }
        }
        for (i in 0 until 10) {
            GlobalScope.launch {
                delay((100 - i * 10).toLong())
                print("❤️$i ")
            }
        }

    }

}