package model;

public class Transition {
	State preState;
	State postState;
	String condition;
	String method;
	public Transition(State preState, State postState, String condition,
			String method) {
		super();
		this.preState = preState;
		this.postState = postState;
		this.condition = condition;
		this.method = method;
	}
	public State getPreState() {
		return preState;
	}
	public void setPreState(State preState) {
		this.preState = preState;
	}
	public State getPostState() {
		return postState;
	}
	public void setPostState(State postState) {
		this.postState = postState;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
}
