package ru.radion.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import ru.radion.IntegrationTestBase;
import ru.radion.annotation.IT;
import ru.radion.database.entity.Car;
import ru.radion.database.repository.CarRepository;
import ru.radion.database.specifications.CarSpecification;
import ru.radion.dto.UserReadDto;
import ru.radion.service.CarService;
import ru.radion.service.UserService;
import ru.radion.utils.mapper.UserMapper;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
public class CarServiceTest extends IntegrationTestBase {

   private final CarService carService;
   private final CarRepository carRepository;
   private final UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Test
    void saveCar() {
        String brand = "Skoda";
        String model = "Octavia";
        Optional<UserReadDto> byId = userService.findById(1L);
        carService.save(brand, model, byId.map(userMapper::userReadDtoToUser).get());
        List<Car> aAll = carService.findAAll();
        System.out.println();

    }

    @Test
    void likeBrand() {
        List<Car> sk = carRepository.findAll(CarSpecification.getByBrandAndModel("ad", ""));
        System.out.println();
        assertFalse(sk.isEmpty());
    }
}
