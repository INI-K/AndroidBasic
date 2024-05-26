package com.ini_k.rxjavatemp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File dexOutputDir = getApplicationContext().getCodeCacheDir();
        dexOutputDir.setReadOnly();

//        Rxjava 데이터 흐름과 전달에 관한 프로그래밍 패러다임
//        어떤 한 시대 사람들의 견해나 사고를 근본적으로 규정하고 있는 테두리로서의 인식의 체계. 또는 사물에 대한 이론적인 틀이나 체계.

        FirstExample demo = new FirstExample();
        demo.emit();
        demo.emit2();

        //기본 Just() 함수
        //순차적으로 처리한다
        //3개이상일시 Error 콜백까지 호출가능
        //2개일시 Comeplete까지만 가능

        Observable<String> source = Observable.just("빨강", "초록", "노랑");

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

        System.out.println("================");
        System.out.println("fromArray() 함수");
        System.out.println("================");

        Integer[] intergerArr = {100, 200, 300};
        Observable<Integer> sourceIntArr = Observable.fromArray(intergerArr);
        sourceIntArr.subscribe(System.out::println);

        //RxJava에서는 int 배열을 인식 시켜려면 Integer배열로 변환 해야한다.

        int[] intArr = {2, 3, 4};
        Observable<Integer> tempIntArrr = Observable.fromArray(toIntegerArray(intArr));
        tempIntArrr.subscribe(System.out::println);

        System.out.println("================");
        System.out.println("================");
        System.out.println("fromCallable() 함수");
        System.out.println("================");

        //Callable은  Runnable의 발전된 형태이며, 결과값을 Return 해주는 차이가 있다.
        Callable<String> callable = () -> {
            Thread.sleep(1000);
            return "Hello Callable";
        };

        Observable<String> callAbleSource = Observable.fromCallable(callable);
        callAbleSource.subscribe(System.out::println);

        System.out.println("================");
        System.out.println("================");
        System.out.println("fromFuture() 함수");
        System.out.println("================");

        Future<String> future = Executors.newSingleThreadExecutor().submit(() -> {
            Thread.sleep(1000);
            return "Hello Future";
        });
        Observable<String> futureAbleSource = Observable.fromFuture(future);
        futureAbleSource.subscribe(System.out::println);

        //Callable,FutureTask 사용 예시.

