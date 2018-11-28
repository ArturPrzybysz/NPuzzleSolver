//package searches.astar.heuristics;
//
//import game.Position;
//import main.Config;
//import searches.astar.heuristics.IHeuristics;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class OvereagerHeuristics implements IHeuristics {
//    private Map<Byte, Position> solvedPositions = new HashMap<>();
//
//    public OvereagerHeuristics(byte[][] solvedTiles) {
//        for (byte i = 0; i < solvedTiles.length; i++) {
//            {
//                for (byte j = 0; j < solvedTiles[0].length; j++) {
//                    solvedPositions.put(solvedTiles[i][j], new Position(i, j));
//                }
//            }
//        }
//    }
//
//    @Override
//    public float getDistance(byte[][] tiles) {
//        float distance = 0;
//        int multiplier;
//        Position goalPosition;
//        for (byte i = 0; i < tiles.length; i++) {
//            for (byte j = 0; j < tiles[0].length; j++) {
//                goalPosition = solvedPositions.get(tiles[i][j]);
//
//                multiplier = Config.size - Math.min(i, j);
//                distance += Math.pow(multiplier * Math.abs(goalPosition.x - i), 2);
//                distance += Math.pow(multiplier * Math.abs(goalPosition.y - j), 2);
//            }
//        }
//        return distance;
//    }
//}
