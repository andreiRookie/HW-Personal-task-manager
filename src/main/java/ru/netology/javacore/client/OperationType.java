package ru.netology.javacore.client;

public enum OperationType {
    Add("ADD"),
    Remove("REMOVE");

    private final String name;
    private OperationType(final String name) {
        this.name = name;
    }

    public static OperationType fromNumber(final int number) {
        switch (number) {
            case 1:
                return OperationType.Add;
            case 2:
                return OperationType.Remove;
            default:
                throw new IllegalArgumentException("unknown operation");
        }
    }

    public String getName() {
        return name;
    }
}
