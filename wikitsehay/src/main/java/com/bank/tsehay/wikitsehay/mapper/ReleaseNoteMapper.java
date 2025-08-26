package com.bank.tsehay.wikitsehay.mapper;

import com.bank.tsehay.wikitsehay.dto.operation.ReleaseNoteRequest;
import com.bank.tsehay.wikitsehay.dto.operation.ReleaseNoteResponse;
import com.bank.tsehay.wikitsehay.model.operation.ReleaseNote;
import com.bank.tsehay.wikitsehay.model.project.Project;
import org.springframework.stereotype.Component;

@Component
public class ReleaseNoteMapper {

    public ReleaseNote toEntity(ReleaseNoteRequest request, Project project) {
        ReleaseNote releaseNote = new ReleaseNote();
        releaseNote.setVersion(request.getVersion());
        releaseNote.setDescription(request.getDescription());
        releaseNote.setReleaseDate(request.getReleaseDate());
        releaseNote.setProject(project);
        return releaseNote;
    }

    public ReleaseNoteResponse toResponse(ReleaseNote releaseNote) {
        ReleaseNoteResponse response = new ReleaseNoteResponse();
        response.setId(releaseNote.getId());
        response.setVersion(releaseNote.getVersion());
        response.setDescription(releaseNote.getDescription());
        response.setReleaseDate(releaseNote.getReleaseDate());
        response.setProjectId(releaseNote.getProject().getId());
        return response;
    }
}
