package searches;

import game.BoardState;
import game.TileGenerator;
import main.Config;
import searches.astar.AStar;
import searches.astar.heuristics.HeuristicsFabric;
import searches.depth.DepthFirstSearch;
import searches.breadth.BreadthFirstSearch;

public class SolverFactory {
    public static IPuzzleSolver getSolver(String solverName, String strategyParameter) {
        Config.solvedTiles = TileGenerator.generate(BoardState.SOLVED);

        switch (solverName) {
            case "dfs":
                Config.order = strategyParameter;
                return new DepthFirstSearch();
            case "bfs":
                Config.order = strategyParameter;
                return new BreadthFirstSearch();
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
