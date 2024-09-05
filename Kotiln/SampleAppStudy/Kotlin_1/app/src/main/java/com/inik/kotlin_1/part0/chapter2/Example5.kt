package com.inik.kotlin_1.part0.chapter2

fun main(){
    val person = Person("수지", 24)
    val dog = Dog("해피",24)

    println(person.toString())
    println(dog.toString())
    println(dog.copy(age = 3).toString())

    val cat: Cat = BlueCat()
    val result = when (cat) {
        is BlueCat -> {"블루"}
        is RedCat -> {"레드"}
        is GreenCat -> {"그린"}
        is WhiteCat -> {"흰색"}
    }
    println(result)
}

class Person(
    val name: String,
    val age: Int,
)

data class Dog(
    val name: String,
    val age: Int
)
//Sealed Class
//상속받은 자식클래스들을 알수있음
sealed class Cat
class BlueCat : Cat()
class RedCat: Cat()
class GreenCat: Cat()
class WhiteCat: Cat()