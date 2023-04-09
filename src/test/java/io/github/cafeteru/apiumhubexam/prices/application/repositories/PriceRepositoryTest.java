package io.github.cafeteru.apiumhubexam.prices.application.repositories;

import static io.github.cafeteru.apiumhubexam.common.infrastructure.util.DateConverter.stringToLocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import io.github.cafeteru.apiumhubexam.prices.domain.Price;
import io.github.cafeteru.apiumhubexam.prices.application.repositories.PricesRepository;

@DataJpaTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PriceRepositoryTest {
    @Autowired
    private PricesRepository pricesRepository;

    @BeforeEach
    public void init() {
        pricesRepository.saveAll(
            List.of(
                createPrice("2020-06-14-00.00.00", "2020-12-31-23.59.59", 1, 0, BigDecimal.valueOf(35.50D)),
                createPrice("2020-06-14-15.00.00", "2020-06-14-18.30.00", 2, 1, BigDecimal.valueOf(25.45D)),
                createPrice("2020-06-15-00.00.00", "2020-06-15-11.00.00", 3, 1, BigDecimal.valueOf(30.50D)),
                createPrice("2020-06-15-16.00.00", "2020-12-31-23.59.59", 4, 1, BigDecimal.valueOf(38.95D))
            )
        );
    }

    private Price createPrice(
        String startDate, String endDate, Integer priceList, Integer priority, BigDecimal price) {
        return Price.builder()
            .brandId(1)
            .startDate(stringToLocalDateTime(startDate))
            .endDate(stringToLocalDateTime(endDate))
            .priceList(priceList)
            .productId(35455)
            .priority(priority)
            .price(price)
            .curr("EUR")
            .build();
    }

    @Test
    void testConsultDateBeforeAll() {
        var found = pricesRepository.consult(stringToLocalDateTime("2020-06-13-23.59.00"), 35455, 1, PageRequest.of(0, 1));
        assertTrue(found.isEmpty());
    }

    @Test
    void testConsultDateAfterAll() {
        var found = pricesRepository.consult(stringToLocalDateTime("2026-06-13-23.59.00"), 35455, 1, PageRequest.of(0, 1));
        assertTrue(found.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "2020-06-14-10.00.00",
        "2020-06-14-16.00.00",
        "2020-06-15-16.00.00"})
    void testConsultWithExamples(String applicationDate) {
        var found = pricesRepository.consult(stringToLocalDateTime(applicationDate), 35455, 1, PageRequest.of(0, 1));
        assertEquals(1, found.size());
        assertEquals(0, BigDecimal.valueOf(35.50D).compareTo(found.get(0).getPrice()));
    }

    @Test
    void testConsultWithValueValidAndPriority0() {
        var priceValue = BigDecimal.valueOf(40D);
        var price = createPrice("2020-06-14-01.00.00", "2020-12-31-23.59.59", 1, 0, priceValue);
        pricesRepository.save(price);
        var found = pricesRepository.consult(stringToLocalDateTime("2020-06-14-02.00.00"), 35455, 1, PageRequest.of(0, 1));
        assertEquals(1, found.size());
        assertEquals(0, priceValue.compareTo(found.get(0).getPrice()));
    }
}
