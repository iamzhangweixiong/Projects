package com.zhangwx.myapplication.Bean;

/**
 * Created by zhangwx on 2016/8/25.
 */
public class User {

    private final String firstName;
    private final String lastName;
    private final String age;

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
}
