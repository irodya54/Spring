package ru.radion.database.connectionPool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
@Component(value = "pool1")
public class ConnectionPool {
    private final String username;
    private final Integer poolsize;

    public ConnectionPool(@Value("${db.username}") String username,
                          @Value("${db.pool.size}") Integer poolsize) {
        this.username = username;
        this.poolsize = poolsize;
    }

//    @PostConstruct
//    public void init() {
//        System.out.println("Initial pool size");
//    }

//    @PreDestroy
//    public void destroy() {
//        System.out.println("Close pool size");
//    }
}
