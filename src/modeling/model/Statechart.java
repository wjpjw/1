package modeling.model;

import java.util.ArrayList;

public class Statechart {
	ArrayList<State> states=new ArrayList<State>();
	ArrayList<Transition> transitions=new ArrayList<Transition>();
	public Statechart(){}
	public Statechart(ArrayList<State> states, ArrayList<Transition> transitions) {
		super();
		this.states = states;
		this.transitions = transitions;
	}
	public ArrayList<State> getStates() {
		return states;
	}
	public void setStates(ArrayList<State> states) {
		this.states = states;
	}
	public ArrayList<Transition> getTransitions() {
		return transitions;
	}
	public void setTransitions(ArrayList<Transition> transitions) {
		this.transitions = transitions;
	}
	
	
	public void addState(State state){
		states.add(state);
	}
	public void addTransition(Transition transition){
		transitions.add(transition);
	}
	
	public void clearState(){
		states.clear();
	}
	public void clearTransition(){
		transitions.clear();
	}
	
	public int indexOfState(State state){
		return states.indexOf(state);
	}
	public int indexOfTransition(Transition transition){
		return transitions.indexOf(transition);
	}
	
	public void removeState(State state){
		states.remove(state);
	}
	public void removeTransition(Transition transition){
		transitions.remove(transition);
	}
	
	public boolean containState(State state){
		return states.contains(state);
	}
	public boolean containTransition(Transition transition){
		return transitions.contains(transition);
	}
	public void copy_from(Statechart s) {
		this.states.clear();
		this.transitions.clear();
		for(int i=0;i<s.states.size();i++){
			states.add(s.states.get(i));
		}
		for(int i=0;i<s.transitions.size();i++){
			transitions.add(s.transitions.get(i));
		}
	}
	
}
