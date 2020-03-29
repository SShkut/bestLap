package com.foxminded.f1_best_lap;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

	public static void main(String[] args) {
		RacerRepository racerRepository = new RacerRepository();
		Formatter formatter = new Formatter();		
		List<Racer> racers = racerRepository.getRacers();
		AtomicInteger index = new AtomicInteger(1);
		
		racers.stream()
			.sorted((d1, d2) -> d1.getBestLapTime().compareTo(d2.getBestLapTime()))
			.map(racer -> formatter.format(racer, index.getAndIncrement()))
			.forEach(System.out::print);
	}
}
