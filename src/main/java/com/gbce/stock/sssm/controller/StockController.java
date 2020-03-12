package com.gbce.stock.sssm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gbce.stock.sssm.service.StockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/stock")
@Slf4j
public class StockController {

	@Autowired
	private StockService stockService;

	@GetMapping(value = "/dividend/{symbol}")
	public Double getDividentYield(@PathVariable("symbol") final String symbol, @RequestParam(name = "price", required = true) final Double price) {

		return stockService.getDividendValue(symbol, price);
	}

	@GetMapping(value = "/peratio/{symbol}")
	public Double getPERatio(@PathVariable("symbol") final String symbol, @RequestParam(name = "price", required = true) final Double price) {

		return stockService.getPERatio(symbol, price);
	}

	@GetMapping("/index")
	private Map<String, Double> getStockIndex() {
		log.info("Fetching Share Index");
		return stockService.getStockIndex();
	}
}
