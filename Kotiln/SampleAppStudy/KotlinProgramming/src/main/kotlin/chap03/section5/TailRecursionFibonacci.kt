package chap03.section5

import java.math.BigInteger


fun main(){
    val n = 100
    val first = BigInteger("0")
    val secound = BigInteger("1")

    println(fibonacci(n, first, secound))
}

tailrec fun fibonacci(n: Int, a: BigInteger ,b: BigInteger): BigInteger{
    return if (n == 0)  a else fibonacci(n-1,b,a+b)
}