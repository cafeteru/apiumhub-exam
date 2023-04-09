package io.github.cafeteru.apiumhubexam.prices.application.services.impl;

import java.time.LocalDateTime;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.github.cafeteru.apiumhubexam.prices.infrastructure.dto.ConsultRS;
import io.github.cafeteru.apiumhubexam.prices.application.repositories.PricesRepository;
import io.github.cafeteru.apiumhubexam.prices.application.services.PricesService;
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
        var price = pricesRepository.consult(applicationDate, idProduct, idBrand, PageRequest.of(0, 1));
        if (Boolean.FALSE.equals(price.isEmpty())) {
            var value = price.get(0);
            var consultRS = ConsultRS.builder()
                .productId(value.getProductId())
                .brandId(value.getBrandId())
                .priceList(value.getPriceList())
                .startDate(value.getStartDate())
                .endDate(value.getEndDate())
                .finalPrice(value.getPrice())
                .build();
            log.info(String.format("consult(%s, %d, %d) - end", applicationDate, idProduct, idBrand));
            return consultRS;
        }
        log.info(String.format("consult(%s, %d, %d) - end", applicationDate, idProduct, idBrand));
        return null;
    }
}
