package com.inik.kotlin_1.part0.chapter2

var textTemp: String? = null
//반듯이 타입지정해야함
lateinit var text: String
val test: Int by lazy {
    println("초기화 중")
    100
}
fun main(){
    textTemp = "name"

    text = "name"
    println(text)

    println("메인 함수 실행")
    println("초기화 한값 $test")
    println("두번째 호출 $test")
}