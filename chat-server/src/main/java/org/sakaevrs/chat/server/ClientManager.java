package org.sakaevrs.chat.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class ClientManager implements Runnable {
    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    public static final List<ClientManager> clients = Collections.synchronizedList(new ArrayList<>());

    public ClientManager(Socket socket) {
        this.socket = socket;

        try {
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.name = this.bufferedReader.readLine();
            clients.add(this);
            System.out.println(this.name + " подключился к чату.");
            this.broadcastMessage("Server: " + this.name + " подключился к чату.");
            this.sendWelcomeMessage();
        } catch (IOException var3) {
            this.closeEverything(socket, this.bufferedReader, this.bufferedWriter);
        }

    }

    private void sendWelcomeMessage() {
        String welcomeMessage = "Добро пожаловать в чат, " + this.name + "!\n" +
                "Чтобы отправить личное сообщение, используйте формат\n@username message\n" +
                "Пример: @alex Привет, как дела?";
        try {
            this.bufferedWriter.write(welcomeMessage);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        } catch (IOException e) {
            closeEverything(this.socket, this.bufferedReader, this.bufferedWriter);
        }
    }

    public void run() {
        while(true) {
            if (this.socket.isConnected()) {
                try {
                    String messageFromClient = this.bufferedReader.readLine();
                    this.broadcastMessage(messageFromClient);
                    continue;
                } catch (IOException var3) {
                    this.closeEverything(this.socket, this.bufferedReader, this.bufferedWriter);
                }
            }

            return;
        }
    }

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

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        this.removeClient();

        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }

            if (bufferedWriter != null) {
                bufferedWriter.close();
            }

            if (socket != null) {
                socket.close();
            }
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    private void removeClient() {
        clients.remove(this);
        System.out.println(this.name + " покинул чат.");
        this.broadcastMessage("Server: " + this.name + " покинул чат.");
    }
}

