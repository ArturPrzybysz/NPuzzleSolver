package main;

import game.BoardState;
import game.TileGenerator;
import searches.astar.heuristics.IHeuristics;

public class Config {
    public static int size = 4;
    public static int width;
    public static int height;
    public static byte[][] solvedTiles = TileGenerator.generate(BoardState.SOLVED);
    public static IHeuristics heuristics;
    public static String order = "LDUR";
}
