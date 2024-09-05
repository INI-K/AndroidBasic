package com.inik.kotlin_1.part0.chapter1

fun main(){

//    1..10 == IntRange(1, 10)
    for(i in 1 .. 10){
        print(i)
        print(".")
    }
    println()
    for(i in IntRange(1, 10)){
        print(i)
        print(".")
    }
    println()

    for(i in 1 until 10){ //끝숫자를 포함하지않음
        print(i)
        print(".")
    }
    println()

    for(i in 1..10 step (2)){
        print(i)
        print(".")
    }
    println()

    for(i in 10 downTo 1){ // 내려갈떄는 downTo
        print(i)
        print(".")
    }
    println()

    for(i in 10 downTo 1 step (2)){
        print(i)
        print(".")
    }
    println()

    var c = 1
    while(c < 11){
        print(c)
        print(".")
        c++
    }
}