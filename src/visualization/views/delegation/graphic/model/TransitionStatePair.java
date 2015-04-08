package visualization.views.delegation.graphic.model;

import java.util.ArrayList;

import model.State;
import model.Transition;

public class TransitionStatePair {
	public State preState;
	public State postState;
	private ArrayList<Transition> transitions=new ArrayList<Transition>();
	public TransitionStatePair(State preState, State postState) {
		this.preState = preState;
		this.postState = postState;
	}
	public TransitionStatePair(Transition transition) {
		this.preState=transition.getPreState();
		this.postState=transition.getPostState();
	}
	private boolean is_contained_by(Transition transition){
		return transition.getPostState()==this.postState&&transition.getPreState()==this.preState;
	}
	public void add_transition(Transition transition){
		if(!transitions.contains(transition)&&is_contained_by(transition)){
			transitions.add(transition);
		}
	}
	public boolean equals(TransitionStatePair pair){
		return ((this.postState==pair.postState)&&(this.preState==pair.preState))||
				((this.postState==pair.preState)&&(this.preState==pair.postState));
	}
	public int get_nr(){
		return transitions.size();
	}
	public int get_index(Transition t){
		for (int i = 0; i < transitions.size(); i++) {
			if(transitions.get(i)==t){
				return i;
			}
		}
		return -1;
	}
}
