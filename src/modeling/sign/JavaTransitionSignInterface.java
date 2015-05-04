package modeling.sign;

import java.util.ArrayList;

import modeling.model.State;
import modeling.model.Transition;

public interface JavaTransitionSignInterface {
//	public Iterator<State> stateExtract(String line);
	public Transition transitionExtract(String line,ArrayList<State> stateList);
}
