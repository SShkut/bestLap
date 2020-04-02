package com.foxminded.f1_best_lap;

import static java.util.Comparator.comparing;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TopRacersFormatter {
	
	public String format(List<Racer> racers, int qualifiedNumber) {
		if (racers.isEmpty() || qualifiedNumber <= 0) {
			return "";
		}
		
		AtomicInteger index = new AtomicInteger(1);
		return racers.stream()
				.sorted(comparing(Racer::getBestLapTime))
				.map(racer -> formatRacer(racer, index.getAndIncrement(), qualifiedNumber))
				.collect(Collectors.joining());
	}

	private String formatRacer(Racer racer, int index, int qualifiedNumber) {				
		char[] offset = new char[20];
		Arrays.fill(offset, ' ');

		LocalTime timeSpent = LocalTime.ofSecondOfDay(racer.getBestLapTime().getSeconds()).plusNanos(racer.getBestLapTime().getNano());
		if (index == qualifiedNumber) {
			return String.format("%-25s%-30s%-15s%n", String.valueOf(index) + ". " + racer.getName(), "|" + racer.getTeam(), "|" + timeSpent)
					+ String.format("%s%n", "------------------------------------------------------------------------");			
		}
		return String.format("%-25s%-30s%-15s%n", String.valueOf(index) + ". " + racer.getName(), "|" + racer.getTeam(), "|" + timeSpent);
	}
}

