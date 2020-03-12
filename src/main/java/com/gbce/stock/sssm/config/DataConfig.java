package com.gbce.stock.sssm.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.gbce.stock.sssm.entities.Stock;
import com.gbce.stock.sssm.entities.repository.StockRepository;
import com.gbce.stock.sssm.enumeration.StockType;

@Configuration
public class DataConfig {
	@Autowired
	private StockRepository stockRepository;

	@PostConstruct
	private void initDb() {
		final Stock stock1 = new Stock();
		stock1.setSymbol("TEA");
		stock1.setType(StockType.Common);
		stock1.setLastDividend(Double.valueOf(0));
		stock1.setParValue(Double.valueOf(100));
		stockRepository.save(stock1);

		final Stock stock2 = new Stock();
		stock2.setSymbol("GIN");
		stock2.setType(StockType.Preferred);
		stock2.setLastDividend(Double.valueOf(8));
		stock2.setFixedDividend(Double.valueOf(2));
		stock2.setParValue(Double.valueOf(100));
		stockRepository.save(stock2);
	}
}
