package org.sakaevrs.hw.hw4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateExample {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        // Вставка данных
        insertCourse(new Course("Java Basics", 30));
        insertCourse(new Course("Java for Beginners", 40));
        insertCourse(new Course("Advanced Java Programming", 50));
        insertCourse(new Course("Web Development with HTML and CSS", 30));
        insertCourse(new Course("JavaScript and jQuery Essentials", 35));
        insertCourse(new Course("Introduction to Python", 45));
        insertCourse(new Course("Python for Data Science", 60));
        insertCourse(new Course("Database Management with SQL", 40));
        insertCourse(new Course("NoSQL Databases: An Overview", 30));
        insertCourse(new Course("Machine Learning Fundamentals", 50));
        insertCourse(new Course("Deep Learning with TensorFlow", 55));
        insertCourse(new Course("Mobile App Development with Flutter", 45));
        insertCourse(new Course("Building RESTful APIs with Node.js", 40));
        insertCourse(new Course("Cloud Computing Basics", 35));
        insertCourse(new Course("Cybersecurity Fundamentals", 50));
        insertCourse(new Course("Agile and Scrum Methodologies", 25));
        insertCourse(new Course("Software Testing and Quality Assurance", 40));
        insertCourse(new Course("UI/UX Design Principles", 30));
        insertCourse(new Course("Digital Marketing Essentials", 20));
        insertCourse(new Course("Blockchain Technology Explained", 40));
        insertCourse(new Course("Introduction to IoT (Internet of Things)", 30));

        // Чтение данных
        Course course = getCourse(1);
        if (course != null) {
            System.out.println(course.getTitle());
        }

        // Обновление данных
        updateCourse(1, "Advanced Java", 45);

        // Удаление данных
        deleteCourse(1);

        sessionFactory.close();
    }

    public static void insertCourse(Course course) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(course);
            transaction.commit(); // Здесь транзакция завершается успешно
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Откат транзакции в случае ошибки
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static Course getCourse(long courseId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Course.class, courseId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateCourse(long courseId, String newTitle, int newDuration) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Course course = session.get(Course.class, courseId);
            if (course != null) {
                course.setTitle(newTitle);
                course.setDuration(newDuration);
                session.update(course);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public static void deleteCourse(long courseId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Course course = session.get(Course.class, courseId);
            if (course != null) {
                session.delete(course);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
