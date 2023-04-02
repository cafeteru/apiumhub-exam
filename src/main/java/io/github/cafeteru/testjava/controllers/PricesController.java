package io.github.cafeteru.testjava.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.cafeteru.testjava.infrastructure.constants.Tags;
import io.github.cafeteru.testjava.infrastructure.constants.Urls;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping(Urls.PRICES)
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = Tags.PRICES)
public class PricesController {

    @GetMapping(Urls.CONSULT)
    public ResponseEntity<Object> consult(
        @Parameter(description = "Fecha de aplicación", example = "2023-04-02-00.00.00")
        String applicationDate,
        @Parameter(description = "Identificador de producto", example = "1")
        Integer idProduct,
        @Parameter(description = "Identificador de cadena", example = "1")
        Integer idBrand) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDate localDate = LocalDate.parse(applicationDate, formatter);
        log.info(String.format("consult(%s, %d, %d) - start", localDate, idProduct, idBrand));
        log.info(String.format("consult(%s, %d, %d) - end", localDate, idProduct, idBrand));
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
