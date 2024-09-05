package com.inik.kotlin_1.part0.chapter1

fun main() {
    max(10, 3)
//    max(3,1)
    isHolyday("월")
}

fun max(a: Int, b: Int) {
//    val result = if(a > b){
//        println(a)
//    }else{
//        println(b)
//    }
    val result2 = if (a > b) {
        a
    } else {
        b
    }
    println(result2)
}

fun max2(a: Int, b: Int) {
    val result3 = if (a > b) a else b
    println(result3)
}

//월 화 수 목 금 토 일
fun isHolyday(dayOfWeek: String) {
    when (dayOfWeek) {
        "월" -> false
        "화" -> false
        "수" -> false
        "목" -> false
        "금" -> false
        "토" -> true
        "일" -> true
    }

    when (dayOfWeek) {
        "월",
        "화",
        "수",
        "목",
        "금" -> false
        "토",
        "일" -> true
    }

    val result = when (dayOfWeek) {
        "토",
        "일" -> true
        else -> false
    }
    println(result)

//    when (dayOfWeek) { // dayOfWeek 매개변수가 any라면 어떠한것도 들어올수 있음
//        "토",
//        "일" -> if (dayOfWeek == "토") "좋아" else "너무 좋아"
//        in 2..4 -> {}
//        in listOf("월", "화") -> {}
//        else -> "안좋아"
//    }

}