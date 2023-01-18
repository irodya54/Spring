package ru.radion.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.radion.database.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query(name = "Company.findByName")
    Optional<Company> findByName(@Param("name2") String name);
    List<Company> findAllByNameContainingIgnoreCase(String fragment);

}
