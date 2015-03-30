package model;

public class State{
	boolean is_exception;
	boolean is_init;
	String name;
	public boolean isIs_init() {
		return is_init;
	}
	public void setIs_init(boolean is_init) {
		this.is_init = is_init;
	}
	public State(boolean is_exception, boolean is_init, String name) {
		super();
		this.is_exception = is_exception;
		this.is_init = is_init;
		this.name = name;
	}
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


