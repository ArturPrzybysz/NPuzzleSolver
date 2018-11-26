package searches.astar;

import game.State;
import searches.IPuzzleSolver;

import java.util.*;

import static java.util.Arrays.deepToString;

public class AStar implements IPuzzleSolver {
    public List<State> findBestPath(State initialState) throws Exception {
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

            if (closedStatesHashes.contains(deepToString(currentState.getTiles()))) {
                continue;
            }

            if (currentState.getDistance() == 0) {
                return IPuzzleSolver.reconstructPath(currentState);
            }

            closedStatesHashes.add(deepToString(currentState.getTiles()));
            openStates.addAll(currentState.getAvailableStates());
        }
        throw new Exception("No solution found");
    }

}
