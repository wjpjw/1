package modeling.mybishe;

import java.util.ArrayList;
import java.util.Iterator;

import model.State;
import model.Statechart;
import model.Transition;
import modeling.sign.ElementSign;
import modeling.sign.JavaStatetransSign;

public class JavaAnnotationAnalyst {

	private Statechart statechart;
	// private ArrayList<ElementSign> signList;
	private JavaStatetransSign javaStatetransSign;

	public JavaAnnotationAnalyst() {
		statechart = new Statechart();
		// signList = new ArrayList<ElementSign>();
		javaStatetransSign = new JavaStatetransSign();
	}

	public void analysis(String line) {
		// for (ElementSign sign : signList) {
		if (javaStatetransSign.hasThisSign(line)) {
			Iterator<State> stateIter = javaStatetransSign.stateExtract(line);
			while (stateIter.hasNext()) {
				statechart.addState(stateIter.next());
			}
			
			Transition transition = javaStatetransSign.transitionExtract(line);
			statechart.addTransition(transition);		
		}
		// }
	}

	public Statechart getStatechart() {
		return statechart;
	}

	public void setStatechart(Statechart statechart) {
		this.statechart = statechart;
	}

	public JavaStatetransSign getJavaStatetransSign() {
		return javaStatetransSign;
	}

	public void setJavaStatetransSign(JavaStatetransSign javaStatetransSign) {
		this.javaStatetransSign = javaStatetransSign;
	}
	
	

}
