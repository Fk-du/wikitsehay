package com.bank.tsehay.wikitsehay.mapper;

import com.bank.tsehay.wikitsehay.dto.project.ProjectResponse;
import com.bank.tsehay.wikitsehay.model.project.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(source = "department.name", target = "department")
    ProjectResponse toResponse(Project project);
}

