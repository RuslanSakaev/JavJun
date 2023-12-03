package org.sakaevrs.hw.hw2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Animal[] animals = {
                new Dog("Buddy", 3),
                new Cat("Whiskers", 5),
                new Dog("Max", 2),
                new Cat("Mittens", 4)
        };

        for (Animal animal : animals) {
            printObjectInfo(animal);
            invokeMakeSoundMethod(animal);
            System.out.println();
        }
    }

    private static void printObjectInfo(Object obj) {
        Class<?> clazz = obj.getClass();
        System.out.println("Class: " + clazz.getSimpleName());

        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // Делаем приватное поле доступным, если не доступно напрямую
                if (!field.canAccess(obj)) {
                    field.setAccessible(true);
                }

                // Игнорируем статические поля
                if (!Modifier.isStatic(field.getModifiers())) {
                    try {
                        System.out.println(field.getName() + ": " + field.get(obj));
                    } catch (IllegalAccessException e) {
                        System.err.println("Error accessing field " + field.getName() + ": " + e.getMessage());
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    private static void invokeMakeSoundMethod(Object obj) {
        Class<?> clazz = obj.getClass();
        try {
            Method makeSoundMethod = clazz.getMethod("makeSound");
            makeSoundMethod.invoke(obj);
        } catch (NoSuchMethodException e) {
            System.err.println("Method makeSound not found in class " + clazz.getSimpleName());
        } catch (Exception e) {
            System.err.println("Error invoking makeSound method in class " + clazz.getSimpleName() + ": " + e.getMessage());
        }
    }
}