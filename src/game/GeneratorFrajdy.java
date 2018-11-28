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
	
	static String sourcePath1_7 = "C:\\Users\\Wojciech\\Desktop\\hehe\\";
	static String outputPathDFS = "C:\\Users\\Wojciech\\Desktop\\heheout\\dfs\\";
	static String outputPathBFS = "C:\\Users\\Wojciech\\Desktop\\heheout\\bfs\\";
	static String outputPathMANH = "C:\\Users\\Wojciech\\Desktop\\heheout\\astar\\manh\\";
	static String outputPathHAMM = "C:\\Users\\Wojciech\\Desktop\\heheout\\astar\\hamm\\";
	static String outputPathYOLO = "C:\\Users\\Wojciech\\Desktop\\heheout\\astar\\yolo\\";
	
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
	
	private static void generujFrajde(String sourcePath, String output, String method, String methodParameter) {
		String[] args = new String[5];
		args[0] = method;
		args[1] = methodParameter;
		int ctr = 0;
		File dir = new File(sourcePath);
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {

		    for (File child : directoryListing) {
		    	args[2] = child.getAbsolutePath();
		    	args[3] = output + "\\solution\\" + method + "_" + methodParameter + ctr + ".txt";
				args[4] = output + "\\info\\" + method + "_" + methodParameter + ctr + ".txt";
				ulusz(args);
				ctr++;
		    }
		  }
	}
	private static void cleanDir(String dir) {
		File dir1 = new File(dir+"\\info");
		File dir2 = new File(dir+"\\solution");
		for(File file: dir1.listFiles()) 
		    if (!file.isDirectory()) 
		        file.delete();
		for(File file: dir2.listFiles()) 
		    if (!file.isDirectory()) 
		        file.delete();
		
	}
	public static void clean() {
		cleanDir(outputPathBFS);
		cleanDir(outputPathDFS);
		cleanDir(outputPathHAMM);
		cleanDir(outputPathMANH);
		cleanDir(outputPathYOLO);
	}
	
	static String[] orders = {
			"RDUL", "RDLU", "DRUL", "DRLU", "LUDR", "LURD", "ULDR", "ULRD"
	};
	
	public static void generujDFS() {
		for(String order: orders) {
			generujFrajde(sourcePath1_7, outputPathDFS, "dfs", order);
		}
	}
	
	public static void generujBFS() {
		for(String order: orders) {
			generujFrajde(sourcePath1_7, outputPathBFS, "bfs", order);
		}
	}
	
	public static void generujManh() {
		generujFrajde(sourcePath1_7, outputPathMANH, "astr", "manh");
	}
	
	public static void generujHamm() {
		generujFrajde(sourcePath1_7, outputPathHAMM, "astr", "hamm");
	}
	
	public static void generujYolo() {
		generujFrajde(sourcePath1_7, outputPathYOLO, "astr", "yolo");
	}
	
	
}
