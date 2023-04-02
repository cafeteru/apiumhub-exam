package io.github.cafeteru.testjava.model.records;

import java.util.Date;

public record ConsultRS(
    int productId,
    int brandId,
    double priceList,
    Date startDate,
    Date endDate,
    double finalPrice
) {
}
