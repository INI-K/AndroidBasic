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
        System.out.println("----");

        StringConcat conCat2 = (s,v) -> System.out.println(s + ", "+ v);
        conCat2.makeString(s1,s2);

        System.out.println("----");
        System.out.println("----");
        System.out.println("----");

        PrintString lamdaStr = s -> System.out.println(s);
        lamdaStr.showString("Hello Lamda_1");
        showMyString(lamdaStr);

        System.out.println("----");
        System.out.println("----");
        System.out.println("----");
        System.out.println("----");

        PrintString reStr = returnString();
        reStr.showString("hello ");

        System.out.println("----");
        System.out.println("----");
        System.out.println("----");
        System.out.println("----");
        System.out.println("----");

        MathUtils mathUtils = new MathUtils();

        System.out.println("람다 참조");
        IAdd addLambda = (x, y) -> mathUtils.AddElement(x , y);
        System.out.println(addLambda.add(10, 20));

        System.out.println("메서드 참조");
        IAdd addMethodRef = mathUtils::AddElement;
        System.out.println(addMethodRef.add(20,40));

        System.out.println(mathUtils.AddElement(5,6));

        System.out.println("----");
        System.out.println("----");
        System.out.println("----");
        System.out.println("----");
        System.out.println("----");
        System.out.println("----");

    }
    public static void showMyString(PrintString p){
        p.showString("Hello_Lamda_ 2");
    }
    public static PrintString returnString(){
        return  a -> System.out.println(a + "world");
    }
    public static PrintString showMyString2(PrintString p2){
        return p2;
    }
}