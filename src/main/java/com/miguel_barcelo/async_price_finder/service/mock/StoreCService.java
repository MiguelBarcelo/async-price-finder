package com.miguel_barcelo.async_price_finder.service.mock;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.miguel_barcelo.async_price_finder.model.PriceResult;

@Service
public class StoreCService {

	public Optional<PriceResult> getPrice(String product) {
		long start = System.currentTimeMillis();
		
		try {
			Thread.sleep(700); // Faster
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return Optional.empty();
		}
		
		double price = 720 + Math.random() * 80; // [720,800]
		
		long duration = System.currentTimeMillis() - start;
		
		return Optional.of(new PriceResult("StoreC", price, duration));
		
	}
}
