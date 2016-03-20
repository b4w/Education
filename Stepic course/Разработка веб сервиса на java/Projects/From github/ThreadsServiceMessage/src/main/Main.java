package main;

public class Main {
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer(5050);
        chatServer.start();
    }
}
