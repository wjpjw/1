package modeling.com.test.popmenu.popup.actions;



import modeling.com.test.popmenu.dialog.NewTransitionWizard;
import modeling.com.test.popmenu.dialog.NewTransitionWizardPage;
import modeling.sign.SignKey;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;



public class NewTransitionAction implements IObjectActionDelegate {

	private Shell shell;

	private IWorkbenchPart targetPart;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
		this.targetPart = targetPart;
	}

	/**
	 * The constructor.
	 */
	public NewTransitionAction() {
		super();
	}

	public void run(IAction action) {
		// 取得工作台
		IWorkbench workbench = PlatformUI.getWorkbench();
		// 取得工作台窗口
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		// 取得工作台页面
		IWorkbenchPage page = window.getActivePage();
		// 取得当前处于活动状态的编辑器窗口
		IEditorPart part = page.getActiveEditor();
		IEditorInput input = part.getEditorInput();
		IDocument document = ((ITextEditor) part).getDocumentProvider()
				.getDocument(input);
		ISelection selection = ((ITextEditor) part).getSite()
				.getSelectionProvider().getSelection();

		if (!(selection instanceof ITextSelection)) {
			return;
		}
		ITextSelection textSelection = (ITextSelection) selection;
		int offset = textSelection.getOffset();

		NewTransitionWizard wizard = new NewTransitionWizard();
		WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
		wizard.setWindowTitle("New Transition Dialog");
		if (Window.OK == dialog.open()) {
			StringBuffer annotation = new StringBuffer();
			annotation.append("/*");
			annotation.append(System.getProperty("line.separator"));
			annotation.append(SignKey.ANNOTATIONSTART);
			annotation.append(SignKey.STATETRANS);
			annotation.append(SignKey.COLON);
			annotation.append(wizard.getTransition().getPreState().getName());
			if(wizard.getTransition().getPreState().isIs_init()){
				annotation.append(SignKey.ANNOTATIONSTART);
				annotation.append(SignKey.INIT);
			}
			if(wizard.getTransition().getPreState().isIs_exception()){
				annotation.append(SignKey.ANNOTATIONSTART);
				annotation.append(SignKey.EXCEPTION);
			}
			annotation.append(SignKey.SPLITER);
			annotation.append(wizard.getTransition().getMethod());
			annotation.append(SignKey.BRACKETSTART);
			annotation.append(wizard.getTransition().getCondition());
			annotation.append(SignKey.BRACKETEND);
			annotation.append(SignKey.SPLITER);
			annotation.append(wizard.getTransition().getPostState().getName());
			if(wizard.getTransition().getPostState().isIs_init()){
				annotation.append(SignKey.ANNOTATIONSTART);
				annotation.append(SignKey.INIT);
			}
			if(wizard.getTransition().getPostState().isIs_exception()){
				annotation.append(SignKey.ANNOTATIONSTART);
				annotation.append(SignKey.EXCEPTION);
			}
			
			annotation.append("*/");
			try {
				document.replace(offset, 0, annotation.toString());
			} catch (BadLocationException e) {
				e.printStackTrace();
			}

		}

//		MessageDialog.openInformation(shell, "Cat", "New Action was executed.");

	}

	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {
		// TODO Auto-generated method stub

	}

}
