package modeling.com.test.popmenu.dialog;

import model.State;
import model.Transition;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class GenerateTransitionDialog_old extends Dialog {

	private Transition transition;
	private Text textPre;
	private Text textPost;
	private Text textTransName;
	private Text textTransCondition;
	private Button button;
	private Shell parentShell;

	public GenerateTransitionDialog_old(Shell parentShell) {
		super(parentShell);
		this.parentShell = parentShell;
		// TODO Auto-generated constructor stub
	}

	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));

		// preState
		Label label = new Label(composite, SWT.NONE);
		label.setText("PreState: ");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false,
				false, 1, 1));

		textPre = new Text(composite, SWT.BORDER);
		textPre.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false, 1, 1));

		// Transition(method)
		label = new Label(composite, SWT.NONE);
		label.setText("Transition(method): ");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false,
				false, 1, 1));

		textTransName = new Text(composite, SWT.BORDER);
		textTransName.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false, 1, 1));

		// Transition(condition)
		label = new Label(composite, SWT.NONE);
		label.setText("Transition(condition): ");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false,
				false, 1, 1));

		textTransCondition = new Text(composite, SWT.BORDER);
		textTransCondition.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING,
				true, false, 1, 1));

		// PostState
		label = new Label(composite, SWT.NONE);
		label.setText("PostState: ");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false,
				false, 1, 1));

		textPost = new Text(composite, SWT.BORDER);
		textPost.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false, 1, 1));

		// button = new Button(composite,SWT.BUTTON1);
		// button.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING,
		// false,
		// false, 2, 1));

		// addListeners();

		return composite;
	}

	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);

		// getButton(OK).setEnabled(false);
	}

	// >>> DAY6

	// Referred by DAY6 <<<
	void addListeners() {
		// textName.addModifyListener(new ModifyListener() {
		//
		// @Override
		// public void modifyText(ModifyEvent e) {
		// getButton(OK).setEnabled(! textName.getText().isEmpty());
		// }});

	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("New Transition");
	}

	@Override
	protected Point getInitialSize() {
		return new Point(400, 250);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}
	
	
	protected void okPressed() {
		State preState = new State();
		preState.setName(textPre.getText());
		
		State postState = new State();
		postState.setName(textPost.getText());
		
		transition = new Transition();
		transition.setPreState(preState);
		transition.setPostState(postState);
		transition.setMethod(textTransName.getText());
		transition.setCondition(textTransCondition.getText());
		super.okPressed();
	}
	
	public Transition getTransition(){
		return transition;
	}
	

}
