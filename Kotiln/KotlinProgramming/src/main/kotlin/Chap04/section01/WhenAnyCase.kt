package Chap04.section01

import java.math.BigInteger

fun main(){
    cases("Hello")
    cases(1)
    cases(System.currentTimeMillis())
}

fun cases(obj: Any){
    when (obj) {
        1 -> println("Int : $obj")
        "Hello" -> println("String: $obj")
        is Long -> println("Long: $obj")
        !is Long -> println("Not a String")
        else -> println("Unkown")
    }
}