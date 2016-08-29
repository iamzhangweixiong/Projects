package com.zhangwx.myapplication.Bean;

import android.databinding.BaseObservable;

/**
 * Created by zhangwx on 2016/8/25.
 */
public class User extends BaseObservable {

    private String firstName;
    private String lastName;
    private String age;

    public User(String firstName, String lastName, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

