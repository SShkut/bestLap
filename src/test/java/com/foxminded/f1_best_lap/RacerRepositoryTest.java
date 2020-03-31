package com.foxminded.f1_best_lap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class RacerRepositoryTest {
	
	private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
	
	private List<Racer> expected;
	
	@InjectMocks
	RacerRepository racerRepository;
	
	@Mock
	FileReader fileReader;

	@BeforeEach
	void setUp() throws Exception {
		initMocks(this);
		expected = new ArrayList<>();
	}

	@Test
	void givenCorrectData_whenGetRacers_thenReturnRacersList() {
		when(fileReader.readFile("start.txt")).thenReturn(Stream.of("LHM2018-05-24_12:18:20.125"));
		when(fileReader.readFile("end.txt")).thenReturn(Stream.of("LHM2018-05-24_12:19:32.585"));
		when(fileReader.readFile("abbreviations.txt")).thenReturn(Stream.of("LHM_Lewis Hamilton_MERCEDES"));
		
		LocalDateTime startTime = LocalDateTime.parse("2018-05-24_12:18:20.125", PATTERN);
		LocalDateTime endTime = LocalDateTime.parse("2018-05-24_12:19:32.585", PATTERN);
		expected.add(new Racer("LHM", "Lewis Hamilton", "MERCEDES", Duration.between(startTime, endTime)));
		
		List<Racer> actual = racerRepository.getRacers();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void givenEmptyStream_whenGetRacers_thenReturnEmptyList() {
		when(fileReader.readFile("start.txt")).thenReturn(Stream.empty());
		when(fileReader.readFile("end.txt")).thenReturn(Stream.of("LHM2018-05-24_12:19:32.585"));
		when(fileReader.readFile("abbreviations.txt")).thenReturn(Stream.empty());
		
		List<Racer> actual = racerRepository.getRacers();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void givenEmptyString_whenGetRacers_thenReturnEmptyList() {
		when(fileReader.readFile("start.txt")).thenReturn(Stream.of(""));
		when(fileReader.readFile("end.txt")).thenReturn(Stream.of("LHM2018-05-24_12:19:32.585"));
		when(fileReader.readFile("abbreviations.txt")).thenReturn(Stream.of(""));
		
		List<Racer> actual = racerRepository.getRacers();
		
		assertEquals(expected, actual);
	}
}
