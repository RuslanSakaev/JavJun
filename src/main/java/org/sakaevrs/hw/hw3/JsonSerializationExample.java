package org.sakaevrs.hw.hw3;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonSerializationExample {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        // Создаем объект Student
        Student student = new Student("Сергей Мельников", 24, 2.8);

        // Сериализуем объект в JSON
        try {
            objectMapper.writeValue(new File("student.json"), student);
            System.out.println("Object serialized to JSON successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Десериализуем объект из JSON
        try {
            Student deserializedStudent = objectMapper.readValue(new File("student.json"), Student.class);
            System.out.println("Object deserialized from JSON successfully.");

            // Выводим все поля объекта, включая GPA
            deserializedStudent.display();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

