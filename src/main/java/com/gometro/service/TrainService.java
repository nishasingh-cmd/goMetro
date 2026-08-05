package com.gometro.service;

import com.gometro.entity.Train;
import java.util.List;

public interface TrainService {
    Train create(Train train);
    List<Train> getAll();
    Train getById(Long id);
    Train update(Long id, Train trainDetails);
    void delete(Long id);
}
