package com.inik.kotlin_1.part0.chapter2

fun main() {
    //let, run, apply, also, with
    //1. let
    val a = 3
    a.let {}
    val user: User? = User("김태환", 10, true)

//    수신객체.let{
//
//        마지막줄///return
//    }
    val age = user?.let {
        it.age
    }
    println(age)

//    //2. run
//    수신객체. run{ this ->
//        마지막줄 리턴
//    }
    val kid = User("아이", 42, false)
    val kidAge = kid.run {
        this.age
    }
    println(kidAge)

    //3. applt
//    수신객체.apply{
//
//    }
//    return 값이 수신객체
    val kidName = kid.apply {
        name
    }
    println(kidName)

    val female = User("영하", 20 , true ,true)
    val femaleValue = female.apply {
        hasGlasses = false
    }
    println(femaleValue.hasGlasses)

    //4. also
//    수신객체.also{it -> // local varialble
//
//    }//return 수신객체(자기자신
    //실제로 객체를 초기화할때는 이런식으로 추천하지 않음(팀바팀)
    val male = User("민수", 17, false, true)
    val maleValue = male.also { user ->
        user.name
        user.hasGlasses = false
    }
    println(maleValue.hasGlasses)

    //5. with
//    with(수신객체){
//        ---
//        마지막줄
//    }
    val  result = with(male){
        hasGlasses = false
        true
    }
}
class User(
    val name: String,
    val age: Int,
    val gender: Boolean,
    var hasGlasses: Boolean = true
)