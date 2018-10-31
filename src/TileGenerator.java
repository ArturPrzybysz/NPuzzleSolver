import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TileGenerator {
    static int[][] generate(BoardState boardState, int size) {
        int[][] tiles = new int[size][size];
        switch (boardState) {
            case SOLVED:
                int tileNumber = 1;
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        tiles[i][j] = tileNumber;
                        tileNumber++;
                    }
                }
                break;
            case RANDOM:
                do {
                    List<Integer> tileNumbers = new ArrayList<>();
                    for (int i = 0; i < size * size; i++) {
                        tileNumbers.add(i);
                    }
                    Collections.shuffle(tileNumbers);
                    int tileIdx = 0;
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            tiles[i][j] = tileNumbers.get(tileIdx);
                            tileIdx++;
                        }
                    }
                }
                while (!SolvabilityTester.check(tiles));
        }
        return tiles;
    }
}

//solvability: https://www.geeksforgeeks.org/check-instance-15-puzzle-solvable/