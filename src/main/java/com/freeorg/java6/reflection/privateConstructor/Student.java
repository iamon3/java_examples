package com.freeorg.java6.reflection.privateConstructor;

/**
 * http://www.javaworld.com/article/2074753/core-java/object-hacking-in-java---power-of-reflection.html
 *
 * This is a class whose constructor is
 * made private so that it can not be
 * instantiated.
 *
 */
public class Student
{
    private String name;

    private Student()
    {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}