package ru.otus;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.annotation.Annotation;
import java.util.List;

public class TestRun {

    private static List<Method> findAnnotatedMethods(Class<?> targetClass, Class<? extends Annotation> annotation) {
        List<Method> annotatedMethods = new ArrayList<>();
        Method[] methods = targetClass.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(annotation)) {
                annotatedMethods.add(method);
            }
        }

        return annotatedMethods;
    }

    private static int runMethod(Object object, String methodName, Object... args){
        try {
            var method = object.getClass().getDeclaredMethod(methodName, toClasses(args));
            method.setAccessible(true);
            method.invoke(object, args);
            return 1;
        }
        catch (Exception e) {
            return 0;
        }
    }

    private static Class<?>[] toClasses(Object[] args) {
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }

    public void run(String className) throws Exception{
        Class<?> classTest = Class.forName(className);

        Object test = classTest.getDeclaredConstructor().newInstance();

        Class<? extends Annotation> before= Before.class;
        Class<? extends Annotation> testAnnotation= Test.class;
        Class<? extends Annotation> after= After.class;

        List<Method> methodsBefore = findAnnotatedMethods(classTest, before );
        List<Method> methodsTest = findAnnotatedMethods(classTest, testAnnotation);
        List<Method> methodsAfter = findAnnotatedMethods(classTest, after );

        int fails = methodsTest.size();
        for ( Method method : methodsTest ){
            for ( Method methodBefore : methodsBefore ){
                runMethod(test,methodBefore.getName());
            }

            fails -= runMethod(test,method.getName());
            for ( Method methodAfter : methodsAfter ){
                runMethod(test,methodAfter.getName());
            }

        }
        System.out.println("Total tests: " + methodsTest.size() + "; Successfully completed: " + (methodsTest.size() - fails)
        + "; Failed tests: " + fails
        );

    }



}
