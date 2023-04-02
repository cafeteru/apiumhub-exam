package io.github.cafeteru.testjava.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.cafeteru.testjava.model.Price;

public interface PricesRepository extends JpaRepository<Price, Long> {
    @Query("select p from Price " +
        "p where p.startDate <= :localDateTime and p.endDate >= :localDateTime and " +
        "p.productId = :productId and " +
        "p.brandId = :brandId and " +
        "p.priority = (select min(p2.priority) from Price " +
        "p2 where p2.startDate <= :localDateTime and p2.endDate >= :localDateTime)")
    Optional<Price> consult(
        @Param("localDateTime") LocalDateTime localDateTime,
        @Param("productId") Integer productId,
        @Param("brandId") Integer brandId);

}
