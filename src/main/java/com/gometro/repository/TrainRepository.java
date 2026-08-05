package com.gometro.repository;

import com.gometro.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
    Optional<Train> findByTrainNumber(String trainNumber);
    List<Train> findByRouteId(Long routeId);
    boolean existsByTrainNumber(String trainNumber);
}
