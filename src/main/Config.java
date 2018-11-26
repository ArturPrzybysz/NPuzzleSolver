package main;

import game.BoardState;
import game.TileGenerator;
import searches.astar.heuristics.IHeuristics;
import searches.astar.heuristics.LimitingHeuristics;
import searches.astar.heuristics.ManhattanHeuristics;

public class Config {
    public static int size = 4;
    public static byte[][] solvedTiles = TileGenerator.generate(BoardState.SOLVED);
    public static IHeuristics metrics = new ManhattanHeuristics(solvedTiles);
}
