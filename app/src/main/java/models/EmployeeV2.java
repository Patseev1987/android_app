package models;


import java.time.LocalDate;


public class EmployeeV2 {

    private Integer id;

    private String name;

    private String lastname;

    private String patronymic;

    private LocalDate joinDate;


    private Department department;

    private EmployeeType type;

    public EmployeeV2(String name, String lastname, String patronymic, LocalDate joinDate, Department department, EmployeeType type) {
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.joinDate = joinDate;
        this.department = department;
        this.type = type;
    }


    @Override
    public String toString() {
        return String.format("Сотрудник: %s %s %s\nПодразделение: %s\nДолжность: %s\nДата начала работы: %s"
        ,lastname,name,patronymic,department.getName(),type.getType(),joinDate.toString());
    }

    public EmployeeV2(Integer id, String name, String lastname, String patronymic, LocalDate joinDate, Department department, EmployeeType type) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.joinDate = joinDate;
        this.department = department;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }
}
