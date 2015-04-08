package modeling.sign;

import java.util.ArrayList;
import java.util.Iterator;

import model.State;
import model.Transition;
import modeling.Recognizer.AnalysisType;

public class JavaStatetransSign extends ElementSign{
	
	public JavaStatetransSign(){
		this.type = AnalysisType.ANOTATION;
		this.key = SignKey.STATETRANS;
	}
	
	@Override
	public boolean hasThisSign(String str) {
		if(str.contains(SignKey.ANNOTATIONSTART+SignKey.STATETRANS))
			return true;
		return false;
	}
	
	private String[] lineHandle(String line){
		String stateTrans = line.trim();
		int beginIndex = SignKey.ANNOTATIONSTART.length() + SignKey.STATETRANS.length() + SignKey.COLON.length();
		int endIndex = stateTrans.length();
		stateTrans = stateTrans.substring(beginIndex,endIndex);
		String[] models = stateTrans.split(SignKey.SPLITER);
		return models;
	}
	
	public Iterator<State> stateExtract(String line){
		String[] models = lineHandle(line);
		State preState = new State();
		preState.setName(models[0]);
		State postState = new State();
		postState.setName(models[2]);
		ArrayList<State> stateList = new ArrayList<State>();
		stateList.add(preState);
		stateList.add(postState);
		return stateList.iterator();
	}
	
	public Transition transitionExtract(String line){
		String[] models = lineHandle(line);
		
		State preState = new State();
		preState.setName(models[0]);
		State postState = new State();
		postState.setName(models[2]);
				
		String transitionString = models[1];		
		String method = transitionString.substring(0,transitionString.indexOf(SignKey.BRACKETSTART));
		String condition = transitionString.substring(transitionString.indexOf(SignKey.BRACKETSTART)+SignKey.BRACKETSTART.length(),transitionString.length()-SignKey.BRACKETSTART.length());
		
		Transition transition = new Transition();
		transition.setPreState(preState);
		transition.setPostState(postState);
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
