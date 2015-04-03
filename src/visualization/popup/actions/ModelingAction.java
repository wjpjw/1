package visualization.popup.actions;

import model.SelectedClass;
import model.SelectedClassMeta;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.Workbench;

import visualization.Visualization;



@SuppressWarnings("restriction")
public class ModelingAction implements IObjectActionDelegate {

	private Shell shell;
	public ModelingAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
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
				//...TBD...
				Visualization.get_instance().visualize_statechart();
				MessageDialog.openInformation(shell,"Modeling",meta.toString());
				return;
			}
		}
		
		MessageDialog.openInformation(shell,"Modeling","Please select the target class.");
		
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		
	}

	
}
