package org.sakaevrs.sem.sem1.task2;

/**
 * Оливковое масло
 */
public class OliveOil implements org.sakaevrs.sem.sem1.task2.HealthyFood {
    @Override
    public boolean getProteins() {
        return false;
    }

    @Override
    public boolean getFats() {
        return true;
    }

    @Override
    public boolean getCarbohydrates() {
        return false;
    }

    @Override
    public String getName() {
        return "Оливковое масло";
    }
}
