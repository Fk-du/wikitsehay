package com.bank.tsehay.wikitsehay.mapper;

import com.bank.tsehay.wikitsehay.dto.incident.IncidentResponse;
import com.bank.tsehay.wikitsehay.model.Department;
import com.bank.tsehay.wikitsehay.model.project.Incident;
import com.bank.tsehay.wikitsehay.model.project.Project;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-21T11:15:39+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class IncidentMapperImpl implements IncidentMapper {

    @Override
    public IncidentResponse toResponse(Incident incident) {
        if ( incident == null ) {
            return null;
        }

        IncidentResponse.IncidentResponseBuilder incidentResponse = IncidentResponse.builder();

        incidentResponse.department( incidentDepartmentName( incident ) );
        incidentResponse.project( incidentProjectName( incident ) );
        incidentResponse.id( incident.getId() );
        incidentResponse.title( incident.getTitle() );
        incidentResponse.description( incident.getDescription() );
        incidentResponse.dateRegistered( incident.getDateRegistered() );
        incidentResponse.startDate( incident.getStartDate() );
        incidentResponse.endDate( incident.getEndDate() );
        incidentResponse.severity( incident.getSeverity() );
        incidentResponse.category( incident.getCategory() );

        return incidentResponse.build();
    }

    private String incidentDepartmentName(Incident incident) {
        if ( incident == null ) {
            return null;
        }
        Department department = incident.getDepartment();
        if ( department == null ) {
            return null;
        }
        String name = department.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String incidentProjectName(Incident incident) {
        if ( incident == null ) {
            return null;
        }
        Project project = incident.getProject();
        if ( project == null ) {
            return null;
        }
        String name = project.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
