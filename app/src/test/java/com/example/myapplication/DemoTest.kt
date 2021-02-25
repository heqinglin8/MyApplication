package com.example.myapplication

import org.junit.Test
import java.util.*

/**
 * @PackageName com.example.myapplication
 * @ClassName DemoTest
 * @Author heqinglin
 * @Date 2020/5/13 下午3:53
 * @Description TODO
 */
class DemoTest {
    @Test
    fun main() {
        val lis: MutableList<Int> =
            ArrayList()
        for (i in 0..99) {
            lis.add(i)
        }
        val r = Runnable {
           val ll = lis.filter {it
               it>50
            }
            val it: Iterator<*> = ll.iterator()
            while (it.hasNext()) {
                println("it:" + it.next())
            }
        }
        for (i in 0..99) {
            val t = Thread(r)
            t.start()
        }
    }
}