package com.gometro.repository;

import com.gometro.entity.MetroCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetroCardRepository extends JpaRepository<MetroCard, Long> {
    Optional<MetroCard> findByCardNumber(String cardNumber);
    List<MetroCard> findByUserId(Long userId);
    boolean existsByCardNumber(String cardNumber);
}
