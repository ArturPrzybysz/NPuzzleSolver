import java.util.*;

class AStar {
    static List<State> findBestPath(State initialState) throws Exception {
        Queue<State> openStates = new PriorityQueue<>((s1, s2) -> {
            if (s1.getCumulativeDistance() - s2.getCumulativeDistance() == 0) {
                return 0;
            } else if (s1.getCumulativeDistance() - s2.getCumulativeDistance() > 0) {
                return 1;
            } else {
                return -1;
            }
        });

        Set<Integer> closedStatesHashes = new HashSet<>();
        openStates.add(initialState);

        while (openStates.size() != 0) {
            State currentState = openStates.poll();

            if (closedStatesHashes.contains(Arrays.deepHashCode(currentState.getTiles()))) {
                continue;
            }

            if (currentState.getDistance() == 0) {
                return reconstructPath(currentState);
            }

            closedStatesHashes.add(Arrays.deepHashCode(currentState.getTiles()));
            openStates.addAll(currentState.getAvailableStates());
        }
        throw new Exception("No solution found");
    }

    private static List<State> reconstructPath(State state) {
        List<State> path = new ArrayList<>();
        State currentState = state;

        do {
            path.add(currentState);
            currentState = currentState.getParent();
        } while (currentState != null);
        return path;
    }

}
