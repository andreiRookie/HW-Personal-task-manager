package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {

    private static final int MAX_CAPACITY = 7;
    private final HashSet<String> tasks = new HashSet<>();

    public void addTask(String task) {
        if (tasks.size() < MAX_CAPACITY) {
            tasks.add(task);
        }
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

    public String getAllTasks() {
        List<String> sortedTasks = tasks.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        sortedTasks.forEach(task -> sb.append("\"").append(task).append("\"").append(" "));
        return sb.toString();
    }
}
