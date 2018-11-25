package main;

import game.BoardState;
import game.State;
import game.TileGenerator;
import hehe.Loader;
import searches.astar.AStar;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    	for (String s: args) {
    		System.out.println(s);
    	}
    	
    	try {
			Loader.load(args);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
        //State initialState = new State(Loader.tiles);
    	State initialState = new State(TileGenerator.generate(BoardState.RANDOM));
        System.out.println("Initial");
        Main.printTiles(initialState.getTiles());
        List<State> solution = null;
        long startTime = System.nanoTime();
        try {
            solution = AStar.findBestPath(initialState);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long timeDiff = System.nanoTime()-startTime;
        assert solution != null;
        Loader.saveSolution(solution);
        Loader.saveInfo(solution, (timeDiff/1000)/1000.0);
        System.out.print("solution size: ");
        System.out.println(solution.size());
        for (State s : solution) {
            Main.printTiles(s.getTiles());
        }
        
    }

    public static void printTiles(byte[][] tiles) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                System.out.print(tiles[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }
}
