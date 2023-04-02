package io.github.cafeteru.testjava.services.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import io.github.cafeteru.testjava.model.records.ConsultRS;
import io.github.cafeteru.testjava.repositories.PricesRepository;
import io.github.cafeteru.testjava.services.PricesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PricesServiceImpl implements PricesService {
    private final PricesRepository pricesRepository;

    @Override
    public ConsultRS consult(LocalDateTime applicationDate, Integer idProduct, Integer idBrand) {
        log.info(String.format("consult(%s, %d, %d) - start", applicationDate, idProduct, idBrand));
        var price = pricesRepository.consult(applicationDate, idProduct, idBrand);
        var consultRS = price.map(value ->
            new ConsultRS(
                value.getProductId(), value.getBrandId(), value.getPriceList(),
                value.getStartDate(), value.getEndDate(), value.getPrice()
            )
        ).orElse(null);
        log.info(String.format("consult(%s, %d, %d) - end", applicationDate, idProduct, idBrand));
        return consultRS;
    }
}
