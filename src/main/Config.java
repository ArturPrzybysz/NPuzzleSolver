package main;

import game.BoardState;
import game.TileGenerator;
import searches.astar.heuristics.IHeuristics;
import searches.astar.heuristics.LimitingHeuristics;

public class Config {
    public static int size = 4;
    public static byte[][] solvedTiles = TileGenerator.generate(BoardState.SOLVED);
     //    static searches.astar.heuristics.IHeuristics metrics = new searches.astar.heuristics.OvereagerHeuristics(solvedTiles);
    public static IHeuristics metrics = new LimitingHeuristics(solvedTiles);
    //    static searches.astar.heuristics.IHeuristics metrics = new searches.astar.heuristics.ManhattanHeuristics(solvedTiles);
}
