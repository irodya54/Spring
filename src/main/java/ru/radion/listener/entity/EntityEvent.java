package ru.radion.listener.entity;

import java.util.EventObject;

public class EntityEvent extends EventObject {
    private final AccessType type;

    public EntityEvent(Object source, AccessType type) {
        super(source);
        this.type = type;
    }

    public AccessType getType() {
        return type;
    }

}
