class Config {
    static int size = 4;
    static int[][] solvedTiles = TileGenerator.generate(BoardState.SOLVED, size);
    static IMetrics metrics = new Manhattan(solvedTiles);
}
