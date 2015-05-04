package modeling.sign.impl;

import modeling.Extractor.interf.CodeType;
import modeling.model.State;
import modeling.sign.ElementSign;
import modeling.sign.JavaStateSignInterface;
import modeling.sign.SignKey;



public class JavaStateSign extends ElementSign implements JavaStateSignInterface{

	public JavaStateSign() {
		this.type = CodeType.ANOTATION;
		this.key = SignKey.STATE;
	}

	@Override
	public boolean hasThisSign(String str) {
		if (str.trim().startsWith(SignKey.ANNOTATION + SignKey.STATE))
			return true;
		return false;
	}

	public State stateExtractState(String line) {
		String stateString = line.trim();
		int beginIndex = SignKey.ANNOTATION.length()
				+ SignKey.STATE.length() + SignKey.COLON.length();
		int endIndex = stateString.length();
		stateString = stateString.substring(beginIndex, endIndex);

		return transitionExtractState(stateString);
		
	}
	
	public State transitionExtractState(String stateString){
		int endIndex;
		State state = new State();
		if (stateString.endsWith(SignKey.ANNOTATION + SignKey.INIT)) {
			state.setIs_init(true);
			endIndex = stateString.lastIndexOf(SignKey.ANNOTATION);
			stateString = stateString.substring(0, endIndex);
		}else{
			state.setIs_init(false);
		}
		if (stateString.endsWith(SignKey.ANNOTATION + SignKey.EXCEPTION)) {
			state.setIs_exception(true);
			endIndex = stateString.lastIndexOf(SignKey.ANNOTATION);
			stateString = stateString.substring(0, endIndex);
		}else{
			state.setIs_exception(false);
		}

		state.setName(stateString);
		return state;
	}
	
	
	public static void main(String[] args) {
		JavaStateSign sign = new JavaStateSign();
		State s = sign.stateExtractState("@state:nihao@exception");
		
		System.out.println(s.getName());
		System.out.println(s.isIs_init());
		System.out.println(s.isIs_exception());

	}

}
