package com.gbce.stock.sssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbce.stock.sssm.entities.Trade;
import com.gbce.stock.sssm.service.TradeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/trade")
@Slf4j
public class TradeController {
	@Autowired
	TradeService tradeService;

	@PostMapping
	private Trade saveTrade(@RequestBody final Trade trade) {
		log.info("Saving Trade Details");
		return tradeService.saveTrade(trade);
	}

	@GetMapping
	private List<Trade> getAllTrades() {
		log.info("Fetching all Trade Details");
		return tradeService.findTradeAll();
	}

	@GetMapping("/vwsp")
	private Double vwsp() {
		log.info("Fetching Volume Weighted Stock Price");
		return tradeService.getVolumeWeightedStockPrice();
	}

}
