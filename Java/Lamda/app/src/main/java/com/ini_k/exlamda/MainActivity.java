package com.ini_k.exlamda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyNumber max = (x , y) -> (x >= y) ? x : y;
        System.out.println(max.getMax(10,20));

        System.out.println("----");

        String s1 = "Hello";
        String s2 = "World";
        StringConCatImpl conCat = new StringConCatImpl();
        conCat.makeString(s1,s2);

        System.out.println("----");

        StringConcat conCat2 = (s,v) -> System.out.println(s + ", "+ v);
        conCat2.makeString(s1,s2);
    }

}