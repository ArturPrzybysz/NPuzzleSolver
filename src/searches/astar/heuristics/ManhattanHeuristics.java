package searches.astar.heuristics;

import game.Position;
import searches.astar.heuristics.IHeuristics;

import java.util.HashMap;
import java.util.Map;

public class ManhattanHeuristics implements IHeuristics {
    private Map<Byte, Position> solvedPositions = new HashMap<>();

    public ManhattanHeuristics(byte[][] solvedTiles) {
        for (byte i = 0; i < solvedTiles.length; i++) {
            {
                for (byte j = 0; j < solvedTiles[0].length; j++) {
                    solvedPositions.put(solvedTiles[i][j], new Position(i, j));
                }
            }
        }
    }

    @Override
    public float getDistance(byte[][] tiles) {
        float distance = 0;
        Position goalPosition;
        for (byte i = 0; i < tiles.length; i++) {
            for (byte j = 0; j < tiles[0].length; j++) {
                goalPosition = solvedPositions.get(tiles[i][j]);
                distance += Math.abs(goalPosition.x - i);
                distance += Math.abs(goalPosition.y - j);
            }
        }
        return distance;
    }
}
