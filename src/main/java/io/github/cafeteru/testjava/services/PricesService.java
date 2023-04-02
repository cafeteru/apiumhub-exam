package io.github.cafeteru.testjava.services;

import io.github.cafeteru.testjava.model.records.ConsultRQ;
import io.github.cafeteru.testjava.model.records.ConsultRS;

public interface PricesService {
    ConsultRS consult(ConsultRQ consultRQ);
}
