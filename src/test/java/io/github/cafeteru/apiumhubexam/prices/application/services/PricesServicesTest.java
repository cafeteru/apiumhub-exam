package io.github.cafeteru.apiumhubexam.prices.application.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.cafeteru.apiumhubexam.prices.domain.Price;
import io.github.cafeteru.apiumhubexam.prices.application.repositories.PricesRepository;
import io.github.cafeteru.apiumhubexam.prices.application.services.impl.PricesServiceImpl;

class PricesServicesTest {
    @Mock
    private PricesRepository pricesRepository;

    @InjectMocks
    private PricesServiceImpl pricesService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsult() {
        Price price = Price.builder()
            .productId(1)
            .brandId(1)
            .priceList(1)
            .startDate(LocalDateTime.now())
            .endDate(LocalDateTime.now())
            .price(BigDecimal.ONE)
            .build();
        when(pricesRepository.consult(any(), any(), any(), any())).thenReturn(Collections.singletonList(price));
        var result = pricesService.consult(LocalDateTime.now(), 1, 1);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(price.getProductId(), result.getProductId());
    }

    @Test
    void testConsultNull() {
        when(pricesRepository.consult(any(), any(), any(), any())).thenReturn(Collections.emptyList());
        var result = pricesService.consult(LocalDateTime.now(), 1, 1);
        Assertions.assertNull(result);
    }
}
