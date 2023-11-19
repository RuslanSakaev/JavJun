package org.sakaevrs.les.les1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main2 {
    public static void main(String[] args) {
        List<User> list = Arrays.asList(new User("Павел", 25), new User("Аркадий", 40), new User("Валера", 30));

        list.stream().map(user -> new User(user.name, user.age - 5)).filter(user -> user.age <= 25).forEach(System.out::println);

    }
}
