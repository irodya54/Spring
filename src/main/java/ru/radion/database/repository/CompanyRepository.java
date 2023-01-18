package ru.radion.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import ru.radion.database.entity.Company;
import ru.radion.database.entity.Role;
import ru.radion.database.entity.User;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query(name = "Company.findByName")
    Optional<Company> findByName(@Param("name2") String name);
    List<Company> findAllByNameContainingIgnoreCase(String fragment);

}
