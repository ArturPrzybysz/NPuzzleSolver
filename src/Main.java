import java.util.List;

public class Main {
    public static void main(String[] args) {
        State initialState = new State(TileGenerator.generate(BoardState.RANDOM, 4));
        System.out.println("Initial");
        Main.printTiles(initialState.getTiles());

        List<State> solution = null;
        try {
            solution = AStar.findBestPath(initialState);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(solution.size());
        for (State s : solution) {
            Main.printTiles(s.getTiles());
        }
    }

    private static void printTiles(byte[][] tiles) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                System.out.print(tiles[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");

    }
}
