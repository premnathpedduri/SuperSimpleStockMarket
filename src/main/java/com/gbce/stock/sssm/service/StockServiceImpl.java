package com.gbce.stock.sssm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gbce.stock.sssm.entities.Stock;
import com.gbce.stock.sssm.entities.Trade;
import com.gbce.stock.sssm.entities.repository.StockRepository;
import com.gbce.stock.sssm.entities.repository.TradeRepository;
import com.gbce.stock.sssm.enumeration.StockType;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository;

	@Autowired
	TradeRepository tradeRepository;

	@Override
	public Double getDividendValue(final String symbol, final Double price) {
		if (price != 0) {
			log.debug("Fetching dividendValue for stock:{}", symbol);
			final Optional<Stock> stock = stockRepository.findById(symbol);

			if (stock.isPresent()) {
				if (stock.get().getType().equals(StockType.Common)) {
					return stock.get().getLastDividend() / price;

				}

				if (stock.get().getType().equals(StockType.Preferred)) {
					return (stock.get().getLastDividend() / price) * stock.get().getParValue();

				}
			}
		}
		return Double.valueOf(0);
	}

	@Override
	public Double getPERatio(final String symbol, final Double price) {
		log.debug("Fetching dividendValue for stock:{}", symbol);
		final Optional<Stock> stock = stockRepository.findById(symbol);
		if (stock.isPresent() && stock.get().getLastDividend() != 0) {
			return price / stock.get().getLastDividend();
		}
		return Double.valueOf(0);
	}

	@Override
	public Map<String, Double> getStockIndex() {
		final Map<String, Double> shareIndex = new HashMap<>();

		final List<Stock> stockList = stockRepository.findAll();
		if (!CollectionUtils.isEmpty(stockList)) {
			for (final Stock stock : stockList) {
				final Set<Trade> trades = tradeRepository.findAllByStock(stock);
				shareIndex.put(stock.getSymbol(), geometricMean(trades));
			}
		}

		return shareIndex;
	}

	private Double geometricMean(final Set<Trade> tradeList) {
		if (!CollectionUtils.isEmpty(tradeList)) {
			final int n = tradeList.size();
			Double product = Double.valueOf(1);
			for (final Trade trade : tradeList) {
				product = product * trade.getTradedPrice();
			}
			return Math.pow(product, (float) 1 / n);
		}

		return Double.valueOf(0);
	}

}
