package com.gometro.service.impl;

import com.gometro.entity.MetroCard;
import com.gometro.exception.ResourceNotFoundException;
import com.gometro.repository.MetroCardRepository;
import com.gometro.service.MetroCardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetroCardServiceImpl implements MetroCardService {

    private final MetroCardRepository metroCardRepository;

    public MetroCardServiceImpl(MetroCardRepository metroCardRepository) {
        this.metroCardRepository = metroCardRepository;
    }

    @Override
    public MetroCard create(MetroCard metroCard) {
        return metroCardRepository.save(metroCard);
    }

    @Override
    public List<MetroCard> getAll() {
        return metroCardRepository.findAll();
    }

    @Override
    public MetroCard getById(Long id) {
        return metroCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MetroCard", "id", id));
    }

    @Override
    public MetroCard update(Long id, MetroCard cardDetails) {
        MetroCard existingCard = getById(id);
        if (cardDetails.getBalance() != null) existingCard.setBalance(cardDetails.getBalance());
        if (cardDetails.getStatus() != null) existingCard.setStatus(cardDetails.getStatus());
        if (cardDetails.getExpiryDate() != null) existingCard.setExpiryDate(cardDetails.getExpiryDate());
        return metroCardRepository.save(existingCard);
    }

    @Override
    public void delete(Long id) {
        MetroCard card = getById(id);
        metroCardRepository.delete(card);
    }
}
