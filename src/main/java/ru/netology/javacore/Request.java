package ru.netology.javacore;

public class Request {

    private final String type;
    private String task;

    public Request(String type) {
        this.type = type;
    }

    public Request(String type, String task) {
        this.type = type;
        this.task = task;
    }

    public String getType() {
        return type;
    }

    public String getTask() {
        return task;
    }
    @Override
    public String toString() {
        return "Request{" +
                "type='" + type + '\'' +
                ", task='" + task + '\'' +
                '}';
    }
}
