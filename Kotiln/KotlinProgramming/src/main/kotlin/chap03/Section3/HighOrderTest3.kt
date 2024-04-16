package chap03.Section3

fun main(){
    val out: () -> Unit = { println("Hello world")}

    out()
    val new = out
    new()
}