package com.gometro.service.impl;

import com.gometro.entity.Train;
import com.gometro.exception.ResourceNotFoundException;
import com.gometro.repository.TrainRepository;
import com.gometro.service.TrainService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;

    public TrainServiceImpl(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Override
    public Train create(Train train) {
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAll() {
        return trainRepository.findAll();
    }

    @Override
    public Train getById(Long id) {
        return trainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Train", "id", id));
    }

    @Override
    public Train update(Long id, Train trainDetails) {
        Train existingTrain = getById(id);
        if (trainDetails.getTrainNumber() != null) existingTrain.setTrainNumber(trainDetails.getTrainNumber());
        if (trainDetails.getTrainName() != null) existingTrain.setTrainName(trainDetails.getTrainName());
        if (trainDetails.getCapacity() != null) existingTrain.setCapacity(trainDetails.getCapacity());
        if (trainDetails.getStatus() != null) existingTrain.setStatus(trainDetails.getStatus());
        if (trainDetails.getRoute() != null) existingTrain.setRoute(trainDetails.getRoute());
        return trainRepository.save(existingTrain);
    }

    @Override
    public void delete(Long id) {
        Train train = getById(id);
        trainRepository.delete(train);
    }
}
