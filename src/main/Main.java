package main;

import game.BoardState;
import game.State;
import game.TileGenerator;
import searches.IPuzzleSolver;
import searches.astar.AStar;
import searches.depth.DepthFirstSearch;
import searches.width.WidthFirstSearch;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        State initialState = new State(TileGenerator.generate(BoardState.RANDOM));

        Main.printTiles(initialState.getTiles());
//        IPuzzleSolver solver = new AStar();
        IPuzzleSolver solver = new DepthFirstSearch();
//        IPuzzleSolver solver = new WidthFirstSearch();
        List<State> solution = null;
        try {
            solution = solver.findBestPath(initialState);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert solution != null;
        System.out.print(solution.size());
        for (State s : solution) {
            Main.printTiles(s.getTiles());
        }
    }

    private static void printTiles(byte[][] tiles) {
        for (byte[] tile : tiles) {
            for (int j = 0; j < tiles[0].length; j++) {
                System.out.print(tile[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
