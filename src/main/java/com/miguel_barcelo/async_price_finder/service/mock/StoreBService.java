package com.miguel_barcelo.async_price_finder.service.mock;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.miguel_barcelo.async_price_finder.model.PriceResult;

@Service
public class StoreBService {
	
	private final Random random = new Random();

	public Optional<PriceResult> getPrice(String product) {
		long start = System.currentTimeMillis();
		
		try {
			Thread.sleep(1500); // higher latency
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return Optional.empty();
		}
		
		// Slower, sometimes fails
		if (random.nextBoolean()) {
			System.out.println("❌ StoreB failed!");
			return Optional.empty();
		}
		
		double price = 750 + Math.random() * 100; // [750, 850]
		
		long duration = System.currentTimeMillis() - start;
		
		return Optional.of(new PriceResult("StoreB", price, duration));
	}
}
