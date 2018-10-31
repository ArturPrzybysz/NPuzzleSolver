import java.util.ArrayList;
import java.util.List;

class State {
    private Position zeroTilePosition;
    private int[][] tiles;
    private double distance;

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

    private State(int[][] tiles, Position zeroTilePosition) {
        this.tiles = tiles;
        this.distance = Config.metrics.getDistance(this.tiles);
        this.zeroTilePosition = zeroTilePosition;
    }

    public int[][] getTiles() {
        return tiles;
    }

    public double getDistance() {
        return distance;
    }

    public List<State> getAvailableStates() {

        List<State> states = new ArrayList<>();
        int[][] tmpTiles = tiles.clone();
        for (int i = zeroTilePosition.x + 1; i < this.tiles.length; i++) {
            tmpTiles[i - 1][zeroTilePosition.y] = tmpTiles[i][zeroTilePosition.y];
            tmpTiles[i][zeroTilePosition.y] = 0;
            states.add(new State(tmpTiles.clone(), new Position(i, zeroTilePosition.y)));
        }

        tmpTiles = tiles.clone();
        for (int i = zeroTilePosition.x - 1; i >= 0; i++) {
            tmpTiles[i + 1][zeroTilePosition.y] = tmpTiles[i][zeroTilePosition.y];
            tmpTiles[i][zeroTilePosition.y] = 0;
            states.add(new State(tmpTiles.clone(), new Position(i, zeroTilePosition.y)));
        }

        tmpTiles = tiles.clone();
        for (int i = zeroTilePosition.y + 1; i < this.tiles.length; i++) {
            tmpTiles[zeroTilePosition.x][i - 1] = tmpTiles[zeroTilePosition.x][i];
            tmpTiles[zeroTilePosition.x][i] = 0;
            states.add(new State(tmpTiles.clone(), new Position(zeroTilePosition.x, i)));
        }

        tmpTiles = tiles.clone();
        for (int i = zeroTilePosition.y - 1; i >= 0; i++) {
            tmpTiles[zeroTilePosition.x][i + 1] = tmpTiles[zeroTilePosition.x][i];
            tmpTiles[zeroTilePosition.x][i] = 0;
            states.add(new State(tmpTiles.clone(), new Position(zeroTilePosition.x, i)));
        }
        return states;
    }

}
