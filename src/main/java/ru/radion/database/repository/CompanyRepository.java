package ru.radion.database.repository;

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
public class CompanyRepository implements CrudRepository<Integer, Company>{
    private final ConnectionPool pool1;
     private final Integer poolSize;
    private final List<ConnectionPool> pools;

    public CompanyRepository(ConnectionPool pool1,
                             @Value("${db.pool.size}") Integer poolSize,
                             List<ConnectionPool> pools) {
        this.pool1 = pool1;
        this.poolSize = poolSize;
        this.pools = pools;
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("delete by id method");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("find by id method");
    }

    @PostConstruct
    private void init() {
        System.out.println("Init Company Repository");
    }
}
