package io.github.cafeteru.testjava.services.impl;

import org.springframework.stereotype.Service;

import io.github.cafeteru.testjava.model.records.ConsultRQ;
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
    public ConsultRS consult(ConsultRQ consultRQ) {
        log.info(String.format("consult(%s) - start", consultRQ));
        var result = pricesRepository.findById(1L).map(price ->
            new ConsultRS(
                price.getProductId(), price.getBrandId(), price.getPriceList(),
                price.getStartDate(), price.getEndDate(), price.getPrice()
            )
        ).orElse(null);
        log.info(String.format("consult(%s) - end", consultRQ));
        return result;
    }
}
