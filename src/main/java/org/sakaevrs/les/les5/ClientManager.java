package org.sakaevrs.les.les5;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable{
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    public static ArrayList<ClientManager> client = new ArrayList<>();

    public ClientManager(Socket socket) {
        try {
            this.socket = socket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            client.add(this);
            broadcastMessage("Server: " + name + " подключился к чату.");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void run() {
        String messageFromClient;

        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                if (messageFromClient.startsWith("TYPING:") || messageFromClient.startsWith("STOPPED_TYPING:")) {
                    broadcastTypingStatus(messageFromClient);
                } else {
                    broadcastMessage(messageFromClient);
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    private void broadcastTypingStatus(String statusMessage) {
        for (ClientManager clientManager : client) {
            try {
                if (!clientManager.name.equals(name)) {
                    clientManager.bufferedWriter.write(statusMessage);
                    clientManager.bufferedWriter.newLine();
                    clientManager.bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    private void broadcastMessage(String messageToSend) {
        for (ClientManager clientManager : client) {
            try {
                if (!clientManager.name.equals(name)) {
                    clientManager.bufferedWriter.write(messageToSend);
                    clientManager.bufferedWriter.newLine();
                    clientManager.bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClient();
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

    public void removeClient(){
        client.remove(this);
        broadcastMessage("SERVER: " + name + " покинул чат.");
    }

}
