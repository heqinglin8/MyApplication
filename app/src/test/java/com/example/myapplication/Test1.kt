package com.example.myapplication

import android.util.Log
import org.junit.Test

/**
 * @PackageName com.example.myapplication
 * @ClassName Test1
 * @Author heqinglin
 * @Date 2019-12-06 17:55
 * @Description TODO
 */


open class Fruit
open class Apple(name: String) : Fruit()
//
val mFruit = Fruit()
val mApple = Apple("苹果")
//

@Test
fun main(){
    println(mApple as Apple)

    println(mApple as Fruit)

    println(mFruit as Fruit)

//    println(mFruit as Apple)  //类型转换异常，父类型不允许转换成子类型：com.example.myapplication.Fruit cannot be cast to com.example.myapplication.Apple

    val list = listOf("1","2")
    list.forEach(::println)
}