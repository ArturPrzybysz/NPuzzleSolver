package searches.depth;

import game.State;
import searches.IPuzzleSolver;

import java.util.*;

import static java.util.Arrays.deepToString;

public class DepthFirstSearch implements IPuzzleSolver {
    private int checkedCtr = 0, processedCtr = 0, recursiveDepth = 0;

    @Override
    public List<State> findPath(State initialState) {
        Set<String> closedStatesHashes = new HashSet<>();
        Stack<State> openStates = new Stack<>();
        openStates.push(initialState);

        while (openStates.size() != 0) {
            State currentState = openStates.pop();
            recursiveDepth = Math.max(recursiveDepth, currentState.depth);
            processedCtr++;

            if (currentState.getDistance() == 0) {
                checkedCtr = closedStatesHashes.size();
                return IPuzzleSolver.reconstructPath(currentState);
            }

            if (closedStatesHashes.contains(deepToString(currentState.getTiles()))) {
                continue;
            }

            closedStatesHashes.add(deepToString(currentState.getTiles()));

            List<State> availableStates = currentState.getAvailableStates();
            openStates.addAll(availableStates);
        }
        return null;
    }

    @Override
    public int getCheckedCtr() {
        return checkedCtr;
    }

    @Override
    public int getProcessedCtr() {
        return processedCtr;
    }

    @Override
    public int getRecursiveDepth() {
        return recursiveDepth;
    }
}
