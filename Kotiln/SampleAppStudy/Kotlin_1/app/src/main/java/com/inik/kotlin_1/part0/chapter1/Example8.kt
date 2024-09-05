package com.inik.kotlin_1.part0.chapter1

fun  main(){
    var name : String = "태환"
    var number : Int = 10 // non-null

    var nickname: String?  = "이니케이"
    var secondNumber: Int? = null

//    val result = if(nickname == null){
//        "값이 없음"
//    }else{
//        nickname
//    }
//    println(result)
//    nickname = null
    nickname = "이니케이"
    val size = nickname!!.length
    println(size)
//    val result = nickname?: "값이 없음"
}