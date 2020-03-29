package com.foxminded.f1_best_lap;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacerRepository {
	
	private final RacerDAO racerDAO;	

	public RacerRepository(RacerDAO racerDAO) {
		this.racerDAO = racerDAO;
	}

	public List<Racer> getRacers() {
		
		RacerConverter racerConverter = new RacerConverter();
		Map<String, LocalDateTime> qualifyingStartData = racerDAO.getDataFrom("start.txt");
		Map<String, LocalDateTime> qualifyingEndData = racerDAO.getDataFrom("end.txt"); 
				
		try (Stream<String> stream = Files.lines(racerDAO.getAbsolutePath("abbreviations.txt"))) {
			return stream
					.map(line -> racerConverter.convert(line, qualifyingStartData, qualifyingEndData))
					.collect(Collectors.toList());
		} catch (IOException e) {
			return new ArrayList<>();
		}
	}
}
