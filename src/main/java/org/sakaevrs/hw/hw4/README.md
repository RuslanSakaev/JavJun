# Java Junior (семинары)
## Урок 4. Базы данных и инструменты взаимодействия с ними
- Создайте базу данных (например, SchoolDB).
- В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
- Настройте Hibernate для работы с вашей базой данных.
- Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
- Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
- Убедитесь, что каждая операция выполняется в отдельной транзакции.

#### Пример решения:

1. Создадим базу данных SchoolDB с полями id (ключ), title, и duration:
```mysql-sql
CREATE DATABASE SchoolDB;

USE SchoolDB;

CREATE TABLE Courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    duration INT
);
```
2. Настроим Hibernate с учётом имеющихся проектов:
```xml
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <property
                name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property
                name="hibernate.connection.url">jdbc:mysql://localhost:3306</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">pePLiy25</property>

        <property name="hibernate.c3p0.min_size">5</property>
        <property name="hibernate.c3p0.max_size">20</property>
        <property name="hibernate.c3p0.timeout">300</property>
        <property name="hibernate.c3p0.max_statements">50</property>
        <property name="hibernate.c3p0.idle_test_period">3000</property>

        <property
                name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>
        <property name="show_sql">true</property>
        <property name="hibernate.hbm2ddl.auto">update</property>

        <mapping class="org.sakaevrs.les.les4.Magic" />
        <mapping class="org.sakaevrs.hw.hw4.Course" />

    </session-factory>
</hibernate-configuration>
```

Далее описываем код в классе Courses и HibernateExample.
