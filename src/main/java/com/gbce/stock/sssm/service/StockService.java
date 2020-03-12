package com.gbce.stock.sssm.service;

import java.util.Map;

public interface StockService {

	Double getDividendValue(String stock, Double price);

	Double getPERatio(String symbol, Double price);

	Map<String, Double> getStockIndex();

}
