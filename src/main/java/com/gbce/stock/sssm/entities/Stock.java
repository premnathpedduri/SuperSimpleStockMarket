package com.gbce.stock.sssm.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.gbce.stock.sssm.enumeration.StockType;

import lombok.Data;

@Entity
@Data
public class Stock {

	@Id
	private String symbol;
	private StockType type;
	private Double lastDividend;
	private Double fixedDividend;
	private Double parValue;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Trade> trades;
}
