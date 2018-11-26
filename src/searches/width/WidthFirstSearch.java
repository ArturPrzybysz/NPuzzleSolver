package searches.width;

import game.State;
import searches.IPuzzleSolver;

import java.util.*;

import static java.util.Arrays.deepToString;

public class WidthFirstSearch implements IPuzzleSolver {
    @Override
    public List<State> findBestPath(State initialState) throws Exception {
        Set<String> closedStatesHashes = new HashSet<>();
        Queue<State> openStates = new LinkedList<>();
        openStates.add(initialState);
        State currentState;

        do {
            currentState = openStates.poll();

            if (closedStatesHashes.contains(deepToString(currentState.getTiles()))) {
                continue;
            }

            closedStatesHashes.add(deepToString(currentState.getTiles()));

            List<State> availableStates = currentState.getAvailableStates();
            openStates.addAll(availableStates);
        } while (currentState.getDistance() != 0);

        return IPuzzleSolver.reconstructPath(currentState);
    }
}
