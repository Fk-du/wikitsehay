package com.bank.tsehay.wikitsehay.mapper;

import com.bank.tsehay.wikitsehay.dto.project.IncidentResponse;
import com.bank.tsehay.wikitsehay.model.project.Incident;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IncidentMapper {

    @Mapping(source = "department.name", target = "department")
    @Mapping(source = "project.name", target = "project")
    IncidentResponse toResponse(Incident incident);
}
