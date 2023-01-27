package ru.radion.database.specifications;

import org.hibernate.metamodel.model.domain.internal.SingularAttributeImpl;
import org.springframework.data.jpa.domain.Specification;
import ru.radion.database.entity.Car;
import ru.radion.database.entity.User;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.Bindable;
import javax.persistence.metamodel.SingularAttribute;
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

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Car> getAllByUsername (String username) {
        return (root, query, cb) -> {

            Join<Car, User> users = root.join("users");
            return cb.equal(users.get("user_id").get("username"), username);
        };
    }
}
