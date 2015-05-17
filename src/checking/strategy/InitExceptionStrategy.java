package checking.strategy;

import java.util.ArrayList;

import modeling.model.State;
import modeling.model.Statechart;
import modeling.model.Transition;
import checking.model.Defect;
import checking.model.DefectType;

public class InitExceptionStrategy implements Strategy {

	private static InitExceptionStrategy strategy = new InitExceptionStrategy();

	private InitExceptionStrategy() {

	}

	public static InitExceptionStrategy getInstance() {
		return strategy;
	}

	public ArrayList<State> returnDefectedStates(Statechart stateChart) {

		ArrayList<State> initExceptionStatesList = new ArrayList<State>();

		for (int i = 0; i < stateChart.getStates().size(); i++) {
			if (stateChart.getStates().get(i).isIs_exception()
					&& stateChart.getStates().get(i).isIs_init()) {
				initExceptionStatesList.add(stateChart.getStates().get(i));
			}
		}

		return initExceptionStatesList;

	}

}
