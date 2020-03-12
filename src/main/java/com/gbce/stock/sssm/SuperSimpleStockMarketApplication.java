package com.gbce.stock.sssm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gbce.stock.sssm.entities.repository.StockRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SuperSimpleStockMarketApplication {

	StockRepository stockRepository;

	public static void main(final String[] args) {
		SpringApplication.run(SuperSimpleStockMarketApplication.class, args);
	}

}
