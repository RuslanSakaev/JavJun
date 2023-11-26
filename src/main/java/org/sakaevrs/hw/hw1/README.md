# Java Junior (семинары)
## Урок 1. Лямбды и Stream API.

1. Напишите программу, которая использует Stream API для обработки списка чисел. 
Программа должна вывести на экран среднее значение всех четных чисел в списке.
```
JavJun\src\main\java\org\sakaevrs\hw\hw1\StreamExample.java
```
- код программы:
```
public class StreamExample {
    public static void main(String[] args) {
        List<Double> numbers = Arrays.asList(5.596, 10.0, 3.97, 66.57, 8.56, 9.32, 8.6, 8.66, 2.363, 9.5);

        double average = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(averagingDouble(Double::doubleValue));

        System.out.println("Среднее значение четных чисел: " + average);
    }
}
```
2. *Дополнительная задача: Переделать метод балансировки корзины товаров cardBalancing() 
с использованием Stream API
```
JavJun\src\main\java\org\sakaevrs\sem\sem1\task2\Cart.java
```
- переделанный метод балансировки корзины товаров cardBalancing() с использованием Stream API:
```
    public void cardBalancing()
    {
        final boolean[] proteins = new boolean[]{foodstuffs.stream().anyMatch(Food::getProteins)};
        final boolean[] fats = {foodstuffs.stream().anyMatch(Food::getFats)};
        final boolean[] carbohydrates = {foodstuffs.stream().anyMatch(Food::getCarbohydrates)};

        if (proteins[0] && fats[0] && carbohydrates[0])
        {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        market.getThings(Food.class).stream()
                .filter(thing -> !proteins[0] && thing.getProteins() ||
                        !fats[0] && thing.getFats() ||
                        !carbohydrates[0] && thing.getCarbohydrates())
                .limit(3 - foodstuffs.size())  // Ограничиваем добавление не более чем 3 продуктами
                .forEach(thing -> {
                    proteins[0] |= thing.getProteins();
                    fats[0] |= thing.getFats();
                    carbohydrates[0] |= thing.getCarbohydrates();
                });

        System.out.println(proteins[0] && fats[0] && carbohydrates[0]
                ? "Корзина сбалансирована по БЖУ."
                : "Невозможно сбалансировать корзину по БЖУ.");
    }
}
```