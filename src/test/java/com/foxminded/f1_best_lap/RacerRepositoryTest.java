package com.foxminded.f1_best_lap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class RacerRepositoryTest {
	
	@InjectMocks
	RacerRepository racerRepository;
	
	@Mock
	FileReader fileReader;

	@BeforeEach
	void setUp() {
		initMocks(this);
	}

	@Test
	void givenCorrectData_whenGetRacers_thenReturnRacersList() throws IOException {	
		List<Racer> expected = new ArrayList<>(); 
		when(fileReader.readFile("start.txt")).thenReturn(Stream.of("LHM2018-05-24_12:18:20.125"));
		when(fileReader.readFile("end.txt")).thenReturn(Stream.of("LHM2018-05-24_12:19:32.585"));
		when(fileReader.readFile("abbreviations.txt")).thenReturn(Stream.of("LHM_Lewis Hamilton_MERCEDES"));
				
		Duration duration = Duration.ofMillis(72460);
		expected.add(new Racer("LHM", "Lewis Hamilton", "MERCEDES", duration));
		
		List<Racer> actual = racerRepository.getRacers();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void givenEmptyStream_whenGetRacers_thenReturnEmptyList() throws IOException {
		List<Racer> expected = new ArrayList<>(); 
		when(fileReader.readFile("start.txt")).thenReturn(Stream.empty());
		when(fileReader.readFile("end.txt")).thenReturn(Stream.of("LHM2018-05-24_12:19:32.585"));
		when(fileReader.readFile("abbreviations.txt")).thenReturn(Stream.empty());
		
		List<Racer> actual = racerRepository.getRacers();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void givenEmptyString_whenGetRacers_thenReturnEmptyList() throws IOException {
		List<Racer> expected = new ArrayList<>(); 
		when(fileReader.readFile("start.txt")).thenReturn(Stream.of(""));
		when(fileReader.readFile("end.txt")).thenReturn(Stream.of("LHM2018-05-24_12:19:32.585"));
		when(fileReader.readFile("abbreviations.txt")).thenReturn(Stream.of(""));
		
		List<Racer> actual = racerRepository.getRacers();
		
		assertEquals(expected, actual);
	}
}
