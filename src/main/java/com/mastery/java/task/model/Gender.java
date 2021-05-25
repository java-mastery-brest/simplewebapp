package com.mastery.java.task.model;

public enum Gender {
    MALE(1),
    FEMALE(2);

    private int id;

    Gender(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Gender genderById(int id) {
        return id == 1 ? MALE : FEMALE;
    }
}
