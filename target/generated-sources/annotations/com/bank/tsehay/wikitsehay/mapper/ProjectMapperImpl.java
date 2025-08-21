package com.bank.tsehay.wikitsehay.mapper;

import com.bank.tsehay.wikitsehay.dto.project.ProjectResponse;
import com.bank.tsehay.wikitsehay.model.Department;
import com.bank.tsehay.wikitsehay.model.project.Project;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-21T13:53:26+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectResponse toResponse(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectResponse.ProjectResponseBuilder projectResponse = ProjectResponse.builder();

        projectResponse.department( projectDepartmentName( project ) );
        projectResponse.id( project.getId() );
        projectResponse.name( project.getName() );
        projectResponse.charter( project.getCharter() );
        projectResponse.startDate( project.getStartDate() );
        projectResponse.endDate( project.getEndDate() );
        projectResponse.status( project.getStatus() );

        return projectResponse.build();
    }

    private String projectDepartmentName(Project project) {
        if ( project == null ) {
            return null;
        }
        Department department = project.getDepartment();
        if ( department == null ) {
            return null;
        }
        String name = department.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
