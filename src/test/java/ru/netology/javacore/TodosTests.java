package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class TodosTests {
    private Todos todos;
    private final String TEST_TASK_1 = "TEST_TASK_1";
    private final String TEST_TASK_2 = "TEST_TASK_2";
    private final String TEST_TASK_3 = "TEST_TASK_3";
    private final String TEST_TASK_4 = "TEST_TASK_4";
    private final String TEST_TASK_5 = "TEST_TASK_5";
    private final String TEST_TASK_6 = "TEST_TASK_6";
    private final String TEST_TASK_7 = "TEST_TASK_7";
    private final String TEST_TASK_8 = "TEST_TASK_8";


    @BeforeEach
    void init() {
        todos = new Todos();
        todos.addTask(TEST_TASK_1);
        todos.addTask(TEST_TASK_2);
        todos.addTask(TEST_TASK_3);
        todos.addTask(TEST_TASK_4);
        todos.addTask(TEST_TASK_5);
        todos.addTask(TEST_TASK_6);
        todos.addTask(TEST_TASK_7);
    }

    @Test
    void addTask() {
        HashSet<String> expected = getAllTestTasksSet();
        HashSet<String> actual = todos.getTasks();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addTask_whenMaxCapacityReachedTaskShouldNotBeAdded() {
        todos.addTask(TEST_TASK_8);

        HashSet<String> expected = getAllTestTasksSet();
        HashSet<String> actual = todos.getTasks();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void removeTask() {
        String taskToRemove = TEST_TASK_7;

        HashSet<String> expected = getAllTestTasksSet();
        expected.remove(taskToRemove);

        todos.removeTask(taskToRemove);
        HashSet<String> actual = todos.getTasks();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    void restoreTask() {

        HashSet<String> expected = getAllTestTasksSet();

        todos.removeTask(TEST_TASK_7);
        todos.restoreTask();
        HashSet<String> actual = todos.getTasks();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void restoreTask_notEverGreen() {

        HashSet<String> expected = getAllTestTasksSet();

        todos.removeTask(TEST_TASK_7);
        todos.removeTask(TEST_TASK_4);
        todos.restoreTask();
        HashSet<String> actual = todos.getTasks();

        Assertions.assertEquals(expected, actual);
    }

    private HashSet<String> getAllTestTasksSet() {
        HashSet<String> set = new HashSet<>();
        set.add(TEST_TASK_1);
        set.add(TEST_TASK_2);
        set.add(TEST_TASK_3);
        set.add(TEST_TASK_4);
        set.add(TEST_TASK_5);
        set.add(TEST_TASK_6);
        set.add(TEST_TASK_7);

        return set;
    }
}
