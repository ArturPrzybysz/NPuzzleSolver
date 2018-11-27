package searches.astar.heuristics;

import game.Position;
import searches.astar.heuristics.IHeuristics;

import java.util.HashMap;
import java.util.Map;

public class LimitingHeuristics implements IHeuristics {
    private Map<Byte, Position> solvedPositions = new HashMap<>();

    private int getSolvingDepth(byte[][] tiles) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                Position position = solvedPositions.get(tiles[i][j]);
                if (position.x != i || position.y != j) {
                    return Math.min(i, j);
                }
            }
        }
        return tiles.length;
    }

    public LimitingHeuristics(byte[][] solvedTiles) {
        for (byte i = 0; i < solvedTiles.length; i++) {
            for (byte j = 0; j < solvedTiles[0].length; j++) {
                solvedPositions.put(solvedTiles[i][j], new Position(i, j));
            }
        }
    }

    @Override
    public float getDistance(byte[][] tiles) {
        int depth = getSolvingDepth(tiles);

        int multiplier = tiles.length - depth + 1;
        float distance = 0;
        Position goalPosition;
        for (int i = depth; i < tiles.length; i++) {
            goalPosition = solvedPositions.get(tiles[i][depth]);
            distance += multiplier * Math.abs(goalPosition.x - depth);
            distance += multiplier * Math.abs(goalPosition.y - depth);
        }

        for (int i = depth; i < tiles.length; i++) {
            goalPosition = solvedPositions.get(tiles[depth][i]);
            distance += multiplier * Math.abs(goalPosition.x - depth);
            distance += multiplier * Math.abs(goalPosition.y - depth);
        }
        return distance;
    }
}
