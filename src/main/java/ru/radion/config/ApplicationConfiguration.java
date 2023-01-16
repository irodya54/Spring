package ru.radion.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import ru.radion.database.connectionPool.ConnectionPool;
import ru.radion.database.repository.CrudRepository;
import ru.radion.database.repository.UserRepository;
import ru.web.ConfigurationWeb;

//@ImportResource("classpath:application.xml")
@Import(ConfigurationWeb.class)
@Configuration(proxyBeanMethods = true)
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.radion",
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Component.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "ru\\..+Repository")

        }
)
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
    @Profile("prod||web")
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
