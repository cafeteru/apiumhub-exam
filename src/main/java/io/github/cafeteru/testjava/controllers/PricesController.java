package io.github.cafeteru.testjava.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.cafeteru.testjava.infrastructure.constants.Urls;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Urls.PRICES)
@Slf4j
@AllArgsConstructor
public class PricesController {
}
