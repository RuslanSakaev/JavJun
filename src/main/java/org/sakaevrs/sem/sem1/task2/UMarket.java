package org.sakaevrs.sem.sem1.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Магазин U-Market
 */
public class UMarket {

    //region Методы

    public <T extends org.sakaevrs.sem.sem1.task2.Thing> T getThingByIndex(Class<T> clazz, int index)
    {
        /*int counter = 1;
        for (var thing : things)
        {
            if (clazz.isAssignableFrom(thing.getClass())){
                if (index == counter++)
                    return (T)thing;
            }
        }
        return null;*/
        AtomicInteger counter = new AtomicInteger(1);
        return things.stream()
                .filter(clazz::isInstance)
                .filter(thing -> index == counter.getAndIncrement())
                .map(clazz::cast)
                .findFirst()
                .orElse(null);
    }
    public <T extends org.sakaevrs.sem.sem1.task2.Thing> Collection<T> getThings(Class<T> clazz)
    {
        /*Collection<T> list = new ArrayList<>();
        for (var thing : things)
        {
            if (clazz.isAssignableFrom(thing.getClass())){
                list.add((T)thing);
            }
        }
        return list;*/
        return things.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .collect(Collectors.toList());
    }

    /**
     * Распечатать список товаров по типу
     * @param <T>
     */
    public <T extends org.sakaevrs.sem.sem1.task2.Thing> void printThings(Class<T> clazz){

        /*int index = 1;
        for (var thing : things)
        {
            if (clazz.isInstance(thing)){
                if (Food.class.isAssignableFrom(thing.getClass())){
                    System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, thing.getName(), ((Food)thing).getProteins() ? "Да" : "Нет",
                            ((Food)thing).getFats() ? "Да" : "Нет", ((Food)thing).getCarbohydrates() ? "Да" : "Нет");
                }
                else {
                    System.out.printf("[%d] %s\n", index++, thing.getName());
                }
            }
        }*/

        int[] counter = {1};

        things.stream()
                .filter(clazz::isInstance)
                .forEach(thing -> {

                    /*System.out.printf(
                            Food.class.isAssignableFrom(thing.getClass()) ?
                                    "[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n" :
                            "[%d] %s\n", counter[0]++, thing.getName(),
                            ((Food)thing).getProteins() ? "Да" : "Нет",
                            ((Food)thing).getFats() ? "Да" : "Нет",
                            ((Food)thing).getCarbohydrates() ? "Да" : "Нет");*/

                    if (org.sakaevrs.sem.sem1.task2.Food.class.isAssignableFrom(thing.getClass())) {
                        org.sakaevrs.sem.sem1.task2.Food foodThing = (org.sakaevrs.sem.sem1.task2.Food) thing;
                        System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                                counter[0]++, thing.getName(),
                                foodThing.getProteins() ? "Да" : "Нет",
                                foodThing.getFats() ? "Да" : "Нет",
                                foodThing.getCarbohydrates() ? "Да" : "Нет");
                    } else {
                        System.out.printf("[%d] %s\n", counter[0]++, thing.getName());
                    }
                });
    }

    private void initializeThings()
    {
        things.add(new org.sakaevrs.sem.sem1.task2.Pen());
        things.add(new org.sakaevrs.sem.sem1.task2.Notebook());

        things.add(new org.sakaevrs.sem.sem1.task2.Chicken());
        things.add(new org.sakaevrs.sem.sem1.task2.Fruit());
        things.add(new org.sakaevrs.sem.sem1.task2.OliveOil());

        things.add(new org.sakaevrs.sem.sem1.task2.BalykCheese());
        things.add(new org.sakaevrs.sem.sem1.task2.Crisps());
        things.add(new org.sakaevrs.sem.sem1.task2.ChocolateBar());

        things.add(new org.sakaevrs.sem.sem1.task2.DumplingsBerries());
        things.add(new org.sakaevrs.sem.sem1.task2.DumplingsMeat());
        things.add(new org.sakaevrs.sem.sem1.task2.Cheburek());
    }

    //endregion

    //region Конструкторы

    public UMarket()
    {
        things = new ArrayList<>();
        initializeThings();
    }

    //endregion

    //region Поля

    /**
     * Товары в магазине
     */
    private final Collection<org.sakaevrs.sem.sem1.task2.Thing> things;

    //endregion

}
