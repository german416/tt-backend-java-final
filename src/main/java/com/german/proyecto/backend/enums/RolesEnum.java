package com.german.proyecto.backend.enums;

public enum RolesEnum {
    ANONYMOUS(1, "Anonymous"),
    CUSTOMER(2, "Customer"),
    MANAGER(3, "Manager"),
    SYSTEM(4, "System"),
    ADMINISTRATOR(5, "Administrator");

    private final Integer id;
    private final String description;

    RolesEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return this.description;
    }
}
