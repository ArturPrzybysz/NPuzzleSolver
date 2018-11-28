package game;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import build.Build;
import main.Main;
import searches.IPuzzleSolver;
import searches.SolverFactory;

public class GeneratorFrajdy {
	
	static String sourcePath = "C:\\Users\\Wojciech\\Desktop\\hehe\\";
	static String outputPathInfo = "C:\\Users\\Wojciech\\Desktop\\heheinfo\\";
	static String outputPathSolution = "C:\\Users\\Wojciech\\Desktop\\hehesolution\\";
	
	private static void ulusz(String[] args) {
		try {
            Build.loadArgs(args);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        IPuzzleSolver solver = SolverFactory.getSolver(Build.strategy, Build.strategyParameter);
        State initialState = new State(Build.tiles);


        List<State> solution = null;
        long startTime = System.nanoTime();
        try {
            solution = solver.findPath(initialState);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long timeDiff = System.nanoTime() - startTime;
        Build.saveSolution(solution);
        Build.saveInfo(solution, (timeDiff / 1000) / 1000.0, solver);
	}
	
	private static void generujFrajde(String method, String methodParameter) {
		String[] args = new String[5];
		args[0] = method;
		args[1] = methodParameter;
		int ctr = 0;
		File dir = new File(sourcePath);
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {

		    for (File child : directoryListing) {
		    	args[2] = child.getAbsolutePath();
		    	args[3] = outputPathSolution + "\\" + method + "_" + methodParameter + ctr + ".txt";
				args[4] = outputPathInfo + "\\" + method + "_" + methodParameter + ctr + ".txt";
				ulusz(args);
				ctr++;
		    }
		  }
	}
	
	static String[] orders = {
			"RDUL", "RDLU", "DRUL", "DRLU", "LUDR", "LURD", "ULDR", "ULRD"
	};
	
	public static void generujDFS() {
		for(String order: orders) {
			generujFrajde("dfs", order);
		}
	}
}
