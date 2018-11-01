import java.util.HashMap;
import java.util.Map;

public class Manhattan implements IMetrics {
    private Map<Byte, Position> solvedPositions = new HashMap<>();

    Manhattan(byte[][] solvedTiles) {
        for (byte i = 0; i < solvedTiles.length; i++) {
            {
                for (byte j = 0; j < solvedTiles.length; j++) {
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
