package ru.radion.database.repository;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import ru.radion.IntegrationTestBase;
import ru.radion.annotacions.Transaction;
import ru.radion.annotation.IT;
import ru.radion.database.entity.Company;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
@Transactional
class CompanyRepositoryTest extends IntegrationTestBase {

    private final TransactionTemplate transactionTemplate;
    private final EntityManager em;
    private final CompanyRepository companyRepository;

    @Test
    void checkFindByName() {
        Optional<Company> google = companyRepository.findByName("google");
        assertTrue(google.isPresent());
        google.ifPresent(company -> assertEquals(company.getName(), "Google"));

    }

    @Test
    void checkListFindByNameIgnoreCase() {
        Optional<Company> meta = companyRepository.findByName("Meta");
        List<Company> companies = companyRepository.findAllByNameContainingIgnoreCase("a");
        assertFalse(companies.isEmpty());
        assertThat(companies).hasSize(2);
        assertTrue(meta.isPresent());
        assertThat(companies).contains(meta.get());
    }
    @Test
    void findById() {
        Company company = em.find(Company.class, 1);

        assertNotNull(company);
        assertThat(company.getName()).isEqualTo("Google");
        assertThat(company.getLocales()).hasSize(2);

    }

    @Test
    void saveCompanyTest() {
        transactionTemplate.executeWithoutResult(tx -> {
            Company company = Company.builder()
                    .name("Microsoft")
                    .locales(Map.of(
                            "ru", "???????????????? Microsoft",
                            "en", "Microsoft description"
                    ))
                    .build();
            em.persist(company);
            em.flush();
            Optional<Company> microsoft = companyRepository.findByName("Microsoft");
            assertTrue(microsoft.isPresent());
            assertThat(microsoft.get().getName()).isEqualTo("Microsoft");
            assertThat(microsoft.get().getLocales()).hasSize(2);
        });

    }
    @Test
    @Disabled
    void isDeletedCompanyTest() {
        Optional<Company> companyOptional = companyRepository.findById(1);
        assertTrue(companyOptional.isPresent());
        companyOptional.ifPresent(companyRepository::delete);
        em.flush();
        assertTrue(companyRepository.findById(1).isEmpty());
    }
}