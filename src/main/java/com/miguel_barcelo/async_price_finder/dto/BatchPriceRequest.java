package com.miguel_barcelo.async_price_finder.dto;

import java.util.List;

public class BatchPriceRequest {
	private List<String> products;
	
	public BatchPriceRequest() {}
	
	public BatchPriceRequest(List<String> products) {
		this.products = products;
	}
	
	public List<String> getProducts() {
		return products;
	}
	
	public void setProducts(List<String> products) {
		this.products = products;
	}
}
