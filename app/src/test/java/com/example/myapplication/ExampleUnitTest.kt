package com.example.myapplication

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        var a:Byte = 2
        println(a)

        var g = 0x0f
        println(g)

        val oneMillion = 1_000_000
        val creditCardNumber = 1234_5678_9012_3456L
        val socialSecurityNumber = 999_99_9999L
        val hexBytes = 0xFF_EC_DE_5E
        val bytes = 0b11010010_01101001_10010100_10010010

        println("oneMillion => $oneMillion")
        println("creditCardNumber => $creditCardNumber")
        println("socialSecurityNumber => $socialSecurityNumber")
        println("hexBytes => $hexBytes")
        println("bytes => $bytes")


        val numValue: Int = 127
        val numValueBox: Int? = numValue

/*
    比较两个数字
 */
        var result: Boolean
        result = numValue == numValueBox
        println("numValue == numValueBox => $result")  // => true,其值是相等的

        result = numValue === numValueBox
/*
  上面定义的变量是Int类型，大于127其内存地址不同，反之则相同。
  这是`kotlin`的缓存策略导致的，而缓存的范围是` -128 ~ 127 `。
  故，下面的打印为false
*/
        println("numValue === numValueBox => $result")


        var char1 :Char = 'a';

        var var1 = char1.toByte()
        var var2 = char1.toInt()+1
        var var3 = char1.toString()
        var var4 = char1.toFloat()
        var var5 = char1.toShort()

        println("var1 => $var1 \n var2 => $var2 \n var3 => $var3 \n var4 => $var4 \n var5 => $var5")


        val text1: String = "我来了！"
        var text2: String = "$text1 kotlin"
        var text3: String = "$text2 ${text1.length} 哈哈！！！！"
        println(text1)
        println(text2)
        println(text3)
    }



}
