package com.ini_k.simplemvc_240409;

public class Person {
    String name;
    String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public void printInfo(Person person){
        System.out.println("이름 : " + name);
        System.out.println("나이 : " + age);
    }
}
