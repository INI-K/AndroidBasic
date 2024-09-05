package chap03.Section3

fun main(){
    var result: Int
    val multi = {x: Int, y: Int -> x * y}
    result = multi(10,20)
    print(result)
}