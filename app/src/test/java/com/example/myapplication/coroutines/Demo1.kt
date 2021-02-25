package com.example.myapplication.coroutines

import kotlinx.coroutines.*
import org.junit.Test

/**
 * @PackageName com.example.myapplication.coroutines
 * @ClassName demo1
 * @Author heqinglin
 * @Date 2020/4/29 下午4:59
 * @Description TODO
 */
class Demo1 {

//    @Test
//    fun main(){
//        GlobalScope.launch {
//            delay(1000L)
//            println("World!")
//        }
//        println("Hello,")
//        Thread.sleep(2000)  //延迟 2 秒来保证 JVM 存活
//    }

//    @Test
//    fun main(){
//        GlobalScope.launch {
//            delay(1000L)
//            println("World!")
//        }
//        println("Hello,")
//        //等效于Thread.sleep(2000)
//        runBlocking {
//            delay(2000L) //延迟 2 秒来保证 JVM 存活
//        }
//    }

//    @Test
//    fun main() = runBlocking<Unit> { // 开始执行主协程
//        GlobalScope.launch { // 在后台启动一个新的协程并继续
//            delay(1000L)
//            println("World!")
//        }
//        println("Hello,") // 主协程在这里会立即执行
//        delay(2000L)      // 延迟 2 秒来保证 JVM 存活
//    }

//    延迟一段时间来等待另一个协程运行并不是一个好的选择。让我们显式（以非阻塞方式）等待所启动的后台 Job 执行结束：

//    @Test
//    fun main()= runBlocking {
//        val job = GlobalScope.launch {
//            delay(1000L)
//            println("world!")
//        }
//        println("Hello,") // 主协程在这里会立即执行
//        job.join()
//    }

//    @Test
//    fun main() = runBlocking { // this: CoroutineScope
//        launch { // 在 runBlocking 作用域中启动一个新协程
//            delay(1000L)
//            println("World!")
//        }
//        println("Hello,")
//    }

    /**
     * 作用域构建器
    除了由不同的构建器提供协程作用域之外，还可以使用 coroutineScope 构建器声明自己的作用域。它会创建一个协程作用域并且在所有已启动子协程执行完毕之前不会结束。
    runBlocking 与 coroutineScope 可能看起来很类似，因为它们都会等待其协程体以及所有子协程结束。 这两者的主要区别在于，runBlocking 方法会阻塞当前线程来等待，
    而 coroutineScope 只是挂起，会释放底层线程用于其他用途。 由于存在这点差异，runBlocking 是常规函数，而 coroutineScope 是挂起函数。
     */
//    @Test
//    fun main() = runBlocking { // this: CoroutineScope
//        launch {
//            delay(200L)
//            println("Task from runBlocking")
//        }
//
//        coroutineScope { // 创建一个协程作用域
//            launch {
//                delay(500L)
//                println("Task from nested launch")
//            }
//
//            delay(100L)
//            println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
//        }
//
//        println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
//    }

}