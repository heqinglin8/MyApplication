package com.example.myapplication

import org.junit.Test

class ArraysTest{

    @Test
    fun main(){
        var arr1:Array<Int> = Array(5,{index->index*2});
        var arr2:Array<Int> = arrayOf(1,3,4,5,6,7,8)
        var arr3  = arrayOfNulls<Int>(5)
        for(v in arr1){
            print(v)
        }
        println("")
        for(v in arr2){
            print(v)
        }
        println("")
        for(v in arr3){
            print(v)
        }
        var arr5 :IntArray = intArrayOf(1,5,6,8)
        println("")
        for(v in arr5){
            print(v)
        }
        println("-------")
        for (i in 16 downTo  10 step 2){
            print("i => $i \t")
        }

        var arrayListTwo = arrayOf(1,3,5,7,9)

        for((index,value) in arrayListTwo.withIndex()){
            println("index => $index \t value => $value")
        }

        var temp = 6;
        when(temp){
            in arrayListTwo -> println("在单数数组")
            in 0 .. 10 -> println("全部数组")
            else -> println("都不在")
        }

        val testStr : String? = "123"
        println(testStr!!.length)

    }

    class Builder{
        private var name : String? = "Tom"
        private var age : Int? = 0
        private var sex : String? = "男"

        fun setName(name : String) : Builder?{
            this.name = name
            return this
        }

        fun setAge(age : Int) : Builder?{
            this.age = age
            return this
        }

        fun setSex(sex: String?) : Builder?{
            this.sex = sex
            return this
        }

        override fun toString(): String {
            return "Builder(name=$name, age=$age, sex=$sex)"
        }
    }

    @Test
   fun main2() {
        val builder : ArraysTest.Builder? = ArraysTest.Builder().setName("Lily")?.setSex("nv")?.setAge(10)
        println(builder.toString())
    }

}