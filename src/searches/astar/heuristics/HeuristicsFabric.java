package searches.astar.heuristics;

import main.Config;

public class HeuristicsFabric {
    public static IHeuristics get(String heuristicName) throws Exception {
        switch (heuristicName) {
//            case "hamm":
//                //TODO
            case "manh":
                return new ManhattanHeuristics(Config.solvedTiles);
            case "yolo":
                return new OvereagerHeuristics(Config.solvedTiles);
            default:
                throw new Exception("No such heuristics");
        }
    }
}
