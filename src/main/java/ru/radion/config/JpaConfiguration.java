package ru.radion.config;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import ru.radion.config.condition.JpaConditional;

import javax.annotation.PostConstruct;

@Conditional(JpaConditional.class)
@Configuration
public class JpaConfiguration {
@PostConstruct
    public void init() {
    System.out.println("Init JPA configuration");
}

}
