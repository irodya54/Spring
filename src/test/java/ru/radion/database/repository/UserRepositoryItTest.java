package ru.radion.database.repository;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.TypeCache;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;
import ru.radion.IntegrationTestBase;
import ru.radion.annotation.IT;
import ru.radion.database.entity.Role;
import ru.radion.database.entity.User;
import ru.radion.dto.PersonalInfo;
import ru.radion.dto.PersonalInfo2;
import ru.radion.dto.UserFilter;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@RequiredArgsConstructor
public class UserRepositoryItTest extends IntegrationTestBase {

    private final UserRepository userRepository;

    @Test
    void testJdbcTemplate() {
        List<PersonalInfo> users = userRepository.findAllByCompanyIdAndRole(1, Role.USER);
        assertThat(users).hasSize(1);
        System.out.println();
    }

    @Test
    void checkAuditing() {
        User user = userRepository.findById(1L).get();
        user.setBirthDate(user.getBirthDate().plusYears(1L));
        userRepository.flush();
        System.out.println();
    }

    @Test
    void checkUserRepositoryImplementation() {
        UserFilter filter = new UserFilter("Ivan", "Ivanov", LocalDate.of(2000, 1, 10));
        List<User> userByFilter = userRepository.findAllByFilter(filter);
        assertThat(userByFilter).hasSize(1);
    }


    @Test
    void checkProjections2() {
        Optional<PersonalInfo> all = userRepository.findTopByFirstname("Ivan", PersonalInfo.class);
        System.out.println();
    }
    @Test
    void checkProjections() {
        List<PersonalInfo2> allByCompanyId = userRepository.findAllByCompanyId(1);
        System.out.println(allByCompanyId);
    }

    @Test
    void checkPageable() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("firstname"));
        Page<User> page = userRepository.findAllBy(pageable);
        System.out.println(page);
        page.forEach(user -> System.out.println(user.getCompany().getName()));


        while (page.hasNext()) {
            page = userRepository.findAllBy(page.nextPageable());
            page.forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }
    @Test
    void testSort() {
        Sort.TypedSort<User> sortby = Sort.sort(User.class);
        Sort sort = sortby.by(User::getFirstname).and(sortby.by(User::getLastname));

        List<User> top3ByOrderBy = userRepository.findTop3OrOrderBy(sort.descending());
        assertThat(top3ByOrderBy).hasSize(3);
    }

    @Test
    void findTop3ByOrderByBirthDate() {
        List<User> top3ByOrderByBirthDate = userRepository.findTop3ByOrderByBirthDate();
        assertThat(top3ByOrderByBirthDate).hasSize(3);
    }

    @Test
    void checkFindTop3User() {
        Optional<User> user = userRepository.findTopByOrderByFirstnameDesc();
        assertTrue(user.isPresent());
        Optional<User> byId = userRepository.findById(4L);
        assertTrue(byId.isPresent());
        User user1 = byId.get();
        user.ifPresent(userTop -> assertEquals(userTop,user1));
    }

    @Test
    void checkUpdateUserRole() {
        int count = userRepository.updateRole(Role.USER, 1L, 5L);
        assertSame(2, count);
    }

    @Test
    void checkFindByUsernameAndLastname() {
        List<User> users = userRepository.findBy("a", "ov");
        Optional<User> userById1 = userRepository.findById(1L);
        assertThat(userById1).isPresent();
        assertThat(users).hasSize(3);
        assertThat(users).contains(userById1.get());
    }

    @Test
    void checkFindByUsername() {
        List<User> byBirthDate = userRepository.findByBirthDate(LocalDate.of(1990, 1, 1),
                LocalDate.of(2000, 1, 1));
        System.out.println();
        assertFalse(byBirthDate.isEmpty());
        assertThat(byBirthDate).hasSize(2);

        Optional<User> userById1 = userRepository.findById(1L);
        assertTrue(userById1.isPresent());

        assertThat(byBirthDate).contains(userById1.get());
    }
}
