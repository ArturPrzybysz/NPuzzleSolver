package searches.depth;

import game.State;
import searches.IPuzzleSolver;

import java.util.*;

public class DepthFirstSearch implements IPuzzleSolver {
    @Override
    public List<State> findBestPath(State initialState) throws Exception {
        Set<Integer> closedStatesHashes = new HashSet<>();
        Stack<State> openStates = new Stack<>();
        openStates.push(initialState);
        State currentState;

        do {
            currentState = openStates.pop();

            if (closedStatesHashes.contains(Arrays.deepHashCode(currentState.getTiles()))) {
                continue;
            }

            closedStatesHashes.add(Arrays.deepHashCode(currentState.getTiles()));

            List<State> availableStates = currentState.getAvailableStates();

            for (State state : availableStates) {
                openStates.push(state);
            }

        } while (currentState.getDistance() != 0);

        return IPuzzleSolver.reconstructPath(currentState);
    }
}
