package ru.radion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.radion.database.entity.Car;
import ru.radion.database.entity.User;
import ru.radion.database.repository.CarRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public void save(String brand, String model, User user) {
        carRepository.save(new Car(UUID.randomUUID(), brand, model, user));
    }

    public List<Car> findAAll() {
        return carRepository.findAll();
    }
}
