import java.util.List;

import static java.lang.Math.random;

class TileGenerator {
    static int[][] generate(BoardState boardState, int size) {
        int[][] tiles = new int[size][size];
        switch (boardState) {
            case CUSTOM:
                tiles = new int[][]{
                        {14, 1, 7, 2},
                        {10, 4, 8, 13},
                        {6, 12, 9, 11},
                        {5, 15, 3, 0}};
                break;
            case SOLVED:
                int tileNumber = 1;
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
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
                for (int i = 0; i < 55; i++) {
                    state = state.getAvailableStates().get((int) (random() * 10) % 6);
                }
                tiles = state.getTiles();
        }
        return tiles;
    }
}

