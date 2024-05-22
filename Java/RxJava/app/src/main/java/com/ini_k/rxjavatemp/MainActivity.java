package com.ini_k.rxjavatemp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File dexOutputDir = getApplicationContext().getCodeCacheDir();
        dexOutputDir.setReadOnly();

        FirstExample demo = new FirstExample();
        demo.emit();
        demo.emit2();
        /////

        //기본 Just() 함수
        //순차적으로 처리한다
        //3개이상일시 Error 콜백까지 호출가능
        //2개일시 Comeplete까지만 가능

        Observable<String> source = Observable.just("빨강","초록","노랑");

        Disposable disposable = source.subscribe(
                value -> System.out.println("OnNext() ; Value : " + value),
                error -> System.out.println("OnError : error : " + error.getMessage()),
                () -> System.out.println("OnComplete")
        );
        System.out.println("isDispose() : " + disposable.isDisposed());

        ///

        Observable<Integer> sourceInt = Observable.create(
                (ObservableEmitter<Integer> emitter) -> {
                    emitter.onNext(100);
                    emitter.onNext(200);
                    emitter.onNext(300);
                    emitter.onComplete();
                }
        );
        sourceInt.subscribe(System.out::println);

        ///

        Observable<Integer> sourceInt2 = Observable.create(
                (ObservableEmitter<Integer> emitter) -> {
                    emitter.onNext(400);
                    emitter.onNext(500);
                    emitter.onNext(600);
                    emitter.onComplete();
                }
        );
        sourceInt2.subscribe(data -> System.out.println("Result : " + data));

        //상위 코드 원형
        sourceInt2.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("Result : " + integer);
            }
        });

        //Create 함수는 RxJava에 익숙한 사용자만 활용하도록 "권고"한다.
        //Create 사용시 Dispose(구독 해지)시 모든 콜백을 해지해야 한다.
        //구독자가 구독하는 동안에만 onNext와 onComplete를 호출해야한다.
        //에러가 발생했을때는 오직 onError 이벤트로만 에러를 전달해야 한다.
        //배압(back pressure)을 직접 처리해야 한다.

        //배압 - 배압(BackPressure)은 업스트림에서 데이터 발행과, 다운스트림에서 데이터를 처리하는 속도의 차이가 큰 것을 배압 이라 한다.
        //예시 - 데이터를 요청 하는 속도와 처리하는 속도가 현저 하게 다른 경우 발생함.

    }
}