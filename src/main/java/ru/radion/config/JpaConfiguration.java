package ru.radion.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import ru.radion.config.condition.JpaConditional;

import javax.annotation.PostConstruct;

@Conditional(JpaConditional.class)
@Configuration
@Slf4j
public class JpaConfiguration {

    @PostConstruct
    public void init() {
        log.info("Init JPA configuration");
    }

}
