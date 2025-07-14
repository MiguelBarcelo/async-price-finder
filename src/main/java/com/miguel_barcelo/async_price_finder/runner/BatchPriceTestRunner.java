package com.miguel_barcelo.async_price_finder.runner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.miguel_barcelo.async_price_finder.dto.PriceResponse;
import com.miguel_barcelo.async_price_finder.service.PriceAggregatorService;

@Component
public class BatchPriceTestRunner implements CommandLineRunner {

	private final PriceAggregatorService priceAggregatorService;
	
	public BatchPriceTestRunner(PriceAggregatorService priceAggregatorService) {
		this.priceAggregatorService = priceAggregatorService;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("🚀 Starting concurrent price search test...");
		
		List<String> products = Arrays.asList(
				"iPhone 16", "Galaxy S25", "Pixel 9", "Xiaomi 14", "Motorola Edge"
		);
		
		ExecutorService executor = Executors.newFixedThreadPool(products.size());
		
		List<Callable<Void>> tasks = products.stream()
				.map(product -> (Callable<Void>)() -> {
					PriceResponse response = priceAggregatorService.findPrices(product);
					System.out.printf("🛒 [%s] Best: %.2f at %s (%dms)\n",
							product,
							response.getBestPrice(),
							response.getBestStore(),
							response.getDurationMs());
					
					return null;
				})
				.toList();
		
		executor.invokeAll(tasks);
		executor.shutdown();
	}
}
