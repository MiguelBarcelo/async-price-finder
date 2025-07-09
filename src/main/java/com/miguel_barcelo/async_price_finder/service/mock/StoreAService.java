package com.miguel_barcelo.async_price_finder.service.mock;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.miguel_barcelo.async_price_finder.model.PriceResult;

@Service
public class StoreAService {

	public Optional<PriceResult> getPrice(String product) {
		long start = System.currentTimeMillis();
		
		try {
			Thread.sleep(1000); // Simulates delay
		} catch (InterruptedException e) {
			Thread.currentThread().getName();
			return Optional.empty();
		}
		
		double price = 700 + Math.random() * 100; // Price between [700,800)
		
		long duration = System.currentTimeMillis() - start;
		
		return Optional.of(new PriceResult("StoreA", price, duration));
	}
}
