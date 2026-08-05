package com.gometro.service.impl;

import com.gometro.entity.Route;
import com.gometro.exception.ResourceNotFoundException;
import com.gometro.repository.RouteRepository;
import com.gometro.service.RouteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Route create(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAll() {
        return routeRepository.findAll();
    }

    @Override
    public Route getById(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route", "id", id));
    }

    @Override
    public Route update(Long id, Route routeDetails) {
        Route existingRoute = getById(id);
        if (routeDetails.getRouteCode() != null) existingRoute.setRouteCode(routeDetails.getRouteCode());
        if (routeDetails.getRouteName() != null) existingRoute.setRouteName(routeDetails.getRouteName());
        if (routeDetails.getTotalDistanceKm() != null) existingRoute.setTotalDistanceKm(routeDetails.getTotalDistanceKm());
        if (routeDetails.getActive() != null) existingRoute.setActive(routeDetails.getActive());
        return routeRepository.save(existingRoute);
    }

    @Override
    public void delete(Long id) {
        Route route = getById(id);
        routeRepository.delete(route);
    }
}
