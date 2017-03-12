package main;

import java.io.IOException;
import java.net.ServerSocket;

public class ChatServer {

    private int port;
    private boolean isFinish;

    public ChatServer(int port) {
        this.port = port;
        this.isFinish = false;
    }

    public void start() {
        System.out.println("Server started");
        while (!isFinish) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
//                serverSocket.setSoTimeout(10000);
//                new Thread(new Chat(serverSocket.accept(), this)).start();
                new Chat(serverSocket.accept(), this).run();
            } catch (IOException e) {
                System.out.println("Error in ChatServer! " + e.getMessage());
            }
        }
    }

    public void close() {
        isFinish = true;
    }
}