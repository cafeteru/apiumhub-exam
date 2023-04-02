package io.github.cafeteru.apiumhubexam.repositories;

import static io.github.cafeteru.apiumhubexam.util.DateConverter.stringToLocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import io.github.cafeteru.apiumhubexam.model.Price;

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
                createProduct("2020-06-14-00.00.00", "2020-12-31-23.59.59", 1, 0, 35.50D),
                createProduct("2020-06-14-15.00.00", "2020-06-14-18.30.00", 2, 1, 25.45D),
                createProduct("2020-06-15-00.00.00", "2020-06-15-11.00.00", 3, 1, 30.50D),
                createProduct("2020-06-15-16.00.00", "2020-12-31-23.59.59", 4, 1, 38.95D)
            )
        );
    }

    private Price createProduct(
        String startDate, String endDate, Integer priceList, Integer priority, Double price) {
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
        assertEquals(35.50D, found.get(0).getPrice());
    }

    @Test
    void testConsultWithValueValidAndPriority0() {
        var price = createProduct("2020-06-14-01.00.00", "2020-12-31-23.59.59", 1, 0, 40D);
        pricesRepository.save(price);
        var found = pricesRepository.consult(stringToLocalDateTime("2020-06-14-02.00.00"), 35455, 1, PageRequest.of(0, 1));
        assertEquals(1, found.size());
        assertEquals(40D, found.get(0).getPrice());
    }
}
