package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {

    private static final int MAX_CAPACITY = 7;
    private final HashSet<String> tasks = new HashSet<>();
    private final Deque<HashSet<String>> states = new ArrayDeque<>();

    public void addTask(String task) {
        states.addLast(new HashSet<>(tasks));
        if (tasks.size() < MAX_CAPACITY) {
            tasks.add(task);
        }
    }

    public void removeTask(String task) {
        states.addLast(new HashSet<>(tasks));
        tasks.remove(task);
    }

    public void restoreTask() {
        HashSet<String> prevState = states.pollLast();
        if (prevState != null){
            tasks.clear();
            tasks.addAll(prevState);
        }
    }

    public String getAllTasks() {
        List<String> sortedTasks = tasks.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        sortedTasks.forEach(task -> sb.append("\"").append(task).append("\"").append(" "));
        return sb.toString();
    }

    public HashSet<String> getTasks() {
        return tasks;
    }
}
