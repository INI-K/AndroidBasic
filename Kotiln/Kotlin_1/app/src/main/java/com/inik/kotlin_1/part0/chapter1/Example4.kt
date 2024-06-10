package com.inik.kotlin_1.part0.chapter1

fun main() {
    val user = User("김태환")
    println(user.age)
    Kid("아이", 3, "male")
}

open class User(
    open val name: String,
    open var age: Int = 100
) //(private val name : String, val age : Int)(접근제한자 가능)
// 직접 상속시 open 선언해야함

class Kid(override val name: String, override var age: Int) : User(name, age) { //User를 상속받음
    var gender: String = "female"

    init {
        println("초기화 중 입니다")
    }
    constructor(name: String, age: Int, gender: String) : this(name, age) {
        this.gender = gender
        println("부 생성자 호출")
    }
}