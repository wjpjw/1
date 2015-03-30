package model;

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
	
}
