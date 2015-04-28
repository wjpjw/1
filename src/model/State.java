package model;

public class State {
	boolean is_exception;
	boolean is_init;
	String name;

	public State() {

	}

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
	
	@Override
	public boolean equals(Object object){
		State state = (State)object;
		if(name.equals(state.getName())){
			if((is_init && state.isIs_init()) || (!is_init) && (!state.isIs_init())){
				if((is_exception && state.isIs_exception()) || (!is_exception) && (!state.isIs_exception())){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return name.hashCode();
	}
}
