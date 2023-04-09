package io.github.cafeteru.apiumhubexam.prices.application.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.cafeteru.apiumhubexam.prices.domain.Price;

public interface PricesRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p " +
        "WHERE p.startDate <= :localDateTime AND p.endDate >= :localDateTime AND " +
        "p.productId = :productId AND " +
        "p.brandId = :brandId AND " +
        "p.priority = (SELECT MIN(p2.priority) FROM Price p2 " +
        "WHERE p2.startDate <= :localDateTime AND p2.endDate >= :localDateTime) " +
        "ORDER BY p.price DESC")
    List<Price> consult(
        @Param("localDateTime") LocalDateTime localDateTime,
        @Param("productId") Integer productId,
        @Param("brandId") Integer brandId,
        Pageable pageable);

}


