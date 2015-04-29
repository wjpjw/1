package model;

public class Defect {
	private State relatedState;
	private String description;
	public DefectType type;
	
	public State getRelatedState() {
		return relatedState;
	}

	public void setRelatedState(State relatedState) {
		this.relatedState = relatedState;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
