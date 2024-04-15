package com.ini_k.simplemvc_240409;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;
    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVariable();
        setView();
    }
    public void setView(){
        button = (Button) findViewById(R.id.infoBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.printInfo(person);
            }
        });
    }
    public void setVariable(){
        person = new Person("김태환", "34");
    }
}