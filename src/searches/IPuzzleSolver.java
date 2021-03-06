package searches;

import game.State;

import java.util.ArrayList;
import java.util.List;

public interface IPuzzleSolver {
    List<State> findPath(State initialState) throws Exception;

    static List<State> reconstructPath(State state) {
        List<State> path = new ArrayList<>();
        State currentState = state;

        do {
            path.add(currentState);
            currentState = currentState.getParent();
        } while (currentState != null);
        return path;
    }

    int getCheckedCtr();

    int getProcessedCtr();

    int getRecursiveDepth();


}
