package io.github.cafeteru.apiumhubexam.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.cafeteru.apiumhubexam.infrastructure.constants.Tags;
import io.github.cafeteru.apiumhubexam.infrastructure.constants.Urls;
import io.github.cafeteru.apiumhubexam.model.dto.ConsultRS;
import io.github.cafeteru.apiumhubexam.services.PricesService;
import io.github.cafeteru.apiumhubexam.util.DateConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping(Urls.PRICES)
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = Tags.PRICES)
public class PricesController {
    private final PricesService pricesService;

    @Operation(summary = "REST endpoint for querying")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = ConsultRS.class))
        })
    })
    @GetMapping(Urls.CONSULT)
    public ResponseEntity<ConsultRS> consult(
        @Parameter(description = "Application date", example = "2020-06-14-00.00.00")
        String applicationDate,
        @Parameter(description = "Product identifier", example = "35455")
        Integer idProduct,
        @Parameter(description = "Brand identifier", example = "1")
        Integer idBrand
    ) {
        log.info(String.format("consult(%s, %d, %d) - start", applicationDate, idProduct, idBrand));
        var localDate = DateConverter.stringToLocalDateTime(applicationDate);
        var consultRS = pricesService.consult(localDate, idProduct, idBrand);
        log.info(String.format("consult(%s, %d, %d) - end", applicationDate, idProduct, idBrand));
        return new ResponseEntity<>(consultRS, HttpStatus.OK);
    }
}
