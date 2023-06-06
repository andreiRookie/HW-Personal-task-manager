package ru.netology.javacore;

import com.google.gson.Gson;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private final int port;
    private final Todos todos;
    private static final String ADD = "ADD";
    private static final String REMOVE = "REMOVE";

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");

        Gson gson = new Gson();
        String json, operationType;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                try (Socket socket = serverSocket.accept();
                     PrintWriter outWriter = new PrintWriter(socket.getOutputStream());
                     BufferedReader inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
                ) {

                    json = inReader.readLine();
                    System.out.println("json " + json);
                    Request request = gson.fromJson(json, Request.class);
                    System.out.println(request);

                    operationType = request.getType();

                    switch (operationType) {
                        case ADD: {
                            todos.addTask(request.getTask());
                            break;
                        }
                        case REMOVE: {
                            todos.removeTask(request.getTask());
                            break;
                        }
                        default:
                            throw new IllegalArgumentException("unknown operation");
                    }

                    String response = todos.getAllTasks();
                    System.out.println("Todos: " + response);

                    outWriter.println(response);
                    outWriter.flush();

                } catch (IOException | IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot start server");
            e.printStackTrace();
        }
    }
}
