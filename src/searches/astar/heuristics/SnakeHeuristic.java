package searches.astar.heuristics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import game.Position;
import main.Config;

public class SnakeHeuristic implements IHeuristics {
    private Map<Byte, Position> solvedPositions = new HashMap<>();

    public SnakeHeuristic(byte[][] solvedTiles) {
        for (byte i = 0; i < solvedTiles.length; i++) {
            for (byte j = 0; j < solvedTiles[0].length; j++) {
                solvedPositions.put(solvedTiles[i][j], new Position(i, j));
            }

        }

    }

    ArrayList<SnakeEl> snakes;


    private int[]
            snakeValuesX = {0, 0, 0, 0, 1, 2, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1},
            snakeValuesY = {3, 2, 1, 0, 0, 0, 0, 1, 2, 3, 3, 2, 1, 1, 2, 3},
            snakeValues = {13, 9, 5, 1, 2, 3, 4, 8, 12, 0, 15, 11, 7, 6, 10, 14};

    private class SnakeEl {
        public SnakeEl next, prev;
        int value;

        public SnakeEl(int value) {
            this.value = value;
        }

    }

    float k = 1;

    @Override
    public float getDistance(byte[][] tiles) {
        float distance = 0;

        for (int i = 0; i < snakeValuesX.length; i++) {
            int v = snakeValues[i]; //13
            int myId = -1;
            int myIdN = -1;
            for (int j = 0; j < snakeValuesX.length; j++) {
                if (tiles[snakeValuesY[j]][snakeValuesX[j]] == v) {
                    myId = j; // 0
                }
            }

            for (int n = 1; n < snakeValuesX.length; n++) {
                int in = myId + n;
                if (in >= snakeValuesX.length) in -= snakeValuesX.length;
                int nv = snakeValues[in];
                for (int j = 0; j < snakeValuesX.length; j++) {
                    if (tiles[snakeValuesY[j]][snakeValuesX[j]] == nv) {
                        myIdN = j;
                    }
                }

                if (myId > myIdN)
                    myIdN += snakeValuesX.length;
                int dis = myIdN - myId - n;

                distance += Math.max(0, dis * 1f / n);
            }

            int dis = myId - i;
            if (dis < 0) dis += snakeValuesX.length;
            if (dis > 0) dis = 1;
            distance += dis;

        }
        return distance;
    }
}
