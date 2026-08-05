package com.gometro.repository;

import com.gometro.entity.Fare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FareRepository extends JpaRepository<Fare, Long> {
    Optional<Fare> findBySourceStationIdAndDestinationStationId(Long sourceStationId, Long destinationStationId);
}
