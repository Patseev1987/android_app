package models;




public class NoteToolAndAmountV2 {

    private Long id;

    private EmployeeV2 employeesId;

    private ToolV2 toolsId;

    private Integer amount;


    public NoteToolAndAmountV2(EmployeeV2 employeesId, ToolV2 toolsId, Integer amount) {
        this.employeesId = employeesId;
        this.toolsId = toolsId;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Сотрудник: %s\nФ.И.О.: %s %s %s\nИнструмент: %s - %s\n" +
                "числится штук: %d",employeesId.getType().getType(),
                employeesId.getLastname(),employeesId.getName(),employeesId.getPatronymic(),
                toolsId.getIdCode(),toolsId.getName(),amount);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeV2 getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(EmployeeV2 employeesId) {
        this.employeesId = employeesId;
    }

    public ToolV2 getToolsId() {
        return toolsId;
    }

    public void setToolsId(ToolV2 toolsId) {
        this.toolsId = toolsId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public NoteToolAndAmountV2(Long id, EmployeeV2 employeesId, ToolV2 toolsId, Integer amount) {
        this.id = id;
        this.employeesId = employeesId;
        this.toolsId = toolsId;
        this.amount = amount;
    }
}
