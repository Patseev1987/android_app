package models;



public class ToolV2 {

    private String idCode;

    private String name;

    private String manufactured;

    private boolean isSharpen;

    private boolean isGood;

    ToolType type;

private String getInfoAboutSharpen(){
    if (isSharpen){
        return "Инструмент заточен";
    } else{
        return "Инструмент не заточен";
    }
}
    private String getInfoAboutState() {
        if(isGood){
            return "Инструмент в рабочен состоянии";
        }else {
            return "Инструмент пора списывать";
        }
    }


    @Override
    public String toString() {
        return String.format("Инструмент: %s \nТип инструмента: %s\nНазвание : %s\nПроизводитель: %s\n" +
                "Состояние заточки инструмента: %s\nСостояние инструмента: %s",idCode,type.getType(),name,manufactured
        ,getInfoAboutSharpen(),getInfoAboutState());
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufactured() {
        return manufactured;
    }

    public void setManufactured(String manufactured) {
        this.manufactured = manufactured;
    }

    public boolean isSharpen() {
        return isSharpen;
    }

    public void setSharpen(boolean sharpen) {
        isSharpen = sharpen;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }

    public ToolType getType() {
        return type;
    }

    public void setType(ToolType type) {
        this.type = type;
    }


    public ToolV2(String idCode, String name, String manufactured, boolean isSharpen, boolean isGood, ToolType type) {
        this.idCode = idCode;
        this.name = name;
        this.manufactured = manufactured;
        this.isSharpen = isSharpen;
        this.isGood = isGood;
        this.type = type;
    }
}
