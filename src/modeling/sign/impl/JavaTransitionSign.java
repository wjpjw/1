package modeling.sign.impl;

import java.util.ArrayList;
import java.util.Iterator;

import model.State;
import model.Transition;
import modeling.Extractor.CodeType;
import modeling.sign.ElementSign;
import modeling.sign.JavaTransitionSignInterface;
import modeling.sign.SignKey;

public class JavaTransitionSign extends ElementSign implements JavaTransitionSignInterface{
	
	JavaStateSign javaStateSign;
	
	public JavaTransitionSign(){
		javaStateSign = new JavaStateSign();
		this.type = CodeType.ANOTATION;
		this.key = SignKey.TRANSITION;
	}
	
	@Override
	public boolean hasThisSign(String str) {
		if(str.trim().startsWith(SignKey.ANNOTATION + SignKey.TRANSITION))
			return true;
		return false;
	}
	
	private String[] lineHandle(String line){
		String stateTrans = line.trim();
		int beginIndex = SignKey.ANNOTATION.length() + SignKey.TRANSITION.length() + SignKey.COLON.length();
		int endIndex = stateTrans.length();
		stateTrans = stateTrans.substring(beginIndex,endIndex);
		String[] models = stateTrans.split(SignKey.SPLITER);
		return models;
	}
	
//	public Iterator<State> stateExtract(String line){
//		String[] models = lineHandle(line);
//		State preState = new State();
//		preState.setName(models[0]);
//		State postState = new State();
//		postState.setName(models[2]);
//		ArrayList<State> stateList = new ArrayList<State>();
//		stateList.add(preState);
//		stateList.add(postState);
//		return stateList.iterator();
//	}
	
	public Transition transitionExtract(String line,ArrayList<State> stateList){
		String[] models = lineHandle(line);
		String prestateString = models[0];
		String poststateString = models[2];
		String transitionString = models[1];
		
		State preState = javaStateSign.transitionExtractState(prestateString);
		State postState = javaStateSign.transitionExtractState(poststateString);
		
		String method = transitionString.substring(0,transitionString.indexOf(SignKey.BRACKETSTART));
		String condition = transitionString.substring(transitionString.indexOf(SignKey.BRACKETSTART)+SignKey.BRACKETSTART.length(),transitionString.length()-SignKey.BRACKETSTART.length());
		
		Transition transition = new Transition();
		if(stateList.contains(preState)){
			transition.setPreState(stateList.get(stateList.indexOf(preState)));
		}else{
			stateList.add(preState);
			transition.setPreState(preState);
		}
		if(stateList.contains(postState)){
			transition.setPostState(stateList.get(stateList.indexOf(postState)));
		}else{
			stateList.add(postState);
			transition.setPostState(postState);
		}	
		transition.setMethod(method);
		transition.setCondition(condition);
		
		return transition;
	}
	
	
	public static void main(String[] args) {
//		String miao = "@stateTrasÀ²À²À²À²À²";
//		System.out.println(new JavaStatetransSign().hasThisSign(miao));
		String nums = "	   0012345	6  7";
		System.out.println(nums.trim().substring(0,2));//Êä³ö01
		System.out.println(nums.trim().indexOf("2"));
		ArrayList<String> hello = new ArrayList<String>();
		hello.add("h");
		hello.add("e");
		Iterator<String> iter = hello.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}

}
