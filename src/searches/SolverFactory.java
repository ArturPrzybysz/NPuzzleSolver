package searches;

import searches.astar.AStar;
import searches.depth.DepthFirstSearch;
import searches.width.BreadthFirstSearch;

public class SolverFactory {
   public static IPuzzleSolver getSolver(String solverName) {
        switch (solverName) {
            case "dfs":
                return new DepthFirstSearch();
            case "bfs":
                return new BreadthFirstSearch();
            case "astr":
                return new AStar();
            default:
                return null;
        }

    }
}
