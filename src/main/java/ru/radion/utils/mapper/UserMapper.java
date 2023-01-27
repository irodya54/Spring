package ru.radion.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.radion.database.entity.User;
import ru.radion.dto.UserCreateDto;
import ru.radion.dto.UserEditDto;
import ru.radion.dto.UserReadDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy =  ReportingPolicy.IGNORE)
public interface UserMapper {

    UserCreateDto userToUserCreateDto(User user);
    User userCreateDtoToUser(UserCreateDto userCreateDto);

    @Mapping(source = "company.id", target = "companyId")

    UserEditDto userToUserEditeDto(User user);
    User userEditDtoToUser(UserEditDto userEditDto);

    UserReadDto userToUserReadeDto(User user);
    User userReadDtoToUser(UserReadDto userReadDto);
}
