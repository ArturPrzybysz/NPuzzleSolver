package searches.astar;

import game.State;
import main.Main;
import searches.IPuzzleSolver;

import static java.util.Arrays.deepToString;

import java.util.*;

public class AStar implements IPuzzleSolver {
    private int checkedCtr = 0, processedCtr = 0, recursiveDepth = 0;

    public List<State> findPath(State initialState) {
        Queue<State> openStates = new PriorityQueue<>((s1, s2) -> {
            if (s1.getCumulativeDistance() - s2.getCumulativeDistance() == 0) {
                return 0;
            } else if (s1.getCumulativeDistance() - s2.getCumulativeDistance() > 0) {
                return 1;
            } else {
                return -1;
            }
        });

        Set<String> closedStatesHashes = new HashSet<>();
        openStates.add(initialState);

        while (openStates.size() != 0) {
            State currentState = openStates.poll();
            processedCtr++;
            recursiveDepth = Math.max(recursiveDepth, currentState.depth);

            if (closedStatesHashes.contains(deepToString(currentState.getTiles()))) {
                continue;
            }

            if (currentState.getDistance() == 0) {
                checkedCtr = closedStatesHashes.size();
                return IPuzzleSolver.reconstructPath(currentState);
            }

            closedStatesHashes.add(deepToString(currentState.getTiles()));

            List<State> availableStates = currentState.getAvailableStates();
            openStates.addAll(availableStates);
        }
        return null;
    }

    public int getCheckedCtr() {
        return checkedCtr;
    }

    public int getProcessedCtr() {
        return processedCtr;
    }

    public int getRecursiveDepth() {
        return recursiveDepth;
    }
}
