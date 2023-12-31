package org.sakaevrs.les.les5;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private final String name;
    private volatile boolean isTyping = false;

    public Client(Socket socket, String userName) {
        this.socket = socket;
        name = userName;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage(){
        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {
            while (socket.isConnected()) {
                if (scanner.hasNextLine()) {
                    String message = scanner.nextLine();
                    if (isTyping) {
                        try {
                            sendTypingStatus(false);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    try {
                        bufferedWriter.write(name + ": " + message);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        bufferedWriter.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        bufferedWriter.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (socket.isConnected()) {
                try {
                    Thread.sleep(3000); // Проверка каждые 3 секунды
                    if (isTyping) {
                        sendTypingStatus(false); // Отправка статуса "перестал печатать"
                    }
                } catch (InterruptedException | IOException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    private void sendTypingStatus(boolean isTyping) throws IOException {
        this.isTyping = isTyping;
        String statusMessage = isTyping ? "TYPING:" + name : "STOPPED_TYPING:" + name;
        bufferedWriter.write(statusMessage);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromGroup;
                try {
                    while (socket.isConnected()) {
                        messageFromGroup = bufferedReader.readLine();
                        if (messageFromGroup != null) {
                            System.out.println(messageFromGroup);
                        } else {
                            closeEverything(socket, bufferedReader, bufferedWriter);
                            break;
                        }
                    }
                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }).start();
    }


    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите своё имя: ");
        String name = scanner.nextLine();
        Socket socket = new Socket("localhost", 1300);
        Client client = new Client(socket, name);
        client.listenForMessage();
        client.sendMessage();
    }

}
