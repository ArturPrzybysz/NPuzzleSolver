package main;

import game.State;
import build.Build;
import searches.IPuzzleSolver;
import searches.SolverFactory;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        try {
            Build.loadArgs(args);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        IPuzzleSolver solver = SolverFactory.getSolver(Build.strategy, Build.strategyParameter);
        State initialState = new State(Build.tiles);


        List<State> solution = null;
        long startTime = System.nanoTime();
        try {
            solution = solver.findPath(initialState);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long timeDiff = System.nanoTime() - startTime;
        Build.saveSolution(solution);
        Build.saveInfo(solution, (timeDiff / 1000) / 1000.0, solver);
        System.out.print("solution size: ");
        System.out.println(Objects.requireNonNull(solution).size());
        for (State s : solution) {
            Main.printTiles(s.getTiles());
        }

    }

    public static void printTiles(byte[][] tiles) {
        for (byte[] tile : tiles) {
            for (int j = 0; j < tiles[0].length; j++) {
                System.out.print(tile[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
