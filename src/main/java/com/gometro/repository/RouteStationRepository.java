package com.gometro.repository;

import com.gometro.entity.RouteStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteStationRepository extends JpaRepository<RouteStation, Long> {
    List<RouteStation> findByRouteIdOrderByStationSequenceAsc(Long routeId);
    List<RouteStation> findByStationId(Long stationId);
}
