package model;

public class SelectedClass {
	private static SelectedClass instance=new SelectedClass();
	public static SelectedClass getInstance(){
		return instance;
	}
	private SelectedClass(){}
	private SelectedClassMeta meta=new SelectedClassMeta();
	private Statechart statechart=new Statechart();
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
