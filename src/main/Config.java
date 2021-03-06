package main;

import game.BoardState;
import game.TileGenerator;
import searches.astar.heuristics.IHeuristics;
import searches.astar.heuristics.ManhattanHeuristics;

public class Config {
    static {
        order = "LURD";
    }

    public static int width;
    public static int height;
    public static byte[][] solvedTiles;
    public static IHeuristics heuristics;
    public static String order;
}
