package io.github.cafeteru.testjava.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import io.github.cafeteru.testjava.infrastructure.constants.Dates;
import io.github.cafeteru.testjava.model.records.ConsultRS;
import io.github.cafeteru.testjava.services.PricesService;

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
        ConsultRS consultRS = new ConsultRS(1, 1, 1, LocalDateTime.now(), LocalDateTime.now(), 1);
        when(pricesService.consult(any(), any(), any())).thenReturn(consultRS);
        var result = pricesController.consult(getLocalDateTimeString(LocalDateTime.now()), 1, 1);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(consultRS.productId(), result.getBody().productId());
    }

    @Test
    void testConsultNull() {
        when(pricesService.consult(any(), any(), any())).thenReturn(null);
        var result = pricesController.consult(getLocalDateTimeString(LocalDateTime.now()), 1, 1);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNull(result.getBody());
    }

    private String getLocalDateTimeString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Dates.PATTERN);
        return localDateTime.format(formatter);
    }
}
