package com.gometro.service.impl;

import com.gometro.entity.Station;
import com.gometro.exception.ResourceNotFoundException;
import com.gometro.repository.StationRepository;
import com.gometro.service.StationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public Station create(Station station) {
        return stationRepository.save(station);
    }

    @Override
    public List<Station> getAll() {
        return stationRepository.findAll();
    }

    @Override
    public Station getById(Long id) {
        return stationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Station", "id", id));
    }

    @Override
    public Station update(Long id, Station stationDetails) {
        Station existingStation = getById(id);
        if (stationDetails.getStationName() != null) existingStation.setStationName(stationDetails.getStationName());
        if (stationDetails.getStationCode() != null) existingStation.setStationCode(stationDetails.getStationCode());
        if (stationDetails.getLocation() != null) existingStation.setLocation(stationDetails.getLocation());
        if (stationDetails.getActive() != null) existingStation.setActive(stationDetails.getActive());
        return stationRepository.save(existingStation);
    }

    @Override
    public void delete(Long id) {
        Station station = getById(id);
        stationRepository.delete(station);
    }
}
