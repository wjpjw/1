package modeling.helper;

import java.util.ArrayList;

import model.TransitionAno;

public class TransitionAnoHelper {

	private ArrayList<TransitionAno> transAnoList;
	
	private int offset;
	
	
	public TransitionAnoHelper(){
		transAnoList = new ArrayList<TransitionAno>();
		offset = 0;
	}
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public void add(TransitionAno transAno){
		transAnoList.add(transAno);
	}
	
	public boolean contains(TransitionAno transAno){
		return transAnoList.contains(transAno);
	}
	
	public int indexOf(TransitionAno transAno){
		return transAnoList.indexOf(transAno);
	}
	
	public TransitionAno get(int index){
		return transAnoList.get(index);
	}
	
	public void clear(){
		transAnoList.clear();
	}
}
