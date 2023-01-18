package ru.radion.database.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import ru.radion.database.entity.Role;
import ru.radion.database.entity.User;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findBy(String firstname, String lastname);

    @Query(nativeQuery = true,
        value = "SELECT u.* FROM users u WHERE u.birth_date BETWEEN :to AND :from")
    List<User> findByBirthDate(LocalDate to, LocalDate from);

    @Modifying(clearAutomatically = true)
    @Query("update User as u set u.role = :role where u.id in :ids")
    int updateRole(Role role, Long... ids);

    Optional<User> findTopByOrderByFirstnameDesc();

    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "50"))
    @Lock(LockModeType.PESSIMISTIC_READ)
    List<User> findTop3ByOrderByBirthDate();

    @EntityGraph(attributePaths = {"company", "company.locales"})
    List<User> findTop3OrOrderBy (Sort sort);

    @EntityGraph(value = "User.company.locales")
    Page<User> findAllBy(Pageable pageable);
}
