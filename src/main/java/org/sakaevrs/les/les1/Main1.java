package org.sakaevrs.les.les1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Привет", "мир", "!", "я", "родился", "!");
        list.stream().filter(str -> str.length() > 4).filter(str -> str.contains("о")).forEach(System.out::println);

        Arrays.asList(1, 10, 0, 4, 5).stream().map(chislo -> "число: " + chislo + ". квадрат числа - " + chislo * chislo).forEach(System.out::println);

        Arrays.asList(1, 10, 0, 5, 4, 5).stream().sorted().distinct().forEach(System.out::println);

        System.out.println(Arrays.asList(1, 10, 0, 5, 4, 5).stream().sorted().distinct().findFirst().get());
    }
}
