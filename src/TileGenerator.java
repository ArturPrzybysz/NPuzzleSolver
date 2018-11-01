import java.util.List;

import static java.lang.Math.random;

class TileGenerator {
    static byte[][] generate(BoardState boardState, int size) {
        byte[][] tiles = new byte[size][size];
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
                for (byte i = 0; i < size; i++) {
                    for (byte j = 0; j < size; j++) {
                        if (i == size - 1 && j == size - 1) {
                            tiles[i][j] = 0;
                        } else {
                            tiles[i][j] = tileNumber;
                        }
                        tileNumber++;
                    }
                }
                break;
            case RANDOM:
                List<State> states = new State(Config.solvedTiles).getAvailableStates();
                State state = new State(states.get(0).getTiles());
                for (int i = 0; i < 500; i++) {
                    state = state.getAvailableStates().get((byte) (random() * 10) % state.getAvailableStates().size());
                }
                tiles = state.getTiles();
        }
        return tiles;
    }
}

