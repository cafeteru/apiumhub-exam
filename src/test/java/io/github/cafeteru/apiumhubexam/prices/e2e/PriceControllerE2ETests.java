package io.github.cafeteru.apiumhubexam.prices.e2e;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import io.github.cafeteru.apiumhubexam.common.domain.ErrorDto;
import io.github.cafeteru.apiumhubexam.prices.infrastructure.dto.ConsultRS;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PriceControllerE2ETests {
    private static final String CONSULT_URL = "/prices/consult";

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:8081";
    }

    @Test
    void testConsultWithInvalidDate() {
        var result = RestAssured.given()
            .queryParams("applicationDate", "00.00")
            .queryParams("idProduct", 35455)
            .queryParams("idBrand", 1)
            .get(CONSULT_URL)
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .extract()
            .body()
            .as(ErrorDto.class);
        Assertions.assertEquals("Invalid LocalDateTime: 00.00", result.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "2020-06-14-10.00.00",
        "2020-06-14-16.00.00",
        "2020-06-14-21.00.00",
        "2020-06-15-10.00.00",
        "2020-06-16-21.00.00"
    })
    void testConsult(String applicationDate) {
        var result = RestAssured.given()
            .queryParams("applicationDate", applicationDate)
            .queryParams("idProduct", 35455)
            .queryParams("idBrand", 1)
            .get(CONSULT_URL)
            .then()
            .statusCode(200)
            .extract()
            .body()
            .as(ConsultRS.class);
        Assertions.assertEquals(0, BigDecimal.valueOf(35.50D).compareTo(result.getFinalPrice()));
    }
}
