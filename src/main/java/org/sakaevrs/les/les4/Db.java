package org.sakaevrs.les.les4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;

public class Db {
    public static final String URL = "jdbc:mysql://localhost:3306";
    public static final String USER = "root";
    public static final String PASSWORD = "pePLiy25";

    public static void con() {
        //try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
        //    Statement statement = con.createStatement();
        //    statement.execute("DROP SCHEMA IF EXISTS `test`");   // Использование обратных кавычек
        //    statement.execute("CREATE SCHEMA `test`"); // Использование обратных кавычек
        //    statement.execute("CREATE TABLE `test`.`table` (`id` INT NOT NULL, `firstname` VARCHAR(45) NULL, `lastname` VARCHAR(45) NULL, PRIMARY KEY(`id`));");
        //    statement.execute("INSERT INTO `test`.`table` (`id`,`firstname`,`lastname`)\n" +
        //            "VALUES (1,'Иванов','Иван');");
        //    statement.execute("INSERT INTO `test`.`table` (`id`,`firstname`,`lastname`)\n" +
        //            "VALUES (2,'Петров','Петр');");
//
        //    ResultSet set = statement.executeQuery("SELECT * FROM test.table;");
        //    while (set.next()){
        //        System.out.println(set.getString(3) + " " + set.getString(2) + " " + set.getInt(1));
        //    }
//
        //} catch (SQLException e) {
        //    throw new RuntimeException(e);
        //}

        /**
         * TODO: Доработать в рамках домашней работы, разделить на методы
         * getconnection - для создания соединенй
         * getdata - для сбора данных из таблицы
         */

        //final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
        //        .configure()
        //        .build();
        //SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        //Session session = sessionFactory.openSession();
        //Magic magic = new Magic("Magic arrow", 10, 0, 0);
        //session.beginTransaction();
        //session.save(magic);
        //session.getTransaction().commit();
        //session.close();

        Connector connector = new Connector();
        // Session session = connector.getSession();
        //Magic magic = new Magic("Magic arrow", 10, 0, 0);
        //session.beginTransaction();
        //session.save(magic);
        //magic = new Magic("Lightning", 25, 0, 0);
        //session.save(magic);
        //magic = new Magic("Stone Skin", 0, 0, 6);
        //session.save(magic);
        //magic = new Magic("Stone Skin", 0, 6, 0);
        //session.save(magic);
        //magic = new Magic("Bloodlust", 0, 6, 0);
        //session.save(magic);
        //magic = new Magic("A curse", 0, -3, 0);
        //session.save(magic);
        //magic = new Magic("Treatment", -30, 0, 0);
        //session.save(magic);
        //session.getTransaction().commit();
        //session.close();

        try (Session session = connector.getSession()) {
            //List<Magic> books = session.createQuery("FROM Magic",
            //        Magic.class).getResultList();
            //books.forEach(b -> {
            //    System.out.println("Book of Magic : " + b);
            //});


            //String hql = "from Magic where id = :id";
            //Query<Magic> query = session.createQuery( hql, Magic.class);
            //query.setParameter("id", 10);
            //Magic magic = query.getSingleResult();
            //System.out.println(magic);
            //magic.setAttBonus(12);
            //magic.setName("Fury");
            //session.beginTransaction();
            //session.update(magic);
            //session.getTransaction().commit();


            Transaction t = session.beginTransaction();
            List<Magic> books = session.createQuery("FROM Magic",
                    Magic.class).getResultList();
            books.forEach(b -> {
                session.delete(b);
            });
            t.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

