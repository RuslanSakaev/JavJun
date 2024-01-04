# Java Junior (семинары)
## Урок 5. Клиент/Сервер своими руками
1. Разработайте простой чат на основе сокетов как это было показано 
на самом семинаре. Ваше приложение должно включать в себя сервер, 
который принимает сообщения от клиентов и пересылает их всем 
участникам чата. (Вы можете просто переписать наше приложение 
с семинара, этого будет вполне достаточно)
2. *Подумайте, как организовать отправку ЛИЧНЫХ сообщений 
в контексте нашего чата, доработайте поддержку отправки личных 
сообщений, небольшую подсказку я дал в конце семинара.

# Решение:
Для реализации возможности отправки личных сообщений в чате, 
нужно внести изменения в обработку сообщений как на стороне сервера, 
так и на стороне клиента. Для этого определим следующий формат: 
- @username: message, 
- где username - это имя пользователя, которому предназначается сообщение, 
- а message - само сообщение

Изменим метод __broadcastMessage__ в классе __ClientManager__, добавим проверки, 
является ли сообщение личным, и если да, отправим его только указанному пользователю.
Для удобства добавим отправку инструкции клиенту __sendWelcomeMessage__.
```java
private void sendWelcomeMessage() {
    String welcomeMessage = "Добро пожаловать в чат, " + this.name + "!\n" +
                            "Чтобы отправить личное сообщение, используйте формат '@username: message'.\n" +
                            "Пример: '@alex: Привет, как дела?'.";
    try {
        this.bufferedWriter.write(welcomeMessage);
        this.bufferedWriter.newLine();
        this.bufferedWriter.flush();
    } catch (IOException e) {
        closeEverything(this.socket, this.bufferedReader, this.bufferedWriter);
    }
}
```
После запуска приложения, в чате видим приветственное сообщение и возможность отправки личных сообщений:
![1](D:\workplace\JavJun\src\main\java\org\sakaevrs\hw\hw5\img\1.bmp)

Таким образом у нас получилась следующая реализация метода __broadcastMessage__:
```java
private void broadcastMessage(String message) {
        String[] parts = message.split(" ");
        if (parts.length > 1 && parts[1].charAt(0) == '@' &&
                clients.stream().anyMatch(client -> client.name.equals(parts[1].substring(1)))) {
            var cln = clients.stream().filter(client -> client.name.equals(parts[1].substring(1))).findFirst();
            if (cln.isPresent()) {
                parts[1] = null;
                String newMessage = Arrays.stream(parts)
                        .filter(s -> s != null && !s.isEmpty())
                        .collect(Collectors.joining(" "));
                try {
                    cln.get().bufferedWriter.write(newMessage);
                    cln.get().bufferedWriter.newLine();
                    cln.get().bufferedWriter.flush();
                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        } else {
            for (ClientManager client : clients) {
                try {
                    // Если клиент не равен по наименованию клиенту-отправителю,
                    // отправим сообщение
                    if (!client.name.equals(name) && message != null) {
                        client.bufferedWriter.write(message);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    }
                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }
    }
```
Метод __broadcastMessage__ в классе ClientManager выполняет следующие действия:

1. Сначала метод разбивает входящее сообщение на части с использованием пробела в качестве разделителя.
2. Если сообщение состоит более чем из одной части, и вторая часть начинается с символа __@__, 
метод проверяет, существует ли клиент с именем, указанным после @. Это делается путем фильтрации списка clients.
Если такой клиент найден __cln.isPresent()__ возвращает __true__, метод рассматривает сообщение как личное и 
предназначенное исключительно для этого клиента.
3. Если сообщение является личным, метод удаляет имя получателя из сообщения, устанавливая __parts[1] = null__, 
затем собирает оставшиеся части обратно в строку и отправляет её указанному клиенту.
Если в процессе возникает __IOException__, метод вызывает closeEverything(...), чтобы закрыть соединение и очистить ресурсы.
4. Если сообщение не является личным (то есть не начинается с __@username__), метод перебирает всех клиентов в 
списке clients и отправляет им сообщение.
При этом сообщение не отправляется клиенту-отправителю __(!client.name.equals(name))__.
Аналогично, при возникновении __IOException__ вызывается __closeEverything(...)__.