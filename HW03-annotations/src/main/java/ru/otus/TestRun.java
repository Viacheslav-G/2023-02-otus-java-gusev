package ru.otus;

import java.lang.reflect.Method;

import static ru.otus.TestUtils.findAnnotatedMethods;
import static ru.otus.TestUtils.runMethod;


public class TestRun {

    public void run(String className) throws Exception{

        Class<?> classTest = Class.forName(className);

        int fails =  findAnnotatedMethods(classTest, Test.class).size() ;

        for ( Method method : findAnnotatedMethods(classTest, Test.class) ){
            Object test = classTest.getDeclaredConstructor().newInstance();

            for ( Method methodBefore : findAnnotatedMethods(classTest, Before.class)){
                runMethod(test,methodBefore.getName());
            }

            fails -= runMethod(test,method.getName());

            for ( Method methodAfter : findAnnotatedMethods(classTest, After.class) ){
                runMethod(test,methodAfter.getName());
            }

        }
        System.out.println("Total tests: " + findAnnotatedMethods(classTest, Test.class).size()+ "; Successfully completed: " + (findAnnotatedMethods(classTest, Test.class).size() - fails)
        + "; Failed tests: " + fails
        );

    }



}
