package main;

import game.BoardState;
import game.TileGenerator;
import searches.astar.heuristics.IHeuristics;
import searches.astar.heuristics.ManhattanHeuristics;

public class Config {
    static {
        width = 4;
        height = 4;
        solvedTiles = TileGenerator.generate(BoardState.SOLVED);
        heuristics = new ManhattanHeuristics(Config.solvedTiles);
    }

    public static int size;
    public static int width;
    public static int height;
    public static byte[][] solvedTiles;
    public static IHeuristics heuristics;
    public static String order;
}
