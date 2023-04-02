package io.github.cafeteru.apiumhubexam.services;

import java.time.LocalDateTime;

import io.github.cafeteru.apiumhubexam.model.records.ConsultRS;

public interface PricesService {
    ConsultRS consult(LocalDateTime applicationDate, Integer idProduct, Integer idBrand);
}