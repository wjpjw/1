package model;
//A-->Method(Condition1)-->B
//A-->Method(Condition2)-->C
//不同的Condition对用着不同的(prestate,poststate)的pair，这里的condition是写注释的人加上的字符串，与我们逻辑的无关，仅仅是字符串。
//(prestate,poststate)相同的Tansition的method可能不同，method相同而condition不同的状况不存在。
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
