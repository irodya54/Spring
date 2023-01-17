package ru.radion.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import ru.radion.database.connectionPool.ConnectionPool;
import ru.radion.database.repository.UserRepository;
import ru.web.ConfigurationWeb;

//@ImportResource("classpath:application.xml")
@Import(ConfigurationWeb.class)
@Configuration(proxyBeanMethods = true)
public class ApplicationConfiguration {
    @Bean
    public String getDriver() {
        return "PostgresDriver";
    }
    @Bean(value = "pool2")
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool pool2 (@Value("${db.username}") String username,
                                 @Value("${db.pool.size}") Integer poolSize) {
        return new ConnectionPool(username, poolSize);
    }
    @Bean
    public ConnectionPool pool3 () {
        return new ConnectionPool("test-username", 25);
    }

    @Bean
    @Profile("prod||dev")
    public UserRepository userRepository2 (ConnectionPool pool2) {
        return new UserRepository(pool2);
    }

    @Bean
    public UserRepository userRepository3 () {
        ConnectionPool connectionPool = pool3();
        ConnectionPool connectionPool1 = pool3();
        ConnectionPool connectionPool2 = pool3();
        return new UserRepository(pool3());
    }

}
