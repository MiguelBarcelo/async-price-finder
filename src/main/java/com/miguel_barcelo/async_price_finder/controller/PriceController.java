package com.miguel_barcelo.async_price_finder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miguel_barcelo.async_price_finder.dto.BatchPriceRequest;
import com.miguel_barcelo.async_price_finder.dto.BatchPriceResponse;
import com.miguel_barcelo.async_price_finder.dto.PriceResponse;
import com.miguel_barcelo.async_price_finder.service.PriceAggregatorService;

@RestController
@RequestMapping("/api/price")
public class PriceController {
	
	private final PriceAggregatorService priceService;
	
	public PriceController(PriceAggregatorService priceService) {
		this.priceService = priceService;
	}

	@GetMapping
	public PriceResponse getPrices(@RequestParam String product) {
		return priceService.findPrices(product);
	}
	
	@PostMapping("/batch")
	public BatchPriceResponse processBatch(@RequestBody BatchPriceRequest request) throws InterruptedException {
		return priceService.processBatch(request.getProducts());
	}
}
