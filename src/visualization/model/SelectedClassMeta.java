package visualization.model;

public class SelectedClassMeta {
	private String name="";
	private String fullPath="";
	private String projectPath="";
	private String projectName="";
	public SelectedClassMeta(String name, String fullPath, String projectPath,
			String projectName) {
		super();
		this.name = name;
		this.fullPath = fullPath;
		this.projectPath = projectPath;
		this.projectName = projectName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public SelectedClassMeta(){}
	public SelectedClassMeta(String name, String fullPath, String projectPath) {
		this.name = name;
		this.fullPath = fullPath;
		this.projectPath = projectPath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullPath() {
		return fullPath;
	}
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	public String getProjectPath() {
		return projectPath;
	}
	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}
	public String toString(){
		return "class name:"+name+"\nproject name:"+projectName+"\nclass source path:"+fullPath+"\nproject path:"+projectPath;
	}
	
}
