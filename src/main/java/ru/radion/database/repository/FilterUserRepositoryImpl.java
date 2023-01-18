package ru.radion.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.radion.database.entity.Role;
import ru.radion.database.entity.User;
import ru.radion.dto.PersonalInfo;
import ru.radion.dto.UserFilter;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository{
    private final EntityManager em;
    private final JdbcTemplate jdbcTemplate;
    public static final String FIND_BY_COMPANY_AND_ROLE = "SELECT firstname, lastname, birth_date " +
            "FROM users as u " +
            "WHERE company_id = ? AND role = ?";
    @Override
    public List<User> findAllByFilter(UserFilter filter) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> user = criteria.from(User.class);
        criteria.select(user);

        List<Predicate> predicates = new ArrayList<>();
        if (filter.getFirstname() != null) {
            predicates.add(cb.like(user.get("firstname"), filter.getFirstname()));
        }
        if (filter.getLastname() != null) {
            predicates.add(cb.like(user.get("lastname"), filter.getLastname()));
        }
        if (filter.getBirthDate() != null) {
            predicates.add(cb.lessThan(user.get("birthDate"), filter.getBirthDate()));
        }

        criteria.where(predicates.toArray(Predicate[]::new));

        return em.createQuery(criteria).getResultList();
    }

    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {
        return jdbcTemplate.query(FIND_BY_COMPANY_AND_ROLE, (rs, rowNum) -> new PersonalInfo(
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getDate("birth_date").toLocalDate()
        ), companyId, role.name());
    }
}
