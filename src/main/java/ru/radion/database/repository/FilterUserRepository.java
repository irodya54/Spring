package ru.radion.database.repository;

import ru.radion.database.entity.Role;
import ru.radion.database.entity.User;
import ru.radion.dto.PersonalInfo;
import ru.radion.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter userFilter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);
}
