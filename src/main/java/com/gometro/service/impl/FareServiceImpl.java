package com.gometro.service.impl;

import com.gometro.entity.Fare;
import com.gometro.exception.ResourceNotFoundException;
import com.gometro.repository.FareRepository;
import com.gometro.service.FareService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FareServiceImpl implements FareService {

    private final FareRepository fareRepository;

    public FareServiceImpl(FareRepository fareRepository) {
        this.fareRepository = fareRepository;
    }

    @Override
    public Fare create(Fare fare) {
        return fareRepository.save(fare);
    }

    @Override
    public List<Fare> getAll() {
        return fareRepository.findAll();
    }

    @Override
    public Fare getById(Long id) {
        return fareRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fare", "id", id));
    }

    @Override
    public Fare update(Long id, Fare fareDetails) {
        Fare existingFare = getById(id);
        if (fareDetails.getAmount() != null) existingFare.setAmount(fareDetails.getAmount());
        return fareRepository.save(existingFare);
    }

    @Override
    public void delete(Long id) {
        Fare fare = getById(id);
        fareRepository.delete(fare);
    }
}
