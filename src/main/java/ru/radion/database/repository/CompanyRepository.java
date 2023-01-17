package ru.radion.database.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.radion.annotacions.Auditing;
import ru.radion.annotacions.InjectBeant;
import ru.radion.annotacions.Transaction;
import ru.radion.database.connectionPool.ConnectionPool;
import ru.radion.database.entity.Company;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
@Transaction
@Auditing
@Repository
@RequiredArgsConstructor
@Slf4j
public class CompanyRepository implements CrudRepository<Integer, Company>{
    private final ConnectionPool pool1;
    @Value("${db.pool.size}")
    private final Integer poolSize;
    private final List<ConnectionPool> pools;


    @Override
    public Optional<Company> findById(Integer id) {
        log.info("find by id method");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        log.info("delete method");
    }

    @PostConstruct
    private void init() {
       log.warn("Init Company Repository");
    }
}
