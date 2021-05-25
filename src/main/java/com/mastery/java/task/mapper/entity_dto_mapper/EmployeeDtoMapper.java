package com.mastery.java.task.mapper.entity_dto_mapper;

import com.mastery.java.task.dto.EmployeeDto;
import com.mastery.java.task.model.Employee;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EmployeeDtoMapper {

    EmployeeDto mapToDto(Employee employee);
    Employee mapToEntity(EmployeeDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void update(EmployeeDto dto, @MappingTarget Employee employee);
}
