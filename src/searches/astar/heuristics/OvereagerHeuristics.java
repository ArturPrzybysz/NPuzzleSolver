package searches.astar.heuristics;

import game.Position;
import main.Config;
import searches.astar.heuristics.IHeuristics;

import java.util.HashMap;
import java.util.Map;

public class OvereagerHeuristics implements IHeuristics {
    private Map<Byte, Position> solvedPositions = new HashMap<>();
    private float[] weights;

    private void initWeights(int size) {
        weights = new float[size];
        for (int i = 0; i < size; i++) {
            weights[i] = i + 1;
        }
    }

    public OvereagerHeuristics(byte[][] solvedTiles) {
        for (byte i = 0; i < solvedTiles.length; i++) {
            {
                for (byte j = 0; j < solvedTiles[0].length; j++) {
                    solvedPositions.put(solvedTiles[i][j], new Position(i, j));
                }
            }
        }

        initWeights(Math.max(Config.width, Config.height));
    }

    @Override
    public float getDistance(byte[][] tiles) {
        float distance = 0;
        int tier;
        Position goalPosition;
        for (byte i = 0; i < Config.width; i++) {
            for (byte j = 0; j < Config.height; j++) {
                goalPosition = solvedPositions.get(tiles[i][j]);

                tier = Math.max(Config.width, Config.height) - Math.min(i, j) - 1;
                float weight = weights[tier];

                distance += weight * Math.abs(goalPosition.x - i);
                distance += weight * Math.abs(goalPosition.y - j);
            }
        }
        return distance;
    }
}
