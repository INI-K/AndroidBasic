package Chap04.section01

fun main(){
    val a = 4
    val b = 7

    val max = if (a > b) {
        println("a 선택")
        a
    }else{
        println("b 선택")
        b
    }

    println(max)

}