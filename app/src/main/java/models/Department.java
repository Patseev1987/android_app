package models;

public enum Department {
    DEPARTMENT_19("Цех 19"),DEPARTMENT_20("Цех 20"),DEPARTMENT_23("Цех 23"),
    DEPARTMENT_26("Цех 26"),DEPARTMENT_43("Цех 43"),WAREHOUSE("Склад");

    private String  name;
    Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
