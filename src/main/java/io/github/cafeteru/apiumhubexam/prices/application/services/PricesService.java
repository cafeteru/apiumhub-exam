package io.github.cafeteru.apiumhubexam.prices.application.services;

import java.time.LocalDateTime;

import io.github.cafeteru.apiumhubexam.prices.infrastructure.dto.ConsultRS;

public interface PricesService {
    ConsultRS consult(LocalDateTime applicationDate, Integer idProduct, Integer idBrand);
}
