package com.miguel_barcelo.async_price_finder.model;

public class PriceResult {
	private String store;
	private double price;
	private long responseTimeMs;
	
	public PriceResult() {}
	
	public PriceResult(String store, double price, long responseTimeMs) {
		this.store = store;
		this.price = price;
		this.responseTimeMs = responseTimeMs;
	}
	
	public String getStore() { return store; }
	public void setStore(String store) { this.store = store; }
	
	public double getPrice() { return price; }
	public void setPrice(double price) { this.price = price; }
	
	public long getResponseTimeMs() { return responseTimeMs; }
	public void setResponseTimeMs(long responseTimeMs) { this.responseTimeMs = responseTimeMs; }
	
}
