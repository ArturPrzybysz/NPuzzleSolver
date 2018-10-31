import java.util.HashMap;
import java.util.Map;

public class Manhattan implements IMetrics {
    private Map<Integer, Position> solvedPositions = new HashMap<>();

    Manhattan(int[][] solvedTiles) {
        for (int i = 0; i < solvedTiles.length; i++) {
            {
                for (int j = 0; j < solvedTiles.length; j++) {
                    solvedPositions.put(solvedTiles[i][j], new Position(i, j));
                }
            }
        }
    }

    @Override
    public double getDistance(int[][] tiles) {
        double distance = 0;
        Position goalPosition;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                goalPosition = solvedPositions.get(tiles[i][j]);
                distance += Math.abs(goalPosition.x - i);
                distance += Math.abs(goalPosition.y - j);
            }
        }
        return distance;
    }
}
