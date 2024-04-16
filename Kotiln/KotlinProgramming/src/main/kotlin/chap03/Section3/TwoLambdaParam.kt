package chap03.Section3

fun main(){
    twoLambda({a, b -> "First $a , $b"},{ "Secound it"})
    twoLambda({a,b -> "First $a , $b"}){"seound it"}
}

fun twoLambda(first: (String, String) -> String, second: (String) -> String){
    println(first("One Param","Two Param"))
    println(second("One Param"))
}