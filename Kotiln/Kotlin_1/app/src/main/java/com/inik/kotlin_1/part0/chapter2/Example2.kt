package com.inik.kotlin_1.part0.chapter2

fun main(){
    //확장함수 Extension function
    //기존에 정의대외 있는 클래스에 함수를 추가하는 기능
    val test = Test()
    Test().hello()
    test.hi()
}
fun Test.hi() = print("하이")
class Test(){
    fun hello() = println("안녕")
    fun bye() = println("잘가")
}