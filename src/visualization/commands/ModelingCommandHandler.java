package visualization.commands;

import java.io.File;

import model.SelectedClass;
import model.SelectedClassMeta;
import model.Statechart;
import modeling.read.ReadJavaToStatechart;

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

import checking.factory.ServiceFactory;
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
				//meta info
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
				String path=project_path.substring(0, project_path.length()-project_name.length()-1);
				path+=full_path;

				// modeling...
				ReadJavaToStatechart toStatechart = new ReadJavaToStatechart();		
				Statechart statechart=toStatechart.readFile(new File(path));
				SelectedClass.getInstance().getStatechart().copy_from(statechart);;
			
				// checking...
				ServiceFactory.getServiceInstance().check(SelectedClass.getInstance().getStatechart());
				
				ModelDispViewDelegation.get_instance().visualize_statechart();
				return null;
			}
		}
		MessageDialog.openInformation(null,"Modeling","Please select the target class.");
		return null;
	}
}
