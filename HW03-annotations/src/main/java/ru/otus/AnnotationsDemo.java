package ru.otus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;

public class AnnotationsDemo {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

          TestRun test = new TestRun();

        try {
            test.run("ru.otus.OrganiserTest");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
