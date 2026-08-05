package com.gometro.service.impl;

import com.gometro.entity.MaintenanceIssue;
import com.gometro.exception.ResourceNotFoundException;
import com.gometro.repository.MaintenanceIssueRepository;
import com.gometro.service.MaintenanceIssueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceIssueServiceImpl implements MaintenanceIssueService {

    private final MaintenanceIssueRepository maintenanceIssueRepository;

    public MaintenanceIssueServiceImpl(MaintenanceIssueRepository maintenanceIssueRepository) {
        this.maintenanceIssueRepository = maintenanceIssueRepository;
    }

    @Override
    public MaintenanceIssue create(MaintenanceIssue maintenanceIssue) {
        return maintenanceIssueRepository.save(maintenanceIssue);
    }

    @Override
    public List<MaintenanceIssue> getAll() {
        return maintenanceIssueRepository.findAll();
    }

    @Override
    public MaintenanceIssue getById(Long id) {
        return maintenanceIssueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MaintenanceIssue", "id", id));
    }

    @Override
    public MaintenanceIssue update(Long id, MaintenanceIssue issueDetails) {
        MaintenanceIssue existing = getById(id);
        if (issueDetails.getTitle() != null) existing.setTitle(issueDetails.getTitle());
        if (issueDetails.getDescription() != null) existing.setDescription(issueDetails.getDescription());
        if (issueDetails.getIssueType() != null) existing.setIssueType(issueDetails.getIssueType());
        if (issueDetails.getPriority() != null) existing.setPriority(issueDetails.getPriority());
        if (issueDetails.getStatus() != null) existing.setStatus(issueDetails.getStatus());
        if (issueDetails.getResolvedAt() != null) existing.setResolvedAt(issueDetails.getResolvedAt());
        return maintenanceIssueRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        MaintenanceIssue issue = getById(id);
        maintenanceIssueRepository.delete(issue);
    }
}
