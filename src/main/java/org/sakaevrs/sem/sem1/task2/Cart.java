package org.sakaevrs.sem.sem1.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

/**
 * Корзина
 * @param <T> Еда
 */
public class Cart <T extends Food>{

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;

    public Cart(UMarket market)
    {
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs(){
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));
    }

    /**
     * Балансировка корзины
     */
    public void cardBalancing()
    {
        //boolean proteins = false;
        //boolean fats = false;
        //boolean carbohydrates = false;

        //for (var food : foodstuffs)
        //{
        //    if (!proteins && food.getProteins())
        //        proteins = true;
        //    else
        //    if (!fats && food.getFats())
        //        fats = true;
        //    else
        //    if (!carbohydrates && food.getCarbohydrates())
        //        carbohydrates = true;
        //    if (proteins && fats && carbohydrates)
        //        break;
        //}

        final boolean[] proteins = new boolean[]{foodstuffs.stream().anyMatch(Food::getProteins)};
        final boolean[] fats = {foodstuffs.stream().anyMatch(Food::getFats)};
        final boolean[] carbohydrates = {foodstuffs.stream().anyMatch(Food::getCarbohydrates)};

        if (proteins[0] && fats[0] && carbohydrates[0])
        {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        //for (var thing : market.getThings(Food.class))
        //{
        //    if (!proteins && thing.getProteins())
        //    {
        //        proteins = true;
        //        foodstuffs.add((T)thing);
        //    }
        //    else if (!fats && thing.getFats())
        //    {
        //        fats = true;
        //        foodstuffs.add((T)thing);
        //    }
        //    else if (!carbohydrates && thing.getCarbohydrates())
        //    {
        //        carbohydrates = true;
        //        foodstuffs.add((T)thing);
        //    }
        //    if (proteins && fats && carbohydrates)
        //        break;
        //}

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

        //if (proteins && fats && carbohydrates)
        //    System.out.println("Корзина сбалансирована по БЖУ.");
        //else
        //    System.out.println("Невозможно сбалансировать корзину по БЖУ.");

        System.out.println(proteins[0] && fats[0] && carbohydrates[0]
                ? "Корзина сбалансирована по БЖУ."
                : "Невозможно сбалансировать корзину по БЖУ.");
    }

    /**
     * Вариант преподавателя

    public void cardBalancing()
    {
        boolean proteins = checkNutrientFlag(Food::getProteins);
        boolean fats = checkNutrientFlag(Food::getFats);
        boolean carbohydrates = checkNutrientFlag(Food::getCarbohydrates);

        if (proteins && fats && carbohydrates) {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        Collection<T> marketFoods = market.getThings(clazz);
        proteins = checkNutrientFlag(proteins, Food::getProteins, marketFoods);
        fats = checkNutrientFlag(fats, Food::getFats, marketFoods);
        carbohydrates = checkNutrientFlag(carbohydrates, Food::getCarbohydrates, marketFoods);

        if (proteins && fats && carbohydrates) {
            System.out.println("Корзина сбалансирована по БЖУ.");
        } else {
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
        }

    }
     */

    /**
     * Проверка наличия конкретного питательного элемента в корзине
     * @param nutrientCheck список продуктов в корзине
     * @return состояние обновленного флага питательного элемента
     */

    /**
    private boolean checkNutrientFlag(Predicate<Food> nutrientCheck) {
        Optional<T> optionalFood = foodstuffs.stream()
                .filter(nutrientCheck)
                .findFirst();
        return optionalFood.isPresent();
    }
     */

    /**
     * Поиск недостающих питательных элементов в корзине и добавление питательно элемента
     * исходя из общего фильтра продуктов
     * @param nutrientFlag наличие питательного элемента
     * @param nutrientCheck список продуктов в корзине
     * @param foods доступный список продуктов (исходя из текущего фильтра)
     * @return состояние обновленного флага питательного элемента (скорее всего будет true,
     * false - в случае, если невозможно найти продукт с нужным питательным элементом, в таком
     * случае, невозможно сбалансировать корзину по БЖУ
     */

    /**
    private boolean checkNutrientFlag(boolean nutrientFlag, Predicate<Food> nutrientCheck, Collection<T> foods) {
        if (!nutrientFlag) {
            Optional<T> optionalFood = foods.stream()
                    .filter(nutrientCheck)
                    .findFirst();
            optionalFood.ifPresent(foodstuffs::add);
            return optionalFood.isPresent();
        }
        return true;
    }
    */
}