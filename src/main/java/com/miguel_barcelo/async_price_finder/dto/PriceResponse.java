package com.miguel_barcelo.async_price_finder.dto;

import java.util.List;

import com.miguel_barcelo.async_price_finder.model.PriceResult;

public class PriceResponse {
	private String product;
	private List<PriceResult> results;
	private double bestPrice;
	private String bestStore;
	private long durationMs;
	
	public PriceResponse() {}
	
	public PriceResponse(String product, List<PriceResult> results, double bestPrice, String bestStore, long durationMs) {
		this.product = product;
		this.results = results;
		this.bestPrice = bestPrice;
		this.bestStore = bestStore;
		this.durationMs = durationMs;
	}
	
	public String getProduct() { return product; }
	public void setProduct(String product) { this.product = product; }
	
	public List<PriceResult> getResults() { return results; }
	public void setResults(List<PriceResult> results) { this.results = results; }
	
	public double getBestPrice() { return bestPrice; }
	public void setBestPrice(double bestPrice) { this.bestPrice = bestPrice; }
	
	public String getBestStore() { return bestStore; }
	public void setBestStore(String bestStore) { this.bestStore = bestStore; }
	
	public long getDurationMs() { return durationMs; }
	public void setDurationMs(long durationMs) { this.durationMs = durationMs; }
	
}
