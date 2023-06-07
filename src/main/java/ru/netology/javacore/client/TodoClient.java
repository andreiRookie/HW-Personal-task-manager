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
        OperationType operationType;
        Request request;

        System.out.println("Enter operation number: 1 - to add task; "
                + "2 - to remove task; 3 - to restore removed task");

        typeInput = Integer.parseInt(scanner.nextLine());
        operationType = OperationType.fromNumber(typeInput);

        switch (operationType) {
            case Add:
            case Remove: {
                System.out.println("Enter task to add/remove:");
                taskInput = scanner.nextLine();

                request = new Request(operationType.getName(), taskInput);
                break;
            }
            case Restore: {
                request = new  Request(operationType.getName());
                break;
            }
            default: throw new IllegalArgumentException("unknown operation");
        }

        String json = gson.toJson(request);

        try (Socket socket = new Socket(host, port);
             PrintWriter outWriter = new PrintWriter(socket.getOutputStream());
             BufferedReader inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {

            outWriter.println(json);
            outWriter.flush();

            String response = inReader.readLine();
            System.out.println(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
