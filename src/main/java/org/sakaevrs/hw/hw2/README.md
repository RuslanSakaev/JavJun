# Java Junior (семинары)
## Урок 2. Reflection API.
### Задача 1:
- Создайте абстрактный класс "Animal" с полями "name" и "age".
- Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
- Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
- Выведите на экран информацию о каждом объекте.
- Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
```agsl
abstract class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

class Dog extends Animal {

    public Dog(String name, int age) {
        super(name, age);
    }

    //public void makeSound() {
        //    System.out.println("Woof! Woof!");
        //}
}

class Cat extends Animal {

    public Cat(String name, int age) {
        super(name, age);
    }

    public void makeSound() {
        System.out.println("Meow! Meow!");
    }
}
```

```agsl
public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Animal[] animals = {
                new Dog("Buddy", 3),
                new Cat("Whiskers", 5),
                new Dog("Max", 2),
                new Cat("Mittens", 4)
        };

        for (Animal animal : animals) {
            printObjectInfo(animal);
            invokeMakeSoundMethod(animal);
            System.out.println();
        }
    }

    private static void printObjectInfo(Object obj) {
        Class<?> clazz = obj.getClass();
        System.out.println("Class: " + clazz.getSimpleName());

        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // Делаем приватное поле доступным, если не доступно напрямую
                if (!field.canAccess(obj)) {
                    field.setAccessible(true);
                }

                // Игнорируем статические поля
                if (!Modifier.isStatic(field.getModifiers())) {
                    try {
                        System.out.println(field.getName() + ": " + field.get(obj));
                    } catch (IllegalAccessException e) {
                        System.err.println("Error accessing field " + field.getName() + ": " + e.getMessage());
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    private static void invokeMakeSoundMethod(Object obj) {
        Class<?> clazz = obj.getClass();
        try {
            Method makeSoundMethod = clazz.getMethod("makeSound");
            makeSoundMethod.invoke(obj);
        } catch (NoSuchMethodException e) {
            System.err.println("Method makeSound not found in class " + clazz.getSimpleName());
        } catch (Exception e) {
            System.err.println("Error invoking makeSound method in class " + clazz.getSimpleName() + ": " + e.getMessage());
        }
    }
}
```
### Дополнительная задача:

- Доработайте метод генерации запроса на удаление объекта из таблицы БД (DELETE FROM <Table> WHERE ID = '<id>')
- В классе QueryBuilder который мы разработали на семинаре.
```agsl
public String buildDeleteQuery(Class<?> clazz, UUID primaryKey) {
        StringBuilder query = new StringBuilder("DELETE FROM ");

        if (clazz.isAnnotationPresent(org.sakaevrs.sem.sem2.task3.Table.class)) {
            org.sakaevrs.sem.sem2.task3.Table tableAnnotation = clazz.getAnnotation(org.sakaevrs.sem.sem2.task3.Table.class);
            query.append(tableAnnotation.name()).append(" WHERE ");

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation.primaryKey()) {
                        query.append(columnAnnotation.name()).append(" = '").append(primaryKey).append("'");
                        break;
                    }
                }
            }

            return query.toString();
        } else {
            return null;
        }
    }
```

```agsl
public static void main(String[] args) throws IllegalAccessException {
        Employee user = new Employee("Stanislav", "sample@gmail.com");
        UUID pk = UUID.randomUUID();
        user.setId(pk);

        org.sakaevrs.sem.sem2.task3.QueryBuilder queryBuilder = new org.sakaevrs.sem.sem2.task3.QueryBuilder();
        String insertQuery = queryBuilder.buildInsertQuery(user);
        System.out.printf("Insert Query: %s\n", insertQuery);

        String selectQuery = queryBuilder.buildSelectQuery(Employee.class, pk);
        System.out.printf("Select Query: %s\n", selectQuery);

        String updateQuery = queryBuilder.buildUpdateQuery(user);
        System.out.printf("Update Query: %s\n", updateQuery);

        String deleteQuery = queryBuilder.buildDeleteQuery(Employee.class, pk);
        System.out.printf("Delete Query: %s\n", deleteQuery);
    }
```