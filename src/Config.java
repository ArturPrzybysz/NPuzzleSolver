class Config {
    static int size = 4;
    static byte[][] solvedTiles = TileGenerator.generate(BoardState.SOLVED, size);
    static IMetrics metrics = new Manhattan(solvedTiles);
}
