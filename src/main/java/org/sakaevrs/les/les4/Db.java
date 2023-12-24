package org.sakaevrs.les.les4;

import java.sql.*;

public class Db {
    public static final String URL = "jdbc:mysql://localhost:3306";
    public static final String USER = "root";
    public static final String PASSWORD = "pePLiy25";

    public static void con() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            Statement statement = con.createStatement();
            statement.execute("DROP SCHEMA IF EXISTS `test`");   // Использование обратных кавычек
            statement.execute("CREATE SCHEMA `test`"); // Использование обратных кавычек
            statement.execute("CREATE TABLE `test`.`table` (`id` INT NOT NULL, `firstname` VARCHAR(45) NULL, `lastname` VARCHAR(45) NULL, PRIMARY KEY(`id`));");
            statement.execute("INSERT INTO `test`.`table` (`id`,`firstname`,`lastname`)\n" +
                    "VALUES (1,'Иванов','Иван');");
            statement.execute("INSERT INTO `test`.`table` (`id`,`firstname`,`lastname`)\n" +
                    "VALUES (2,'Петров','Петр');");

            ResultSet set = statement.executeQuery("SELECT * FROM test.table;");
            while (set.next()){
                System.out.println(set.getString(3) + " " + set.getString(2) + " " + set.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

/**
 * TODO: Доработать в рамках домашней работы, разделить на методы
 * getconnection - для создания соединенй
 * getdata - для сбора данных из таблицы
 */