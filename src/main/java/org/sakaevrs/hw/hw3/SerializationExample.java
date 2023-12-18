package org.sakaevrs.hw.hw3;

import java.io.*;

public class SerializationExample {
    public static void main(String[] args) {
        // Создаем объект Student
        Student student = new Student("Сергей Васильв", 27, 4.5);

        // Сериализуем объект в файл
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            oos.writeObject(student);
            System.out.println("Object serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Десериализуем объект из файла
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"))) {
            Student deserializedStudent = (Student) ois.readObject();
            System.out.println("Object deserialized successfully.");

            // Выводим все поля объекта, включая GPA
            deserializedStudent.display();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
