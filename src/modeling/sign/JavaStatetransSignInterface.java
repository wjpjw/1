package modeling.sign;

import java.util.Iterator;

import model.State;
import model.Transition;

public interface JavaStatetransSignInterface {
	public Iterator<State> stateExtract(String line);
	public Transition transitionExtract(String line);
}
