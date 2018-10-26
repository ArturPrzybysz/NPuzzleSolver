import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    Position zeroTilePosition;
    private byte[][] tiles;

    public Board(byte size, BoardState boardState) {
        tiles = new byte[size][size];

        switch (boardState) {
            case SOLVED:
                byte tileNumber = 1;
                for (byte i = 0; i < size; i++) {
                    for (byte j = 0; j < size; j++) {
                        tiles[i][j] = tileNumber;
                        tileNumber++;
                    }
                }
                break;
            case RANDOM:
                do {
                    List<Byte> tileNumbers = new ArrayList<>();
                    for (byte i = 0; i < size * size; i++) {
                        tileNumbers.add(i);
                    }
                    Collections.shuffle(tileNumbers);
                    int tileIdx = 0;
                    for (byte i = 0; i < size; i++) {
                        for (byte j = 0; j < size; j++) {
                            tiles[i][j] = tileNumbers.get(tileIdx);

                            if (tiles[i][j] == 0) {
                                zeroTilePosition = new Position(i, j);
                            }
                            tileIdx++;
                        }
                    }
                }
                while (!SolvabilityTester.check(this));
        }
    }


    public State[] getAvailableStates() {

        return null;
    }
}

//solvability: https://www.geeksforgeeks.org/check-instance-15-puzzle-solvable/