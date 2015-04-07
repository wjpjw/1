package visualization.commands;

import java.util.ArrayList;

import model.SelectedClass;
import model.SelectedClassMeta;
import model.State;
import model.Transition;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.internal.Workbench;

import visualization.views.delegation.ModelDispViewDelegation;

@SuppressWarnings("restriction")
public class ModelingCommandHandler extends AbstractHandler implements IHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelectionService selectionService = Workbench.getInstance().getActiveWorkbenchWindow().getSelectionService();    
		ISelection selection = selectionService.getSelection();
		if(selection instanceof IStructuredSelection) {  
			Object element = ((IStructuredSelection)selection).getFirstElement();  
			if (element instanceof ICompilationUnit) 
			{
				ICompilationUnit iFile = (ICompilationUnit) element;
				String class_name = iFile.getElementName();
				class_name = class_name.replace(".java", "");
				String full_path = iFile.getPath().toString();
				IJavaProject java_project = iFile.getJavaProject();
			    IProject project = java_project.getProject();
				String project_name = java_project.getElementName();
				String project_path = project.getRawLocationURI().toString();
				project_path = project_path.substring(6);
				SelectedClassMeta meta=SelectedClass.getInstance().getMeta();
				meta.setFullPath(full_path);
				meta.setName(class_name);
				meta.setProjectPath(project_path);
				meta.setProjectName(project_name);
				//modeling code here...
				//...这里是用于测试图形化的modeling代码的桩...
				for (int i = 0; i < 5; i++) {
					SelectedClass.getInstance().getStatechart().getStates().add(new State(false, "state~"+i));
				}
				ArrayList<State> states=SelectedClass.getInstance().getStatechart().getStates();
				State s1=states.get(0);
				State s2=states.get(1);
				State s3=states.get(2);
				State s4=states.get(3);
				State s5=states.get(4);
				ArrayList<Transition> transitions=SelectedClass.getInstance().getStatechart().getTransitions();
				transitions.add(new Transition(s1, s2, "c1", "m1"));
				transitions.add(new Transition(s1, s2, "c1", "m2"));
				transitions.add(new Transition(s1, s2, "c1", "m3"));
				transitions.add(new Transition(s1, s2, "c1", "m4"));
				transitions.add(new Transition(s1, s2, "c1", "m5"));
				transitions.add(new Transition(s3, s5, "c1", "m6"));
				transitions.add(new Transition(s5, s3, "c1", "m7"));
				transitions.add(new Transition(s2, s5, "c1", "m8"));
				transitions.add(new Transition(s2, s5, "c1", "m9"));
				transitions.add(new Transition(s1, s5, "c1", "m10"));
				transitions.add(new Transition(s2, s5, "c2", "m10"));
				transitions.add(new Transition(s3, s5, "c3", "m10"));
				transitions.add(new Transition(s4, s5, "c4", "m10"));
				transitions.add(new Transition(s2, s1, "c1", "m11"));
				transitions.add(new Transition(s1, s3, "c1", "m12"));
				transitions.add(new Transition(s3, s2, "c1", "m13"));
				transitions.add(new Transition(s2, s1, "c1", "m14"));
				transitions.add(new Transition(s3, s1, "c1", "m15"));

				//-----------------END---------------
				ModelDispViewDelegation.get_instance().visualize_statechart();
				MessageDialog.openInformation(null,"Modeling",meta.toString());
				return null;
			}
		}
		MessageDialog.openInformation(null,"Modeling","Please select the target class.");
		return null;
	}
}
