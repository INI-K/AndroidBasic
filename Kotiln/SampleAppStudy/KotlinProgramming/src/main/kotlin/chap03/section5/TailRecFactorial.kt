package chap03.section5

fun main(){
    val number = 5
    println("Factorial: $number -> ${factortial(number)}")
}

tailrec fun factorial(n: Int, run: Int = 1): Long{
    return if (n == 1)run.toLong() else factorial(n - 1, run * n)
}