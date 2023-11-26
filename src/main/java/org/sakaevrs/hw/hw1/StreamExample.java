package org.sakaevrs.hw.hw1;

//Напишите программу, которая использует Stream API для обработки списка чисел.
//Программа должна вывести на экран среднее значение всех четных чисел в списке.

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.averagingInt;

public class StreamExample {
    public static void main(String[] args) {
        List<Double> numbers = Arrays.asList(5.596, 10.0, 3.97, 66.57, 8.56, 9.32, 8.6, 8.66, 2.363, 9.5);

        //double average = numbers.stream() // создает поток из списка чисел
        //        .filter(n -> n % 2 == 0)  // фильтруем только четные числа
        //        .mapToInt(Integer::intValue) // преобразуем Integer в int
        //        .average()  // находим среднее значение
        //        .orElse(0.0);  // если списка нет, возвращаем 0.0

        //System.out.println("Среднее значение четных чисел: " + average);


        //с использованием статических импортёров и метода collect для подсчета среднего значения:
        double average = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(averagingDouble(Double::doubleValue));

        System.out.println("Среднее значение четных чисел: " + average);
    }
}
