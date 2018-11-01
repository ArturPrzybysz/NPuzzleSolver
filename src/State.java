import java.util.ArrayList;
import java.util.List;

class State {
    private State parent = null;
    private Position zeroTilePosition;
    private int[][] tiles;
    private double distance;
    private double cumulativeDistance = 0;

    State(int[][] tiles) {
        this.tiles = tiles;
        this.distance = Config.metrics.getDistance(this.tiles);
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j] == 0) {
                    this.zeroTilePosition = new Position(i, j);
                }
            }
        }
    }

    private State(int[][] tiles, Position zeroTilePosition, State parent) {
        this.parent = parent;
        this.tiles = tiles;
        this.distance = Config.metrics.getDistance(tiles);
        this.cumulativeDistance = parent.getCumulativeDistance() + distance;

        this.zeroTilePosition = zeroTilePosition;
    }

    int[][] getTiles() {
        return tiles;
    }

    double getDistance() {
        return distance;
    }

    List<State> getAvailableStates() {

        List<State> states = new ArrayList<>();
        int[][] tmpTiles = Util.copy2DArray(tiles);
        for (int i = zeroTilePosition.x + 1; i < this.tiles.length; i++) {
            tmpTiles[i - 1][zeroTilePosition.y] = tmpTiles[i][zeroTilePosition.y];
            tmpTiles[i][zeroTilePosition.y] = 0;
            states.add(new State(Util.copy2DArray(tmpTiles), new Position(i, zeroTilePosition.y), this));
        }

        tmpTiles = Util.copy2DArray(tiles);
        for (int i = zeroTilePosition.x - 1; i >= 0; i--) {
            tmpTiles[i + 1][zeroTilePosition.y] = tmpTiles[i][zeroTilePosition.y];
            tmpTiles[i][zeroTilePosition.y] = 0;
            states.add(new State(Util.copy2DArray(tmpTiles), new Position(i, zeroTilePosition.y), this));
        }

        tmpTiles = Util.copy2DArray(tiles);
        for (int i = zeroTilePosition.y + 1; i < this.tiles.length; i++) {
            tmpTiles[zeroTilePosition.x][i - 1] = tmpTiles[zeroTilePosition.x][i];
            tmpTiles[zeroTilePosition.x][i] = 0;
            states.add(new State(Util.copy2DArray(tmpTiles), new Position(zeroTilePosition.x, i), this));
        }

        tmpTiles = Util.copy2DArray(tiles);
        for (int i = zeroTilePosition.y - 1; i >= 0; i--) {
            tmpTiles[zeroTilePosition.x][i + 1] = tmpTiles[zeroTilePosition.x][i];
            tmpTiles[zeroTilePosition.x][i] = 0;
            states.add(new State(Util.copy2DArray(tmpTiles), new Position(zeroTilePosition.x, i), this));
        }
        return states;
    }

    State getParent() {
        return parent;
    }

    public double getCumulativeDistance() {
        return cumulativeDistance;
    }
}
