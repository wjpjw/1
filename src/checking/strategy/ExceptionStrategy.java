package checking.strategy;

import java.util.ArrayList;

import modeling.model.State;
import modeling.model.Statechart;
import modeling.model.Transition;
import checking.model.Defect;
import checking.model.DefectType;
import checking.service.CheckingService;

public class ExceptionStrategy implements Strategy{
	
	private static ExceptionStrategy strategy = new ExceptionStrategy();
	
	private ExceptionStrategy(){
		
	}
	
	public static ExceptionStrategy getInstance(){
		return strategy;
	}
	
	public  ArrayList<State> returnDefectedStates(Statechart stateChart){
		
		ArrayList<Transition> tl = new ArrayList<Transition>(stateChart.getTransitions());
		ArrayList<State> exceptionStatesList = new ArrayList<State>(stateChart.getStates());
		
		
		
		for (int i = 0; i < tl.size(); i++) {
			Transition transition = tl.get(i);
			if (transition.getPreState() != null
					&& transition.getPostState() != null) {
				if (!transition.getPreState().isIs_exception()) {
					if (exceptionStatesList.contains(transition.getPreState()))
						exceptionStatesList.remove(transition.getPreState());
				} else if (transition.getPostState().isIs_exception()) {
					if (exceptionStatesList.contains(transition.getPreState()))
						exceptionStatesList.remove(transition.getPreState());
				}

			}
		}
		
		
		
		return exceptionStatesList;
		
	}
	
}
