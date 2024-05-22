package com.ini_k.exlamda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyNumber max = (x , y) -> (x >= y) ? x : y;
        System.out.println(max.getMax(10,20));
    }
}