package com.gometro.service;

import com.gometro.entity.Station;
import java.util.List;

public interface StationService {
    Station create(Station station);
    List<Station> getAll();
    Station getById(Long id);
    Station update(Long id, Station stationDetails);
    void delete(Long id);
}
