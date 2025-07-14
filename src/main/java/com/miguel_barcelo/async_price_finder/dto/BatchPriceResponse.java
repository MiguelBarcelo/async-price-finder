package com.miguel_barcelo.async_price_finder.dto;

import java.util.List;

public class BatchPriceResponse {
	private List<PriceResponse> results;
	
	public BatchPriceResponse() {}
	
	public BatchPriceResponse(List<PriceResponse> results) {
		this.results = results;
	}
	
	public List<PriceResponse> getResults() {
		return results;
	}
	
	public void setResults(List<PriceResponse> results) {
		this.results = results;
	}
}
