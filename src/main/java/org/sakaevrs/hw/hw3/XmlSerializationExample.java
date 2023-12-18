package org.sakaevrs.hw.hw3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlSerializationExample {
    public static void main(String[] args) {
        try {
            // Создаем объект Student
            Student student = new Student("Пётр Николаев", 31, 3.1);

            // Сериализуем объект в XML
            JAXBContext context = JAXBContext.newInstance(Student.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(student, new File("student.xml"));
            System.out.println("Object serialized to XML successfully.");

            // Десериализуем объект из XML
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Student deserializedStudent = (Student) unmarshaller.unmarshal(new File("student.xml"));
            System.out.println("Object deserialized from XML successfully.");

            // Выводим все поля объекта, включая GPA
            deserializedStudent.display();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

