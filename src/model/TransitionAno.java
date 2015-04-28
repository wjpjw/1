package model;

public class TransitionAno {

	public static final int newlinesLength = 2;
	
	
	private int offset;
	private String transitionLine;
	private String methodName;
	private int length;
	
	public TransitionAno(){
		
	}
	
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getTransitionLine() {
		return transitionLine;
	}
	public void setTransitionLine(String transitionLine) {
		this.transitionLine = transitionLine;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	@Override
	public boolean equals(Object object){
		TransitionAno transAno = (TransitionAno)object;
		if(methodName.equals(transAno.getMethodName())){
			return true;
		}
		return false;
	}
	@Override
	public int hashCode(){
		return methodName.hashCode();
	}
	
}
