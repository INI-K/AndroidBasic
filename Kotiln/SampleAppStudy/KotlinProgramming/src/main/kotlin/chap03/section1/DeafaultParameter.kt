package chap03.section1


fun main(){
    val name = "김태환"
    val email = "annette57@naver.c"

    add(name)
    add(name,email)
    defaultArgs()
    defaultArgs(200)
}

fun add(name: String, email: String ="Default"){
    val output = "${name}님의 이메일은 ${email}입니다."
    println(output)
}
fun defaultArgs(x: Int = 100, y: Int = 200){
    println(x + y)
}