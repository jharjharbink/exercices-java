package org.example.exo.exceptions.exo4;

public class Student {
    String name;
    int age;

    public Student(String name, int age) throws InvalidAgeException {
        if (age < 0)
            throw new InvalidAgeException();
        this.name = name;
        this.age = age;
    }
}
