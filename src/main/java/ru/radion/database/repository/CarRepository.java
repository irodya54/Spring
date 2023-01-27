package ru.radion.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.radion.database.entity.Car;

import java.util.List;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID>, JpaSpecificationExecutor<Car> {
//    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO car (id, brand, model, user_id) VALUES (gen_random_uuid(), :brand, :model, 1)", nativeQuery = true)
    void saveCar(@Param("brand") String brand, @Param("model") String model);

    List<Car> findAll();
}
