package com.ctoader.learn;

import com.google.protobuf.Timestamp;

public class JavaApp {

    public static void main(String[] args) {
        PersonWrapper.Person person = PersonWrapper.Person.newBuilder()
                .setId(1)
                .setName("John Doe")
                .setBirthday(Timestamp.newBuilder()
                                      .setSeconds(System.currentTimeMillis())
                                      .build())
                .build();

        System.out.println("Happy birthday " + person + " !");
    }
}
