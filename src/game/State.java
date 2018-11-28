package game;

import main.Config;
import main.Util;
import searches.astar.heuristics.ManhattanHeuristics;

import java.util.ArrayList;
import java.util.List;

public class State {
    private State parent = null;
    private Position zeroTilePosition;
    private byte[][] tiles;
    private double distance;
    private double cumulativeDistance = 0;
    public char lastDirection = ' ';
    public int depth = 0;

    public State(byte[][] tiles) {
        this.tiles = tiles;
        if (Config.heuristics == null) {
            Config.heuristics = new ManhattanHeuristics(Config.solvedTiles);
        }
        this.distance = Config.heuristics.getDistance(this.tiles);
        for (short i = 0; i < tiles.length; i++) {
            for (short j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j] == 0) {
                    this.zeroTilePosition = new Position(i, j);
                    break;
                }
            }
        }
    }

    private State(byte[][] tiles, Position zeroTilePosition, State parent, char lastDirection) {
        this.parent = parent;
        this.tiles = tiles;
        this.distance = Config.heuristics.getDistance(tiles);
        this.cumulativeDistance = parent.getCumulativeDistance() + distance;
        this.depth = parent.depth + 1;
        this.zeroTilePosition = zeroTilePosition;
        this.lastDirection = lastDirection;
    }

    public byte[][] getTiles() {
        return tiles;
    }

    public double getDistance() {
        return distance;
    }

    public List<State> getAvailableStates() {
        List<State> states = new ArrayList<>();
        for (int i = 0; i < Config.order.length(); i++) {
            byte[][] tmpTiles = Util.copy2DArray(tiles);
            State newState;
            switch (Config.order.charAt(i)) {
                case 'L':
                    if (lastDirection == 'R') continue;
                    newState = moveLeft(tmpTiles);
                    if (newState != null)
                        states.add(newState);
                    break;
                case 'R':
                    if (lastDirection == 'L') continue;
                    newState = moveRight(tmpTiles);
                    if (newState != null)
                        states.add(newState);
                    break;
                case 'U':
                    if (lastDirection == 'D') continue;
                    newState = moveUp(tmpTiles);
                    if (newState != null)
                        states.add(newState);
                    break;
                case 'D':
                    if (lastDirection == 'U') continue;

                    newState = moveDown(tmpTiles);
                    if (newState != null)
                        states.add(newState);
                    break;
            }
        }
        return states;
    }

    private State moveUp(byte[][] tiles) {
        int i = zeroTilePosition.x - 1;
        if (i - 1 < 0) return null;

        tiles[i + 1][zeroTilePosition.y] = tiles[i][zeroTilePosition.y];
        tiles[i][zeroTilePosition.y] = 0;
        return new State(Util.copy2DArray(tiles), new Position(i, zeroTilePosition.y), this, 'U');
    }

    private State moveDown(byte[][] tiles) {
        int i = zeroTilePosition.x + 1;
        if (i >= Config.width) return null;

        tiles[i - 1][zeroTilePosition.y] = tiles[i][zeroTilePosition.y];
        tiles[i][zeroTilePosition.y] = 0;
        return new State(Util.copy2DArray(tiles), new Position(i, zeroTilePosition.y), this, 'D');
    }

    private State moveRight(byte[][] tiles) {
        int i = zeroTilePosition.y + 1;
        if (i >= Config.height) return null;

        tiles[zeroTilePosition.x][i - 1] = tiles[zeroTilePosition.x][i];
        tiles[zeroTilePosition.x][i] = 0;
        return new State(Util.copy2DArray(tiles), new Position(zeroTilePosition.x, i), this, 'R');
    }

    private State moveLeft(byte[][] tiles) {
        int i = zeroTilePosition.y - 1;
        if (i < 0) return null;
        tiles[zeroTilePosition.x][i + 1] = tiles[zeroTilePosition.x][i];
        tiles[zeroTilePosition.x][i] = 0;
        return new State(Util.copy2DArray(tiles), new Position(zeroTilePosition.x, i), this, 'L');
    }

    public State getParent() {
        return parent;
    }

    public double getCumulativeDistance() {
        return cumulativeDistance;
    }
}
