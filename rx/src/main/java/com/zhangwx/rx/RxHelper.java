package com.zhangwx.rx;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by zhangwx on 2016/9/14.
 */
public class RxHelper {

    Observable<String> myObservable = Observable.create(
            new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> sub) {
                    sub.onNext("Hello, world!");
                    sub.onCompleted();
                }
            }
    );

    Subscriber<String> mySubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            System.out.println(s);
        }
    };

    public void funtion() {
        myObservable.subscribe(mySubscriber);
    }

    Observable<String> myObservable2 = Observable.just("zhang");

    public void funtion2() {
        myObservable2.subscribe(System.out::println);
    }

    public void funtion3() {
        Observable.just("zhang", "__")
                .map(s -> s + "wei")
                .map(s -> s + "xiong")
                .subscribe(System.out::println);
    }
}
