package org.sakaevrs.hw.hw2;


abstract class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

class Dog extends Animal {

    public Dog(String name, int age) {
        super(name, age);
    }

    //public void makeSound() {
        //    System.out.println("Woof! Woof!");
        //}
}

class Cat extends Animal {

    public Cat(String name, int age) {
        super(name, age);
    }

    public void makeSound() {
        System.out.println("Meow! Meow!");
    }
}