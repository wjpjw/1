package checking.strategy;

import java.util.ArrayList;

import modeling.model.State;
import modeling.model.Statechart;
import modeling.model.Transition;
import checking.model.Defect;
import checking.model.DefectType;

public class UnreachableStrategy implements Strategy {

	private static UnreachableStrategy strategy = new UnreachableStrategy();

	private UnreachableStrategy() {

	}

	public static UnreachableStrategy getInstance() {
		return strategy;
	}

	public ArrayList<State> returnDefectedStates(Statechart stateChart) {

		ArrayList<Transition> tl = new ArrayList<Transition>(
				stateChart.getTransitions());
		ArrayList<State> reachableStatesList = new ArrayList<State>();
		ArrayList<State> unreachableStatesList = new ArrayList<State>(
				stateChart.getStates());
		for (int i = 0; i < tl.size(); i++) {
			Transition transition = tl.get(i);
			if (transition.getPreState() != null
					&& transition.getPreState().isIs_init()) {
				reachableStatesList.add(transition.getPreState());
				reachableStatesList.add(transition.getPostState());
				for (int j = 0; j < tl.size(); j++) {
					if (transition.getPostState().equals(
							tl.get(j).getPreState())) {
						transition = tl.get(j);
						reachableStatesList.add(transition.getPostState());
					}
				}
			}
		}

		for (int i = 0; i < stateChart.getStates().size(); i++) {
			State state = stateChart.getStates().get(i);
			if (reachableStatesList.contains(state)) {
				unreachableStatesList.remove(state);
			}
		}

		return unreachableStatesList;
	}
}
