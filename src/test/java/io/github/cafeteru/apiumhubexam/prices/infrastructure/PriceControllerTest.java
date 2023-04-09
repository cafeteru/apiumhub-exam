package io.github.cafeteru.apiumhubexam.prices.infrastructure;

import static io.github.cafeteru.apiumhubexam.common.infrastructure.util.DateConverter.localDateTimeToString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import io.github.cafeteru.apiumhubexam.prices.application.services.PricesService;
import io.github.cafeteru.apiumhubexam.prices.infrastructure.dto.ConsultRS;

class PriceControllerTest {
    @Mock
    private PricesService pricesService;

    @InjectMocks
    private PricesController pricesController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsult() {
        var consultRS = ConsultRS.builder()
            .productId(1)
            .brandId(1)
            .priceList(1)
            .startDate(LocalDateTime.now())
            .endDate(LocalDateTime.now())
            .finalPrice(BigDecimal.ONE)
            .build();
        when(pricesService.consult(any(), any(), any())).thenReturn(consultRS);
        var result = pricesController.consult(localDateTimeToString(LocalDateTime.now()), 1, 1);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(consultRS.getProductId(), result.getBody().getProductId());
    }

    @Test
    void testConsultNull() {
        when(pricesService.consult(any(), any(), any())).thenReturn(null);
        var result = pricesController.consult(localDateTimeToString(LocalDateTime.now()), 1, 1);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNull(result.getBody());
    }
}
