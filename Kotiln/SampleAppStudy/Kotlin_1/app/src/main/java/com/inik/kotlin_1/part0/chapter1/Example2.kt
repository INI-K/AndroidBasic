package com.inik.kotlin_1.part0.chapter1

fun main() {
//    println("Hello world")
//    test()
    val result = test(1, c = 5)
    test2(id = "태환님", name = "김태환", nickName = "태환")
    println(result)
    println(time1(1,3))
    println(time2(1,3))
}

// 2. 함수

fun test(a: Int, b: Int = 3, c: Int = 4): Int {
    println(a + b)
    return a + b
}

fun test2(name: String, nickName: String, id: String) = println(name + nickName + id)

fun time1(a: Int, b: Int) : Int {
    return a * b
}
fun time2(a: Int, b:Int) = a * b
