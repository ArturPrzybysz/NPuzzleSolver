package main;

import game.BoardState;
import game.TileGenerator;
import searches.astar.heuristics.HammingHeuristic;
import searches.astar.heuristics.IHeuristics;
import searches.astar.heuristics.LimitingHeuristics;
import searches.astar.heuristics.SnakeHeuristic;

public class Config {
    public static int size = 4;
    public static byte[][] solvedTiles = TileGenerator.generate(BoardState.SOLVED);
    //public static searches.astar.heuristics.IHeuristics metrics = new searches.astar.heuristics.OvereagerHeuristics(solvedTiles);
    // public static IHeuristics metrics = new LimitingHeuristics(solvedTiles);
    public static IHeuristics metrics = new SnakeHeuristic(solvedTiles);
    //public static searches.astar.heuristics.IHeuristics metrics = new searches.astar.heuristics.ManhattanHeuristics(solvedTiles);
    //public static IHeuristics metrics = new HammingHeuristic(solvedTiles);
}
