package org.sakaevrs.sem.sem3.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.sakaevrs.sem.sem3.task2.ToDoListApp.*;
import static org.sakaevrs.sem.sem3.task2.ToDoListApp.FILE_XML;

public class Program {

    public static void main(String[] args) {
        List<org.sakaevrs.sem.sem3.task2.ToDo> tasks;
        File f = new File(FILE_JSON);
        if (f.exists() && !f.isDirectory())
            tasks = loadTasksFromFile(FILE_JSON);
        else
            tasks = prepareTasks();
        org.sakaevrs.sem.sem3.task2.ToDoListApp.saveTasksToFile(FILE_JSON, tasks);
        org.sakaevrs.sem.sem3.task2.ToDoListApp.saveTasksToFile(FILE_BIN, tasks);
        org.sakaevrs.sem.sem3.task2.ToDoListApp.saveTasksToFile(FILE_XML, tasks);

        displayTasks(tasks);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить новую задачу");
            System.out.println("2. Отметить задачу как выполненную");
            System.out.println("3. Выйти");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    org.sakaevrs.sem.sem3.task2.ToDoListApp.addNewTask(scanner, tasks);
                    break;
                case "2":
                    org.sakaevrs.sem.sem3.task2.ToDoListApp.markTaskAsDone(scanner, tasks);
                    break;
                case "3":
                    org.sakaevrs.sem.sem3.task2.ToDoListApp.saveTasksToFile(FILE_JSON, tasks);
                    org.sakaevrs.sem.sem3.task2.ToDoListApp.saveTasksToFile(FILE_BIN, tasks);
                    org.sakaevrs.sem.sem3.task2.ToDoListApp.saveTasksToFile(FILE_XML, tasks);
                    System.out.println("Список задач сохранен.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
                    break;
            }

            displayTasks(tasks);
        }




    }

    static List<org.sakaevrs.sem.sem3.task2.ToDo> prepareTasks()
    {
        ArrayList<org.sakaevrs.sem.sem3.task2.ToDo> list = new ArrayList<>();
        list.add(new org.sakaevrs.sem.sem3.task2.ToDo("Сходить в магазин за продуктами"));
        list.add(new org.sakaevrs.sem.sem3.task2.ToDo("Погулять с собакой"));
        list.add(new org.sakaevrs.sem.sem3.task2.ToDo("Заменить лампочку"));
        return list;
    }

}
