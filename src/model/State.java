package model;

public class State{
	boolean is_exception;
	boolean is_init;
	String name;
	public State(boolean is_exception, String name) {
		super();
		this.is_exception = is_exception;
		this.name = name;
	}
	public boolean isIs_exception() {
		return is_exception;
	}
	public void setIs_exception(boolean is_exception) {
		this.is_exception = is_exception;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}