//        Callable<Integer> task = () -> {
//            Thread.sleep(5000);  // 1초동안 대기
//            return 123;  // 결과 반환
//        };
//
//        FutureTask<Integer> futureEx = new FutureTask<>(task);
//        new Thread(futureEx).start();  // Future 작업 실행
//
//        // 결과가 준비될 때까지 Blocking
//        try {
//            Integer result = futureEx.get();
//            System.out.println("퓨처 대기 Result: " + result);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        System.out.println("================");
        System.out.println("================");
        System.out.println("Single 클래스");
        System.out.println("================");
        ///오직 하나의 객체만 '발행'한다
        //따라서 하나의 결과만 Return 하는 API를 호출할떄 유용함

        Observable<String> singleSource  = Observable.just("Hello Single : 1");
        Single.fromObservable(singleSource)
                .subscribe(System.out::println);

        Observable.just("Hello Single : 2")
                .single("default item")
                .subscribe(System.out::println);

        String[] colors = {"Red","Blue","Gold"};
        Observable.fromArray(colors)
                .first("default value")
                .subscribe(System.out::println);

        Observable.empty()
                .single("Default Value")
                .subscribe(System.out::println);

        Observable.just(new ExSingleClass("이니케이"), new ExSingleClass("쿠스"))
                .take(1)
                .single(new ExSingleClass("쀈타"))
                .subscribe(ExSingleClass::getName);

        System.out.println("================");
        System.out.println("================");
        System.out.println("Maybe 클래스");
        System.out.println("================");
        //아마도,
        // 0개 혹은 1개 (0개가능 또는 1개만 가능 )

        System.out.println("================");
        System.out.println("================");
        System.out.println("Subject 클래스");
        System.out.println("================");

        AsyncSubject<String> subject = AsyncSubject.create();
        subject.subscribe(data -> System.out.println("Subscriber #1 ==> " + data));
        subject.onNext("Red");
        subject.onNext("Green");
        subject.subscribe(data -> System.out.println("Subscriber #2 ==> " + data));
        subject.onNext("Blue");
        subject.onComplete();

        Float[] temperature = {10.1f, 13.4f, 12.5f};
        Observable<Float> tempratureSource = Observable.fromArray(temperature);

        AsyncSubject<Float> tempratureAsyncSub = AsyncSubject.create();
        tempratureAsyncSub.subscribe(data -> System.out.println("temprature Subscriber #1 ==> " + data));

        tempratureSource.subscribe(tempratureAsyncSub);

        //public abstract class Subject<T> extends Observable<T> implements Observer<T>

        AsyncSubject<Integer> asyncSubInteger = AsyncSubject.create();
        asyncSubInteger.onNext(10);
        asyncSubInteger.onNext(11);
        asyncSubInteger.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        asyncSubInteger.onNext(12);
        asyncSubInteger.onComplete();
        asyncSubInteger.onNext(13);
        asyncSubInteger.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        asyncSubInteger.subscribe(data -> System.out.println("Subscriber #3 => " + data));


        System.out.println("================");
        System.out.println("================");
        System.out.println("BehaviorSubject 클래스");
        System.out.println("구독자가 구독하면 가장 최근값,혹은 기본값을 넘겨줌");
        System.out.println("================");

        BehaviorSubject<String> beHaviorSub = BehaviorSubject.createDefault("PINK");
        beHaviorSub.subscribe(data -> System.out.println("Subscriber #1 =>" + data));
        beHaviorSub.onNext("RED");
        beHaviorSub.onNext("GREEN");
        beHaviorSub.subscribe(data -> System.out.println("Subscriber #2 =>" + data));
        beHaviorSub.onNext("BLUE");
        beHaviorSub.onComplete();

        System.out.println("================");
        System.out.println("================");
        System.out.println("PublishSubject 클래스");
        System.out.println("해당 시점에 발생한 데이터만 구독자에게 전달받는다");
        System.out.println("================");

        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        publishSubject.onNext("RED");
        publishSubject.onNext("GREEN");
        publishSubject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        publishSubject.onNext("BLUE");
        publishSubject.onComplete();

        System.out.println("================");
        System.out.println("================");
        System.out.println("ReplaySubject 클래스");
        System.out.println("발행한 데이터 모두를 구독한 시점에 구독자에게 전달받는다.");
        System.out.println("================");

        ReplaySubject<String> replaySubject = ReplaySubject.create();
        replaySubject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        replaySubject.onNext("RED");
        replaySubject.onNext("GREEN");
        replaySubject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        replaySubject.onNext("BLUE");
        replaySubject.onComplete();

        System.out.println("================");
        System.out.println("================");
        System.out.println("ConnectableObservable 클래스");
        System.out.println("Subject클래스 처럼 Cold Observable을 Hot Observable로 변환한다.");
        System.out.println("================");

        String[] stringdata = {"RED", "GREEN", "BLUE"};
        Observable<String> balls = Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(i -> stringdata[i])
                .take(stringdata.length);
        ConnectableObservable<String> connectableObservableSource = balls.publish();
        connectableObservableSource.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        connectableObservableSource.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        connectableObservableSource.connect();

        try {
            Thread.sleep(2500);
            connectableObservableSource.subscribe(data -> System.out.println("Subscriber #3 => " + data));
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Integer[] toIntegerArray(int[] intArray) {
        return IntStream.of(intArray).boxed().toArray(Integer[]::new);
    }

}