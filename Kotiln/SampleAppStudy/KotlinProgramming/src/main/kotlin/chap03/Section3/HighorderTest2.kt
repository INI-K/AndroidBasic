package chap03.Section3


fun main(){
    var result: Int
    result = highOrder({x , y -> x + y},10,20)
    println(result)
}

fun highOrder(sum: (Int,Int) -> Int, a: Int,b: Int): Int{
    return sum(a,b)
}