package main;

import game.GeneratorFrajdy;

public class Generation {

	public static void main(String[] args) {
		GeneratorFrajdy.clean();
		GeneratorFrajdy.generujBFS();
		GeneratorFrajdy.generujDFS();
		GeneratorFrajdy.generujManh();
		GeneratorFrajdy.generujHamm();
		GeneratorFrajdy.generujYolo();
	}

}
