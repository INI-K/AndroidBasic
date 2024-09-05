package chap02.section2

fun main(){
    val number = 10
    var language = "Korean"
    val secondNumber: Int = 20
    language = "English"

    println("numver : $number")
    println("language : $language")
    println("secondNumver : $secondNumber")

    var num : Double = 0.1

    for(x in 0..999){
        num += 0.1
    }

    println(num)


}