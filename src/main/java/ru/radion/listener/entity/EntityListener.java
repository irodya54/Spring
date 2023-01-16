package ru.radion.listener.entity;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class EntityListener {

    @EventListener
    @Order(10)
    public void acceptEntity(EntityEvent entityEvent) {
        if (entityEvent.getType().equals(AccessType.READ)) {

            System.out.println("Entity: " + entityEvent);
        }
    }
}
