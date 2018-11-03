class Config {
    static int size = 4;
    static byte[][] solvedTiles = TileGenerator.generate(BoardState.SOLVED);
    //    static IHeuristics metrics = new OvereagerHeuristics(solvedTiles);
    static IHeuristics metrics = new LimitingHeuristics(solvedTiles);
    //    static IHeuristics metrics = new ManhattanHeuristics(solvedTiles);
}
