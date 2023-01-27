package ru.radion.database.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import ru.radion.database.entity.Role;
import ru.radion.database.entity.User;
import ru.radion.dto.PersonalInfo2;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends
        JpaRepository<User, Long>,
        FilterUserRepository,
        RevisionRepository<User, Long, Integer>,
        QuerydslPredicateExecutor<User> {
    Optional<User> findByUsernameIgnoreCase(String username);

    @Query("select u from User u where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findBy(String firstname, String lastname);


    @Query(value = "select u.* from users u where u.firstname like :firstname limit 1", nativeQuery = true)
    User findByFirstname(String firstname);

    @Query(nativeQuery = true,
        value = "SELECT u.*, c.*, cl.* " +
                "FROM users u " +
                "join company c on c.id = u.company_id " +
                "join company_locales cl on c.id = cl.company_id " +
                "WHERE u.birth_date BETWEEN :to AND :from")
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

//    List<PersonalInfo> findAllByCompanyId(Integer companyId);

    <T> Optional<T> findTopByFirstname (String firstname, Class<T> clazz);

    @Query(nativeQuery = true,
            value = "SELECT u.firstname, u.lastname, u.birth_date BirthDate FROM users AS u WHERE u.company_id = :companyId")
    List<PersonalInfo2> findAllByCompanyId (Integer companyId);

}
