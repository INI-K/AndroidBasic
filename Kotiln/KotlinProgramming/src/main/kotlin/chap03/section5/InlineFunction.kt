package chap03.section5

fun main(){
    shortFunc(3){ println("FirstCall: $it") }
    shortFunc(5){ println("SecondCall : $it") }
}
inline fun shortFunc(a: Int, out: (Int) -> Unit){
    println("Before calling out()")
    out(a)
    println("After calling out()")
}