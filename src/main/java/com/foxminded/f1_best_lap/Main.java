package com.foxminded.f1_best_lap;

public class Main {

	public static void main(String[] args) {
		RacerRepository racerRepository = new RacerRepository();
		TopRacersFormatter topRacersFormatter = new TopRacersFormatter();
		int qualifiedNumber = 15;
		System.out.println(topRacersFormatter.format(racerRepository.getRacers(), qualifiedNumber));
	}
}
