package models;

public enum EmployeeType {
    WORKER("Рабочий"), CONTROLLER("Контролер"), WAREHOUSE_WORKER("Работник склада");
    private String type;

    EmployeeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
