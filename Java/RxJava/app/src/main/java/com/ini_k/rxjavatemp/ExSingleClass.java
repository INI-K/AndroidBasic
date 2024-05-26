package com.ini_k.rxjavatemp;

import io.reactivex.Single;

public class ExSingleClass {
    String Name;

    public ExSingleClass(String name) {
        this.Name = name;
    }

    public String getName() {
        System.out.println(Name);
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
