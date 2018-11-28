package searches.astar.heuristics;

import java.util.HashMap;
import java.util.Map;

import game.Position;
import main.Config;

public class HammingHeuristic implements IHeuristics {
    private Map<Byte, Position> solvedPositions = new HashMap<>();

    public HammingHeuristic(byte[][] solvedTiles) {
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
        int ctr = 1;
        for (byte i = 0; i < tiles.length; i++) {
            for (byte j = 0; j < tiles[0].length; j++) {
            	if(tiles[i][j] == 0) continue;
                goalPosition = solvedPositions.get(tiles[i][j]);
                if (goalPosition.x != i || goalPosition.y != j)
                distance += 1;
            }
        }
        return distance;
    }
}