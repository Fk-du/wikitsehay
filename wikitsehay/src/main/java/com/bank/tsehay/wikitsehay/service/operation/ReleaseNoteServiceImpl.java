package com.bank.tsehay.wikitsehay.service.operation;

import com.bank.tsehay.wikitsehay.dto.operation.ReleaseNoteRequest;
import com.bank.tsehay.wikitsehay.dto.operation.ReleaseNoteResponse;
import com.bank.tsehay.wikitsehay.mapper.ReleaseNoteMapper;
import com.bank.tsehay.wikitsehay.model.operation.ReleaseNote;
import com.bank.tsehay.wikitsehay.model.project.Project;
import com.bank.tsehay.wikitsehay.repository.operation.ReleaseNoteRepository;
import com.bank.tsehay.wikitsehay.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReleaseNoteServiceImpl implements ReleaseNoteService {

    private final ReleaseNoteRepository releaseNoteRepository;
    private final ProjectRepository projectRepository;
    private final ReleaseNoteMapper releaseNoteMapper;

    @Override
    public ReleaseNoteResponse createReleaseNote(ReleaseNoteRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        ReleaseNote releaseNote = releaseNoteMapper.toEntity(request, project);
        return releaseNoteMapper.toResponse(releaseNoteRepository.save(releaseNote));
    }

    @Override
    public ReleaseNoteResponse getReleaseNoteById(Long id) {
        ReleaseNote releaseNote = releaseNoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ReleaseNote not found"));
        return releaseNoteMapper.toResponse(releaseNote);
    }

    @Override
    public List<ReleaseNoteResponse> getAllReleaseNotes() {
        return releaseNoteRepository.findAll()
                .stream()
                .map(releaseNoteMapper::toResponse)
                .toList();
    }

    @Override
    public List<ReleaseNoteResponse> getReleaseNotesByProject(Long projectId) {
        return releaseNoteRepository.findByProjectId(projectId)
                .stream()
                .map(releaseNoteMapper::toResponse)
                .toList();
    }

    @Override
    public ReleaseNoteResponse updateReleaseNote(Long id, ReleaseNoteRequest request) {
        ReleaseNote releaseNote = releaseNoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ReleaseNote not found"));
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        releaseNote.setVersion(request.getVersion());
        releaseNote.setDescription(request.getDescription());
        releaseNote.setReleaseDate(request.getReleaseDate());
        releaseNote.setProject(project);

        return releaseNoteMapper.toResponse(releaseNoteRepository.save(releaseNote));
    }

    @Override
    public void deleteReleaseNote(Long id) {
        releaseNoteRepository.deleteById(id);
    }
}
