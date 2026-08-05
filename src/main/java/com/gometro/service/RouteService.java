package com.gometro.service;

import com.gometro.entity.Route;
import java.util.List;

public interface RouteService {
    Route create(Route route);
    List<Route> getAll();
    Route getById(Long id);
    Route update(Long id, Route routeDetails);
    void delete(Long id);
}
