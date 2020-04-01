package com.foxminded.f1_best_lap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TopRacersFormatterTest {
	
	private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
	
	private TopRacersFormatter topRacersFormatter;

	@BeforeEach
	void setUp() {
		topRacersFormatter = new TopRacersFormatter();
	}

	@Test
	void givenDriverAndIndex_whenFormat_thenReturnFormattedString() {
		List<Racer> racers = new ArrayList<>();
		racers.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.ofMillis(72013)));
		racers.add(new Racer("VBM", "Valtteri Bottas", "MERCEDES", Duration.ofMillis(72434)));
		int qualifiedNumber = 15;

		String expected = String.format("%-25s%-30s%-15s%n%-25s%-30s%-15s%n", 
				"1. " + racers.get(0).getName(), "|" + racers.get(0).getTeam(), "|" + durationToTime(racers.get(0).getBestLapTime())
				,"2. " + racers.get(1).getName(), "|" + racers.get(1).getTeam(), "|" + durationToTime(racers.get(1).getBestLapTime()));
		String actual = topRacersFormatter.format(racers, qualifiedNumber);
		
		assertEquals(expected, actual);
	}

	@Test
	void givenLastQualifiedDriver_whenFormat_thenReturnFormattedStringWithDelimiter() {
		List<Racer> racers = new ArrayList<>();
		racers.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.ofMillis(72013)));
		racers.add(new Racer("VBM", "Valtteri Bottas", "MERCEDES", Duration.ofMillis(72434)));
		int qualifiedNumber = 1;
		
		String expected = String.format("%-25s%-30s%-15s%n%s%n%-25s%-30s%-15s%n", "1. " + racers.get(0).getName(), "|" + racers.get(0).getTeam(), "|" + durationToTime(racers.get(0).getBestLapTime())
				, "------------------------------------------------------------------------"
				, "2. " + racers.get(1).getName(), "|" + racers.get(1).getTeam(), "|" + durationToTime(racers.get(1).getBestLapTime()));
		String actual = topRacersFormatter.format(racers, qualifiedNumber);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void givenLessThanZeroQualifiedNumber_whenFormat_thenEmptyString() {
		List<Racer> racers = new ArrayList<>();
		int qualifiedNumber = -15;
		
		String expected = "";
		String actual = topRacersFormatter.format(racers, qualifiedNumber);
		
		assertEquals(expected, actual);
	}
	
	LocalTime durationToTime(Duration duration) {
		return LocalTime.ofSecondOfDay(duration.getSeconds()).plusNanos(duration.getNano());
	}
}
