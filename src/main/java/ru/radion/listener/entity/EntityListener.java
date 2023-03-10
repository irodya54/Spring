package ru.radion.listener.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EntityListener {

    @EventListener
    @Order(10)
    public void acceptEntity(EntityEvent entityEvent) {
        if (entityEvent.getType().equals(AccessType.READ)) {

            log.warn("Entity: " + entityEvent.getSource().toString());
        }
    }
}
