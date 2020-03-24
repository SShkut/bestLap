package com.foxminded.f1_best_lap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		Path startFilePath = Paths.get("C:\\dev\\eclipse-workspace\\f1-best-lap\\src\\main\\resources\\files\\start.log");
		Path endFilePath = Paths.get("C:\\dev\\eclipse-workspace\\f1-best-lap\\src\\main\\resources\\files\\end.log");
		List<Lap> startData = LapDataParser.parse(startFilePath);
		List<Lap> endData = LapDataParser.parse(endFilePath);
		List<Lap> report = LapReport.createReport(startData, endData);
		print(Paths.get("C:\\dev\\eclipse-workspace\\f1-best-lap\\src\\main\\resources\\files\\abbreviations.txt"), report);
	}
	
	private static void print(Path abbreviationFilePath, List<Lap> report) {
		Map<String, String> abbreviations = new HashMap<>();
		char[] offset = new char[20];
		Arrays.fill(offset, ' ');
		
		try(Stream<String> stream = Files.lines(abbreviationFilePath))	{
			abbreviations = stream.map(line -> Arrays.asList(line.split("_"))).collect(Collectors.toMap(s -> s.get(0), s -> s.get(1) + "_" + s.get(2)));
		} catch(IOException e) {
			System.out.println("Can't read file at path: " + abbreviationFilePath);
		}
		
		Collections.sort(report, (l1, l2) -> l1.getTime().compareTo(l2.getTime()));
		int chartNumber = 0;
		for (Lap lap : report) {
			if (chartNumber == 15) {
				System.out.println("------------------------------------------------------------------------");
			}
			List<String> pilotAndTeam = Arrays.asList(abbreviations.get(lap.getPilotTeamAbbreviatoin()).split("_"));
			String pilotName = pilotAndTeam.get(0);
			String pilotTeam = pilotAndTeam.get(1);
			System.out.format("%-25s%-30s%-15s%n", String.valueOf(++chartNumber) + ". " + pilotName, "|" + pilotTeam, "|" + lap.getTime());
		}
	}
}
