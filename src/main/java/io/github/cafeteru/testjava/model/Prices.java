package io.github.cafeteru.testjava.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "PRICES")
public class Prices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "BRAND_ID")
    private Integer brandId;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;
    @Column(name = "PRICE_LIST")
    private Integer priceList;
    @Column(name = "PRODUCT_ID")
    private Integer productId;
    // PRIORITY: Desambiguador de aplicación de precios.
    // Si dos tarifas coinciden en un rango de fechas se aplica la de mayor prioridad (mayor valor numérico).
    @Column(name = "PRIORITY")
    private Integer priority;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "CURR")
    private String curr;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prices product = (Prices) o;
        return Objects.equals(id, product.id) && Objects.equals(brandId, product.brandId) &&
            Objects.equals(startDate, product.startDate) && Objects.equals(endDate, product.endDate) &&
            Objects.equals(priceList, product.priceList) && Objects.equals(productId, product.productId)
            && Objects.equals(priority, product.priority) && Objects.equals(price, product.price) &&
            Objects.equals(curr, product.curr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandId, startDate, endDate, priceList, productId, priority, price, curr);
    }
}
