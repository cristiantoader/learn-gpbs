package com.ctoader.learn;

import com.google.protobuf.Timestamp;
import lombok.Builder;
import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class JavaApp {

    public static void main(String[] args) throws IOException {
        PersonWrapper.Person person = PersonWrapper.Person.newBuilder()
                .setId(1)
                .setName("John Doe")
                .setBirthday(Timestamp.newBuilder()
                        .setSeconds(System.currentTimeMillis())
                        .build())
                .build();

        int serializedSize = person.getSerializedSize();
        System.out.println("GPB serialized size: " + serializedSize); // 21

        PersonPojo personPojo = PersonPojo.builder()
                .id(person.getId())
                .name(person.getName())
                .brithday(new Date(person.getBirthday().getSeconds()))
                .build();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(personPojo);
        oos.flush();

        byte[] data = bos.toByteArray();
        System.out.println("POJO serialized size:" + data.length); // 270
    }


    @Data
    @Builder
    private static class PersonPojo implements Serializable {
        private static final long serialVersionUID = 8254588519628630755L;
        private Long id;
        private String name;
        private Date brithday;
    }
}
