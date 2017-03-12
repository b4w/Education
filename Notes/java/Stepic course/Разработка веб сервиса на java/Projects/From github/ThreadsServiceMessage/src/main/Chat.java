package main;

import java.io.*;
import java.net.Socket;

public class Chat implements Runnable {

    Socket socket;
    ChatServer chatServer;

    public Chat(Socket socket, ChatServer chatServer) {
        this.socket = socket;
        this.chatServer = chatServer;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = in.readLine();
            if (!message.equals("Bue")) {
                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                out.write(message);
                out.close();
            } else {
                in.close();
                chatServer.close();
            }
        } catch (IOException e) {
            System.out.println("Chat error! " + e.getMessage());
        }
    }
}
