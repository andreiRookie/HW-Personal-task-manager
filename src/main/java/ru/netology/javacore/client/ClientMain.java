package ru.netology.javacore.client;

import java.io.IOException;

public class ClientMain {
    public static final String HOST= "127.0.0.1";
    public static final int PORT = 8989;

    public static void main(String[] args) throws IOException {
        TodoClient client = new TodoClient(HOST, PORT);
        client.start();
    }
}
