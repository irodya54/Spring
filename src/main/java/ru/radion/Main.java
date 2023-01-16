package ru.radion;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.radion.config.ApplicationConfiguration;
import ru.radion.database.connectionPool.ConnectionPool;
import ru.radion.database.entity.Company;
import ru.radion.database.repository.CrudRepository;
import ru.radion.service.CompanyService;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext()) {
            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("web", "prod");
            context.refresh();

            CompanyService service = context.getBean("companyService", CompanyService.class);
            System.out.println(service.findById(1));
        }


    }
}