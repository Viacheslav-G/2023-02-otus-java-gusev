package ru.otus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtils {

    static Class<?>[] toClasses(Object[] args) {
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }

    static List<Method> findAnnotatedMethods(Class<?> targetClass, Class<? extends Annotation> annotation) {
        List<Method> annotatedMethods = new ArrayList<>();
        Method[] methods = targetClass.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(annotation)) {
                annotatedMethods.add(method);
            }
        }

        return annotatedMethods;
    }
    static int runMethod(Object object, String methodName, Object... args){
        try {
            var method = object.getClass().getDeclaredMethod(methodName, toClasses(args));
            method.setAccessible(true);
            method.invoke(object, args);
            return 1;
        }
        catch (Throwable e) {
            return 0;
        }
    }

}
