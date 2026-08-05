package com.gometro.service;

import com.gometro.entity.Fare;
import java.util.List;

public interface FareService {
    Fare create(Fare fare);
    List<Fare> getAll();
    Fare getById(Long id);
    Fare update(Long id, Fare fareDetails);
    void delete(Long id);
}
