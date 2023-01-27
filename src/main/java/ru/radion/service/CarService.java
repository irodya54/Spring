package ru.radion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.radion.database.entity.Car;
import ru.radion.database.entity.User;
import ru.radion.database.repository.CarRepository;
import ru.radion.database.specifications.CarSpecification;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarService {
    private final CarRepository carRepository;

    public List<Car> getCarsByUsername(String username) {
        return carRepository.findAll(CarSpecification.getAllByUsername(username));
    }
    public void save(String brand, String model, User user) {
        carRepository.save(new Car(UUID.randomUUID(), brand, model, user));
    }

    public List<Car> findAAll() {
        return carRepository.findAll();
    }
}
