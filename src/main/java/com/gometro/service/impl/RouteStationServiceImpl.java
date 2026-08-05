package com.gometro.service.impl;

import com.gometro.entity.RouteStation;
import com.gometro.exception.ResourceNotFoundException;
import com.gometro.repository.RouteStationRepository;
import com.gometro.service.RouteStationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteStationServiceImpl implements RouteStationService {

    private final RouteStationRepository routeStationRepository;

    public RouteStationServiceImpl(RouteStationRepository routeStationRepository) {
        this.routeStationRepository = routeStationRepository;
    }

    @Override
    public RouteStation create(RouteStation routeStation) {
        return routeStationRepository.save(routeStation);
    }

    @Override
    public List<RouteStation> getAll() {
        return routeStationRepository.findAll();
    }

    @Override
    public RouteStation getById(Long id) {
        return routeStationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RouteStation", "id", id));
    }

    @Override
    public RouteStation update(Long id, RouteStation details) {
        RouteStation existing = getById(id);
        if (details.getStationSequence() != null) existing.setStationSequence(details.getStationSequence());
        if (details.getDistanceFromSourceKm() != null) existing.setDistanceFromSourceKm(details.getDistanceFromSourceKm());
        return routeStationRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        RouteStation routeStation = getById(id);
        routeStationRepository.delete(routeStation);
    }
}
