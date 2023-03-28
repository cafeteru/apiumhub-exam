package io.github.cafeteru.testjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.cafeteru.testjava.model.Prices;

public interface PricesRepository extends JpaRepository<Prices, Long> {
}
