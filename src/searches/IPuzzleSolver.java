package searches;

import game.State;

import java.util.ArrayList;
import java.util.List;

public interface IPuzzleSolver {
    public List<State> findBestPath(State initialState) throws Exception;

    static List<State> reconstructPath(State state) {
        List<State> path = new ArrayList<>();
        State currentState = state;

        do {
            path.add(currentState);
            currentState = currentState.getParent();
        } while (currentState != null);
        return path;
    }
}
