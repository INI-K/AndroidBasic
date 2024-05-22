package chap03.section5

fun main(){
    val number = 4
    val result: Long

    result = factortial(number)
    println("Factorial: $number -> $result")
}

fun factortial(n: Int): Long {
    return if (n == 1) n.toLong() else n * factortial(n - 1)
}