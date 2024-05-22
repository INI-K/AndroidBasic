package com.ini_k.rxjavatemp;


import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class FirstExample {
    Observable observableOne;
    Observable observableTwo;

    public void emit(){
        observableOne.just("1", "2","3","4")
                .subscribe(System.out::println);
    }
    public void emitExit(){
    }
    public void emit2(){
        observableTwo.just("11", "22","33","44")
                .subscribe(System.out::println);
    }
}
