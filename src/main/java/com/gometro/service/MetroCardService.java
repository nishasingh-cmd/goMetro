package com.gometro.service;

import com.gometro.entity.MetroCard;
import java.util.List;

public interface MetroCardService {
    MetroCard create(MetroCard metroCard);
    List<MetroCard> getAll();
    MetroCard getById(Long id);
    MetroCard update(Long id, MetroCard cardDetails);
    void delete(Long id);
}
