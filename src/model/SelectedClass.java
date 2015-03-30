package model;

public class SelectedClass {
	public SelectedClass(SelectedClassMeta meta, Statechart statechart) {
		this.meta = meta;
		this.statechart = statechart;
	}
	private SelectedClassMeta meta;
	private Statechart statechart;
	public SelectedClassMeta getMeta() {
		return meta;
	}
	public void setMeta(SelectedClassMeta meta) {
		this.meta = meta;
	}
	public Statechart getStatechart() {
		return statechart;
	}
	public void setStatechart(Statechart statechart) {
		this.statechart = statechart;
	}
	
}
