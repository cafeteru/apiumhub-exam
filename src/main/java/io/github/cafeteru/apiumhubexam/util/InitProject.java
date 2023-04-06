package io.github.cafeteru.apiumhubexam.util;

import static io.github.cafeteru.apiumhubexam.util.DateConverter.stringToLocalDateTime;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import io.github.cafeteru.apiumhubexam.model.Price;
import io.github.cafeteru.apiumhubexam.repositories.PricesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InitProject {
    private final PricesRepository pricesRepository;

    @PostConstruct
    public void init() {
        pricesRepository.saveAll(
            List.of(
                createProduct("2020-06-14-00.00.00", "2020-12-31-23.59.59", 1, 0, BigDecimal.valueOf(35.50D)),
                createProduct("2020-06-14-15.00.00", "2020-06-14-18.30.00", 2, 1, BigDecimal.valueOf(25.45D)),
                createProduct("2020-06-15-00.00.00", "2020-06-15-11.00.00", 3, 1, BigDecimal.valueOf(30.50D)),
                createProduct("2020-06-15-16.00.00", "2020-12-31-23.59.59", 4, 1, BigDecimal.valueOf(38.95D))
            )
        );
    }

    private Price createProduct(
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
}
