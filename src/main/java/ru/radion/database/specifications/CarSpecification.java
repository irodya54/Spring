package ru.radion.database.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.radion.database.entity.Car;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CarSpecification{

    public static Specification<Car> getByBrandAndModel(String brand, String model) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Path brandExpression = root.get("brand");
            Path modelExpression = root.get("model");

            predicates.add(criteriaBuilder.like(brandExpression, "%"  + brand + "%"));
            predicates.add(criteriaBuilder.like(modelExpression, "%" + model + "%"));

            if (predicates.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
