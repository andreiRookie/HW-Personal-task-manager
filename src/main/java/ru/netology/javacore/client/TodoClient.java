package ru.netology.javacore.client;

import com.google.gson.Gson;
import ru.netology.javacore.Request;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TodoClient {

    private final int port;
    private final String host;

    public TodoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in);

        Gson gson = new Gson();
        int typeInput;
        String taskInput;

        System.out.println("Enter operation number: 1 - to add task; 2 - to remove task");
        typeInput = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter new task:");
        taskInput = scanner.nextLine();

        try (Socket socket = new Socket(host, port);
             PrintWriter outWriter = new PrintWriter(socket.getOutputStream());
             BufferedReader inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            OperationType operationType = OperationType.fromNumber(typeInput);

            Request request = new Request(operationType.getName(), taskInput);

            String json = gson.toJson(request);
            System.out.println("Request json: \n" + json);

            outWriter.println(json);
            outWriter.flush();

            String response = inReader.readLine();
            System.out.println(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
