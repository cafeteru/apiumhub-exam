package io.github.cafeteru.testjava.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import io.github.cafeteru.testjava.model.Prices;
import io.github.cafeteru.testjava.repositories.PricesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InitProject {
    private final PricesRepository productRepository;

    @PostConstruct
    public void init() {
        productRepository.saveAll(
            List.of(
                createProduct("2020-06-14-00.00.00", "2020-12-31-23.59.59", 1, 0, 35.50D),
                createProduct("2020-06-14-15.00.00", "2020-06-14-18.30.00", 2, 1, 25.45D),
                createProduct("2020-06-15-00.00.00", "2020-06-15-11.00.00", 3, 1, 30.50D),
                createProduct("2020-06-15-16.00.00", "2020-12-31-23.59.59", 4, 1, 38.95D)
            )
        );
    }

    private Prices createProduct(
        String startDate, String endDate, Integer priceList, Integer priority, Double price) {
        return Prices.builder()
            .brandId(1)
            .startDate(convertStringToDate(startDate))
            .endDate(convertStringToDate(endDate))
            .priceList(priceList)
            .productId(35455)
            .priority(priority)
            .price(price)
            .curr("EUR")
            .build();
    }

    private Date convertStringToDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }
}
