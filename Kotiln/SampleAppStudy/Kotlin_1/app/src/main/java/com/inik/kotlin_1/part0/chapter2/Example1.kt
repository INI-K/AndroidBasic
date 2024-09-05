package com.inik.kotlin_1.part0.chapter2

fun main(){
    //1. 익명함수
    //2. 변수처럼 사용되서, 함수의 argument, return
    //3. 한 번 사용되고 , 재사용되지 않는 함수
    val a = fun(){ println("Hello") }
    val c: Int =10
    val b: (Int) -> Int = {
        it * 10
//        println(it)
    }
    println(b(10))
    val d ={i: Int, j: Int -> i * j}
    val f : (Int,String,Boolean) -> String ={a,b,c -> b}

    hello(10,b)
}
fun hello(a: Int, b: (Int) -> Int){
    println(a)
    println(b(20))
}