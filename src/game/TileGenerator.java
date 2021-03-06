package game;

import main.Config;

import java.util.List;

import static java.lang.Math.random;

public class TileGenerator {
    public static byte[][] generate(BoardState boardState) {
        byte[][] tiles = new byte[Config.width][Config.height];
        switch (boardState) {
            case CUSTOM:
                tiles = new byte[][]{
                        {14, 6, 12, 0},
                        {3, 10, 7, 15},
                        {1, 2, 11, 13},
                        {4, 5, 8, 9}};
                break;
            case SOLVED:
                byte tileNumber = 1;
                for (byte i = 0; i < Config.width; i++) {
                    for (byte j = 0; j < Config.height; j++) {
                        tiles[i][j] = tileNumber;
                        tileNumber++;
                    }
                }
                tiles[Config.width - 1][Config.height - 1] = 0;
                break;
            case RANDOM:
                List<State> states = new State(Config.solvedTiles).getAvailableStates();
                State state = new State(states.get(0).getTiles());
                for (int i = 0; i < 80; i++) {
                    state = state.getAvailableStates().get((byte) (random() * 10) % state.getAvailableStates().size());
                }
                tiles = state.getTiles();
        }
        return tiles;
    }
}

