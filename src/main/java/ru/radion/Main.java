package ru.radion;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.radion.database.connectionPool.ConnectionPool;
import ru.radion.database.ioc.Container;
import ru.radion.database.repository.CompanyRepository;
import ru.radion.service.UserService;

public class Main {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml")) {
            ConnectionPool pool1 = context.getBean("pool1", ConnectionPool.class);
            CompanyRepository repository = context.getBean("companyRepository", CompanyRepository.class);
            System.out.println();
        }


    }
}