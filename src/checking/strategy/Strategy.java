package checking.strategy;

import java.util.ArrayList;

import modeling.model.State;
import modeling.model.Statechart;

public interface Strategy {
	
	public  ArrayList<State> returnDefectedStates(Statechart stateChart);
	
}
