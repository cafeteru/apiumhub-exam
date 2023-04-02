package io.github.cafeteru.apiumhubexam.model.records;

import java.time.LocalDateTime;

public record ConsultRS(
    int productId,
    int brandId,
    double priceList,
    LocalDateTime startDate,
    LocalDateTime endDate,
    double finalPrice
) {
}
