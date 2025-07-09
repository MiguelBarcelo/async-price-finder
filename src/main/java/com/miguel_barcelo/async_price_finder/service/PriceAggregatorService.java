package com.miguel_barcelo.async_price_finder.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.miguel_barcelo.async_price_finder.dto.PriceResponse;
import com.miguel_barcelo.async_price_finder.model.PriceResult;
import com.miguel_barcelo.async_price_finder.service.mock.StoreAService;
import com.miguel_barcelo.async_price_finder.service.mock.StoreBService;
import com.miguel_barcelo.async_price_finder.service.mock.StoreCService;

@Service
public class PriceAggregatorService {
	
	private final StoreAService storeA;
	private final StoreBService storeB;
	private final StoreCService storeC;
	private final ExecutorService executor;
	
	public PriceAggregatorService(StoreAService storeA, StoreBService storeB, StoreCService storeC) {
		this.storeA = storeA;
		this.storeB = storeB;
		this.storeC = storeC;
		this.executor = Executors.newFixedThreadPool(3); // One per store 
	}

	public PriceResponse findPrices(String product) {
		long start = System.currentTimeMillis();
		
		List<Callable<Optional<PriceResult>>> tasks = Arrays.asList(
				() -> storeA.getPrice(product),
				() -> storeB.getPrice(product),
				() -> storeC.getPrice(product)
		);
		
		List<PriceResult> results = new ArrayList<>();
		
		try {
			List<Future<Optional<PriceResult>>> futures = executor.invokeAll(tasks, 2, TimeUnit.SECONDS);
			
			for (Future<Optional<PriceResult>> future: futures) {
				try {
					if (!future.isCancelled()) {
						Optional<PriceResult> result = future.get(); // Doesn't block because they are already completed or have timeout; can throw an exception
						result.ifPresent(results::add);						
					} else {
						System.out.println("⏱️ Task was cancelled due to timeout");
					}
				} catch (CancellationException e) {
					System.out.println("⚠️ Task was cancelled explicitly");
				} catch (ExecutionException e) {
					System.out.println("❌ Task threw an exception: " + e.getCause());
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					System.out.println("🔌 Thread was interrupted while waiting");
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("❌ invokeAll interrupted");
		}
		
		// Search the best PriceResult
		Optional<PriceResult> bestResult = results.stream()
				.min(Comparator.comparingDouble(PriceResult::getPrice));
		
		double bestPrice = bestResult.map(PriceResult::getPrice).orElse(-1.0);
		String bestStore = bestResult.map(PriceResult::getStore).orElse("N/A");
		
		long duration = System.currentTimeMillis() - start;
		
		return new PriceResponse(product, results, bestPrice, bestStore, duration);
	}
}
