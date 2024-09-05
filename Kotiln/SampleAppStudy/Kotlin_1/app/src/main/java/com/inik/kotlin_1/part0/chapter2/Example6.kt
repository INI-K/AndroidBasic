package com.inik.kotlin_1.part0.chapter2

//import com.inik.kotlin_1.part0.chapter2.Book.Companion.NAME
import com.inik.kotlin_1.part0.chapter2.Book.Novel.NAME

fun main(){

    println(Counter.count)

    Counter.countUp()
    Counter.countUp()

    println(Counter.count)
    Counter.hello()

}

object Counter: Hello() {
    init {
        println("카운터 초기화")
    }
    var count = 0
    fun countUp(){
        count++
    }
}
open class Hello(){
    fun hello() = println("hello")
}

class Book{
    companion object Novel{
        val NAME = "이름"
        const val NAME2 = "이름2"
        fun mockBook() = Book()
    }
}