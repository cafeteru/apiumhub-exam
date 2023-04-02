package io.github.cafeteru.testjava.services;

import java.time.LocalDateTime;

import io.github.cafeteru.testjava.model.records.ConsultRS;

public interface PricesService {
    ConsultRS consult(LocalDateTime applicationDate, Integer idProduct, Integer idBrand);
}
