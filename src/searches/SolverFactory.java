package searches;

import main.Config;
import searches.astar.AStar;
import searches.astar.heuristics.HeuristicsFabric;
import searches.astar.heuristics.IHeuristics;
import searches.depth.DepthFirstSearch;
import searches.width.BreadthFirstSearch;

public class SolverFactory {
    public static IPuzzleSolver getSolver(String solverName, String strategyParameter) {
        switch (solverName) {
            case "dfs":
                return new DepthFirstSearch(strategyParameter);
            case "bfs":
                return new BreadthFirstSearch(strategyParameter);
            case "astr":
                try {
                    Config.heuristics = HeuristicsFabric.get(strategyParameter);
                    return new AStar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            default:
                return null;
        }

    }
}
