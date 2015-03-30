package visualization.popup.actions;

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
		@SuppressWarnings("restriction")
		
		
		ISelectionService selectionService = Workbench.getInstance().getActiveWorkbenchWindow().getSelectionService();    
		ISelection selection = selectionService.getSelection();
		if(selection instanceof IStructuredSelection) {  
			Object element = ((IStructuredSelection)selection).getFirstElement();  
			if (element instanceof ICompilationUnit) 
			{
				MessageDialog.openInformation(shell,"Modeling","Start Statecharts Modeling...");
				ICompilationUnit iFile = (ICompilationUnit) element;
				
				// 获得.java文件名
				String claName = iFile.getElementName();
				claName = claName.replace(".java", "");
				String cPath = iFile.getPath().toString();
				MessageDialog.openInformation(shell,"class path",cPath+","+claName);

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
