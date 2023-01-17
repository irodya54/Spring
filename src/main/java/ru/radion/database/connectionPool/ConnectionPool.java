package ru.radion.database.connectionPool;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component(value = "pool1")
//@Component
@RequiredArgsConstructor
@Slf4j
public class ConnectionPool {
    @Value("${db.username}")
    private final String username;
    @Value("${db.pool.size}")
    private final Integer poolsize;

    @PostConstruct
    public void init() {
        log.info("Initial pool size");
    }

    @PreDestroy
    public void destroy() {
        log.info("Closing pool size");
    }
}
