package ru.radion.utils.mapper;

import org.mapstruct.Mapper;
import ru.radion.database.entity.Company;
import ru.radion.dto.CompanyReadDto;
@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyReadDto companyToCompanyReadDto (Company company);
    Company companyReadDtoToCompany (CompanyReadDto companyReadDto);
}
