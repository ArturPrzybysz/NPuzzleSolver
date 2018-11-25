package hehe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import game.State;
import searches.astar.AStar;

public class Loader {

	public static byte[][] tiles;
	public static String strategy;
	public static String strategyParemeter;
	public static String initialStatePath;
	public static String pathToSaveSolution;
	public static String pathToSaveInfo;
	
	public static void load(String[] args) throws IOException {
		 strategy = args[0];
		 strategyParemeter = args[1];
		 initialStatePath = args[2];
		 pathToSaveSolution = args[3];
		 pathToSaveInfo = args[4];
		
		//initial state
		File initialStateFile = new File(initialStatePath);
		BufferedReader br = new BufferedReader(new FileReader(initialStateFile)); 
		String st = br.readLine();
		String[] size = st.split(" ");
		tiles = new byte[Integer.parseInt(size[0])][Integer.parseInt(size[1])];
		int j = 0, i = 0;
		while ((st = br.readLine()) != null) {
			String[] fileValues = st.split(" ");
			for(String fileValue: fileValues) {
				tiles[j][i] = (byte) Integer.parseInt(fileValue);
				i++;
			}
			j++;
			i = 0;
		}
	}
	
	public static void saveSolution(List<State> solution) {
		try (PrintWriter out = new PrintWriter(pathToSaveSolution)) {
			if (solution == null)
				out.println(-1);
			else {
				String moves = "";
				out.println(solution.size()-1);
			    for (State state: solution) {
			    	if (state.lastMove != null)
			    		moves = state.lastMove + moves;
			    }
			    out.print(moves);
			}

		    out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void saveInfo(List<State> solution, double time) {
		try (PrintWriter out = new PrintWriter(pathToSaveInfo)) {
			if (solution == null)
				out.println(-1);
			else {
				out.println(solution.size()-1);
				out.println(AStar.checkedCtr);
				out.println(AStar.processedCtr);
				out.println(AStar.recursiveDepth);
				out.println(time);
			}

		    out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
