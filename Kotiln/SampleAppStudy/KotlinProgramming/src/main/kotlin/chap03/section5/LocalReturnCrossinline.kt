package chap03.section5

fun main() {
    shortFunc5(3){
        println("First call: $it")
    }
}

inline fun shortFunc5(a: Int, crossinline out: (Int) -> Unit) {
    println("Before calling out")
    nestedFunc { out(a) }
    println("After callin out()")
}
fun nestedFunc(body: ()-> Unit){
    body()
}