package ru.radion;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.radion.config.ApplicationConfiguration;
import ru.radion.database.connectionPool.ConnectionPool;
import ru.radion.database.entity.Company;
import ru.radion.database.repository.CrudRepository;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext()) {
            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("web", "prod");
            context.refresh();
            ConnectionPool pool1 = context.getBean("pool1", ConnectionPool.class);
            CrudRepository<Integer, Company> repository = context.getBean("companyRepository", CrudRepository.class);
            repository.findById(1);
        }


    }
}