package modeling.com.test.popmenu.popup.actions;

import java.util.ArrayList;

import model.State;
import model.TransitionAno;
import modeling.com.test.popmenu.dialog.GenerateAnnotationWizard;
import modeling.com.test.popmenu.key.ComboKey;
import modeling.read.ReadOffset;
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
import org.eclipse.ui.IPathEditorInput;
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
	private ReadOffset readOffset;

	/**
	 * The constructor.
	 */
	public GenerateAnnotationAction() {
		super();
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
		this.targetPart = targetPart;
	}

	public void run(IAction action) {
		// 取得工作台
		IWorkbench workbench = PlatformUI.getWorkbench();
		// 取得工作台窗口
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();

		// ISelectionService selectionService =
		// Workbench.getInstance().getActiveWorkbenchWindow().getSelectionService();
		// ISelection fileSelection = selectionService.getSelection();
		// if(fileSelection instanceof IStructuredSelection) {
		// Object element =
		// ((IStructuredSelection)fileSelection).getFirstElement();
		// System.out.println("first if");
		// if (element instanceof ICompilationUnit)
		// {
		// System.out.println("second if");
		// ICompilationUnit iFile = (ICompilationUnit) element;
		// String class_name = iFile.getElementName();
		// class_name = class_name.replace(".java", "");
		// String full_path = iFile.getPath().toString();
		// IJavaProject java_project = iFile.getJavaProject();
		// IProject project = java_project.getProject();
		// String project_name = java_project.getElementName();
		// String project_path = project.getRawLocationURI().toString();
		// project_path = project_path.substring(6);
		// SelectedClassMeta meta=SelectedClass.getInstance().getMeta();
		// meta.setFullPath(full_path);
		// meta.setName(class_name);
		// meta.setProjectPath(project_path);
		// meta.setProjectName(project_name);
		//
		// String path = project_path.substring(0,
		// project_path.length()-project_name.length()-1);
		// path += full_path;
		//
		// System.out.println(path);
		// }
		// }
		//
		//

		// 取得工作台页面
		IWorkbenchPage page = window.getActivePage();
		// 取得当前处于活动状态的编辑器窗口
		IEditorPart part = page.getActiveEditor();
		IEditorInput input = part.getEditorInput();
		
		String path = "";
		if (input instanceof IPathEditorInput) {
			path = ((IPathEditorInput) input).getPath().toString();
		}

		IDocument document = ((ITextEditor) part).getDocumentProvider()
				.getDocument(input);
		ISelection selection = ((ITextEditor) part).getSite()
				.getSelectionProvider().getSelection();

		if (!(selection instanceof ITextSelection)) {
			return;
		}

		ITextSelection textSelection = (ITextSelection) selection;
		int offset = textSelection.getOffset();
		// System.out.println("offset"+offset);

		GenerateAnnotationWizard wizard = new GenerateAnnotationWizard();
		WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
		wizard.setWindowTitle("Generate Annotation Dialog");
		wizard.setType(type);
		int result = dialog.open();

		if (Window.OK == result) {
			StringBuffer annotation = new StringBuffer();
			// System.out.println(wizard.getType());
			if (wizard.getType().equals(ComboKey.STATETYPE)) {
				annotation.append(SignKey.ANNOSTART);
				annotation.append(System.getProperty("line.separator"));
				ArrayList<State> stateList = wizard.getStatelist();
				int size = stateList.size();
				for (int i = 0; i < size; i++) {
					State state = stateList.get(i);
					annotation.append(SignKey.ANNOTATION);
					annotation.append(SignKey.STATE);
					annotation.append(SignKey.COLON);
					annotation.append(state.getName());
					if (state.isIs_init()) {
						annotation.append(SignKey.ANNOTATION);
						annotation.append(SignKey.INIT);
					}
					if (state.isIs_exception()) {
						annotation.append(SignKey.ANNOTATION);
						annotation.append(SignKey.EXCEPTION);
					}
					if (i == (size - 1)) {
						annotation.append(SignKey.ANNOEND);
					} else {
						annotation.append(System.getProperty("line.separator"));
					}
				}

			} else if (wizard.getType().equals(ComboKey.TRANSITIONTYPE)) {
				readOffset = new ReadOffset();
				readOffset.readOffset(document.get());
				TransitionAno transAno = new TransitionAno();
				transAno.setMethodName(wizard.getTransition().getMethod());
				if (readOffset.getTransAnoHelper().contains(transAno)) {
					TransitionAno realTransAno = readOffset.getTransAnoHelper()
							.get(readOffset.getTransAnoHelper().indexOf(
									transAno));
					offset = realTransAno.getOffset()
							+ realTransAno.getLength();
					annotation.append(System.getProperty("line.separator"));
					annotation.append(SignKey.ANNOTATION);
					annotation.append(SignKey.TRANSITION);
					annotation.append(SignKey.COLON);
					annotation.append(wizard.getTransition().getPreState()
							.getName());
					if (wizard.getTransition().getPreState().isIs_init()) {
						annotation.append(SignKey.ANNOTATION);
						annotation.append(SignKey.INIT);
					}
					if (wizard.getTransition().getPreState().isIs_exception()) {
						annotation.append(SignKey.ANNOTATION);
						annotation.append(SignKey.EXCEPTION);
					}
					annotation.append(SignKey.SPLITER);
					annotation.append(wizard.getTransition().getMethod());
					annotation.append(SignKey.BRACKETSTART);
					annotation.append(wizard.getTransition().getCondition());
					annotation.append(SignKey.BRACKETEND);
					annotation.append(SignKey.SPLITER);
					annotation.append(wizard.getTransition().getPostState()
							.getName());
					if (wizard.getTransition().getPostState().isIs_init()) {
						annotation.append(SignKey.ANNOTATION);
						annotation.append(SignKey.INIT);
					}
					if (wizard.getTransition().getPostState().isIs_exception()) {
						annotation.append(SignKey.ANNOTATION);
						annotation.append(SignKey.EXCEPTION);
					}

					System.out.println("有的+" + offset);
				} else {
					annotation.append(SignKey.ANNOSTART);
					annotation.append(System.getProperty("line.separator"));
					annotation.append(SignKey.ANNOTATION);
					annotation.append(SignKey.TRANSITION);
					annotation.append(SignKey.COLON);
					annotation.append(wizard.getTransition().getPreState()
							.getName());
					if (wizard.getTransition().getPreState().isIs_init()) {
						annotation.append(SignKey.ANNOTATION);
						annotation.append(SignKey.INIT);
					}
					if (wizard.getTransition().getPreState().isIs_exception()) {
						annotation.append(SignKey.ANNOTATION);
						annotation.append(SignKey.EXCEPTION);
					}
					annotation.append(SignKey.SPLITER);
					annotation.append(wizard.getTransition().getMethod());
					annotation.append(SignKey.BRACKETSTART);
					annotation.append(wizard.getTransition().getCondition());
					annotation.append(SignKey.BRACKETEND);
					annotation.append(SignKey.SPLITER);
					annotation.append(wizard.getTransition().getPostState()
							.getName());
					if (wizard.getTransition().getPostState().isIs_init()) {
						annotation.append(SignKey.ANNOTATION);
						annotation.append(SignKey.INIT);
					}
					if (wizard.getTransition().getPostState().isIs_exception()) {
						annotation.append(SignKey.ANNOTATION);
						annotation.append(SignKey.EXCEPTION);
					}

					// System.out.println("没有+"+offset);
					annotation.append(SignKey.ANNOEND);
				}

			}

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
