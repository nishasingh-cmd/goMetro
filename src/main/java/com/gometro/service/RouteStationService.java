package com.gometro.service;

import com.gometro.entity.RouteStation;
import java.util.List;

public interface RouteStationService {
    RouteStation create(RouteStation routeStation);
    List<RouteStation> getAll();
    RouteStation getById(Long id);
    RouteStation update(Long id, RouteStation details);
    void delete(Long id);
}
