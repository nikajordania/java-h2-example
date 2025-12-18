package org.example.lecture_12;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {
    private Integer id;
    private String name;
    private String lastname;
    private String groupName;
    private int age;

    public Person(String name, String lastname, String groupName, int age) {
        this.name = name;
        this.lastname = lastname;
        this.groupName = groupName;
        this.age = age;
    }
}
