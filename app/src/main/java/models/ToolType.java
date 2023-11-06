package models;

public enum ToolType {
    INNER("Сменная пластина"),HOLDER("Державка для пластин"),
    MILLING_CUTTER("Фреза"),MEASURER("Мерительный инструмент"),
    TOOL_FOR_GEAR("Интрумент ддля зубообработки"), CUTTER("Резец");
    private String type;

    ToolType(String name) {
        this.type = name;
    }

    public String getType() {
        return type;
    }
}
