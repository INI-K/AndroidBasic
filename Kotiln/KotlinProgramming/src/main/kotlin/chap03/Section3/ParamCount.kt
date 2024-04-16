package chap03.Section

fun main(){
    noParam { "Hello World" }
    noParam({"Hello World"})
}

fun noParam(out: () -> String) = println(out())