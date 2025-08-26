package com.bank.tsehay.wikitsehay.service.operation;

import com.bank.tsehay.wikitsehay.dto.operation.ReleaseNoteRequest;
import com.bank.tsehay.wikitsehay.dto.operation.ReleaseNoteResponse;

import java.util.List;

public interface ReleaseNoteService {
    ReleaseNoteResponse createReleaseNote(ReleaseNoteRequest request);
    ReleaseNoteResponse getReleaseNoteById(Long id);
    List<ReleaseNoteResponse> getAllReleaseNotes();
    List<ReleaseNoteResponse> getReleaseNotesByProject(Long projectId);
    ReleaseNoteResponse updateReleaseNote(Long id, ReleaseNoteRequest request);
    void deleteReleaseNote(Long id);
}
