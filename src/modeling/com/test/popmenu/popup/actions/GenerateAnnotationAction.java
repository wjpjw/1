package modeling.com.test.popmenu.popup.actions;

import java.util.ArrayList;

import model.State;
import modeling.com.test.popmenu.dialog.ComboKey;
import modeling.com.test.popmenu.dialog.GenerateAnnotationWizard;
import modeling.sign.SignKey;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;


public class GenerateAnnotationAction implements IObjectActionDelegate {

	private String type;
	private Shell shell;
	private IWorkbenchPart targetPart;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
		this.targetPart = targetPart;
	}

	/**
	 * The constructor.
	 */
	public GenerateAnnotationAction() {
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

		GenerateAnnotationWizard wizard = new GenerateAnnotationWizard();
		WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
		wizard.setWindowTitle("Generate Annotation Dialog");
		wizard.setType(type);
		int result = dialog.open();
		if (Window.OK == result) {
			StringBuffer annotation = new StringBuffer();
//			System.out.println(wizard.getType());
			if(wizard.getType().equals(ComboKey.STATETYPE)){
				annotation.append(SignKey.ANNOSTART);
				annotation.append(System.getProperty("line.separator"));
				ArrayList<State> stateList = wizard.getStatelist();
				int size = stateList.size();
				for(int i = 0; i < size; i++){
					State state = stateList.get(i);
					annotation.append(SignKey.ANNOTATION);
					annotation.append(SignKey.STATE);
					annotation.append(SignKey.COLON);
					annotation.append(state.getName());
					if(state.isIs_init()){
						annotation.append(SignKey.ANNOTATION);
						annotation.append(SignKey.INIT);
					}
					if(state.isIs_exception()){
						annotation.append(SignKey.ANNOTATION);
						annotation.append(SignKey.EXCEPTION);
					}
					if(i == (size - 1)){
						annotation.append(SignKey.ANNOEND);
					}else{
						annotation.append(System.getProperty("line.separator"));
					}
				}
			
			}else if(wizard.getType().equals(ComboKey.TRANSITIONTYPE)){
				annotation.append(SignKey.ANNOSTART);
				annotation.append(System.getProperty("line.separator"));
				annotation.append(SignKey.ANNOTATION);
				annotation.append(SignKey.TRANSITION);
				annotation.append(SignKey.COLON);
				annotation.append(wizard.getTransition().getPreState().getName());
				if(wizard.getTransition().getPreState().isIs_init()){
					annotation.append(SignKey.ANNOTATION);
					annotation.append(SignKey.INIT);
				}
				if(wizard.getTransition().getPreState().isIs_exception()){
					annotation.append(SignKey.ANNOTATION);
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
					annotation.append(SignKey.ANNOTATION);
					annotation.append(SignKey.INIT);
				}
				if(wizard.getTransition().getPostState().isIs_exception()){
					annotation.append(SignKey.ANNOTATION);
					annotation.append(SignKey.EXCEPTION);
				}
				
				annotation.append(SignKey.ANNOEND);
			}
					
			try {
				document.replace(offset, 0, annotation.toString());
			} catch (BadLocationException e) {
				e.printStackTrace();
			}

		}

		MessageDialog.openInformation(shell, "Cat", "New Action was executed.");

	}

	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {
		// TODO Auto-generated method stub

	}

}
