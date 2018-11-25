package searches.astar;

import game.State;
import main.Main;

import java.util.*;
public class AStar {
	public static int checkedCtr, processedCtr, recursiveDepth;
	
	
    public static List<State> findBestPath(State initialState) throws Exception {
    	checkedCtr = 0;
    	processedCtr = 0;
    	recursiveDepth = 0;
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
        int ctr = 0;

        while (openStates.size() != 0) {
        	if (openStates.size() < 1) return null;

        	
            State currentState = openStates.poll();
            recursiveDepth = Math.max(recursiveDepth, Math.abs(currentState.depth));
            checkedCtr++;
            processedCtr++;
            
        	ctr++;
        	if (ctr%1000000 == 0) {
        		System.out.println("iteracje: " + ctr);
        		System.out.println("distance: " + currentState.getDistance());
        		Main.printTiles(currentState.getTiles());
        	}

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
