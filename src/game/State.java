package game;

import main.Config;
import main.Util;

import java.util.ArrayList;
import java.util.List;

public class State {
    private State parent = null;
    private Position zeroTilePosition;
    private byte[][] tiles;
    private double distance;
    private double cumulativeDistance = 0;
    public String lastMove;
    public int depth;

    public State(byte[][] tiles) {
        this.tiles = tiles;
        this.distance = Config.metrics.getDistance(this.tiles);
        for (short i = 0; i < tiles.length; i++) {
            for (short j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j] == 0) {
                    this.zeroTilePosition = new Position(i, j);
                }
            }
        }
        depth = 0;
    }

    private State(byte[][] tiles, Position zeroTilePosition, State parent) {
        this.parent = parent;
        this.tiles = tiles;
        this.distance = Config.metrics.getDistance(tiles);
        this.cumulativeDistance = parent.getCumulativeDistance() + distance;
        this.depth = parent.depth+1;
        this.zeroTilePosition = zeroTilePosition;
    }
    
    private State(byte[][] tiles, Position zeroTilePosition, State parent, String lastMove) {
        this.parent = parent;
        this.tiles = tiles;
        this.distance = Config.metrics.getDistance(tiles);
        this.cumulativeDistance = parent.getCumulativeDistance() + distance;
        this.depth = parent.depth+1;
        this.zeroTilePosition = zeroTilePosition;
        this.lastMove = lastMove;
    }

    public byte[][] getTiles() {
        return tiles;
    }

    public double getDistance() {
        return distance;
    }

    public List<State> getAvailableStates() {

        List<State> states = new ArrayList<>();
        byte[][] tmpTiles = Util.copy2DArray(tiles);
        for (int i = zeroTilePosition.x + 1; i < this.tiles.length; i++) {
            tmpTiles[i - 1][zeroTilePosition.y] = tmpTiles[i][zeroTilePosition.y];
            tmpTiles[i][zeroTilePosition.y] = 0;
            states.add(new State(Util.copy2DArray(tmpTiles), new Position(i, zeroTilePosition.y), this, "D"));
            break;
        }

        tmpTiles = Util.copy2DArray(tiles);
        for (int i = zeroTilePosition.x - 1; i >= 0; i--) {
            tmpTiles[i + 1][zeroTilePosition.y] = tmpTiles[i][zeroTilePosition.y];
            tmpTiles[i][zeroTilePosition.y] = 0;
            states.add(new State(Util.copy2DArray(tmpTiles), new Position(i, zeroTilePosition.y), this, "U"));
            break;
        }

        tmpTiles = Util.copy2DArray(tiles);
        for (int i = zeroTilePosition.y + 1; i < this.tiles.length; i++) {
            tmpTiles[zeroTilePosition.x][i - 1] = tmpTiles[zeroTilePosition.x][i];
            tmpTiles[zeroTilePosition.x][i] = 0;
            states.add(new State(Util.copy2DArray(tmpTiles), new Position(zeroTilePosition.x, i), this, "R"));
            break;
        }

        tmpTiles = Util.copy2DArray(tiles);
        for (int i = zeroTilePosition.y - 1; i >= 0; i--) {
            tmpTiles[zeroTilePosition.x][i + 1] = tmpTiles[zeroTilePosition.x][i];
            tmpTiles[zeroTilePosition.x][i] = 0;
            states.add(new State(Util.copy2DArray(tmpTiles), new Position(zeroTilePosition.x, i), this, "L"));
            break;
        }
        return states;
    }

    public State getParent() {
        return parent;
    }

    public double getCumulativeDistance() {
        return cumulativeDistance;
    }
}
