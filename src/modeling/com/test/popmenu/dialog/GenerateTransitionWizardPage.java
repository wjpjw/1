package modeling.com.test.popmenu.dialog;

import modeling.com.test.popmenu.key.ComboKey;
import modeling.model.State;
import modeling.model.Transition;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class GenerateTransitionWizardPage extends WizardPage {

	private Combo preInitCombo;
	private Combo postExceptionCombo;
	private Text textPre;
	private Text textPost;
	private Text textTransName;
	private Text textTransCondition;
	private Transition transition;
	private State preState;
	private State postState;
	
	
	protected GenerateTransitionWizardPage(String pageName) {
		super(pageName);
		this.setTitle("Input transition information");
		this.setMessage("Input the prestate, methodName, poststate and so on.");
		transition = new Transition();
		preState = new State();
		postState = new State();
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(4, false);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		this.setControl(composite);
		
		//prestate
		Label label = new Label(composite, SWT.NONE);
		label.setText("PreState: ");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false,
				false, 1, 1));
		
		textPre = new Text(composite, SWT.BORDER);
		GridData data = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1, 1);
		data.widthHint = 200;
		textPre.setLayoutData(data);

		preInitCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);		
		preInitCombo.add(ComboKey.NOTINITSTATE);
		preInitCombo.add(ComboKey.ISINITSTATE);
		preInitCombo.select(0);
		data = new GridData(SWT.BEGINNING, SWT.BEGINNING, false,
				false, 1, 1);
		data.widthHint = 100;
		preInitCombo.setLayoutData(data);
		preInitCombo.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if(preInitCombo.getText().equals(ComboKey.ISINITSTATE)){
					preState.setIs_init(true);
				}else{
					preState.setIs_init(false);
				}
			}});
		if(preInitCombo.getText().equals(ComboKey.ISINITSTATE)){
			preState.setIs_init(true);
		}else{
			preState.setIs_init(false);
		}
		//ռλ
		label = new Label(composite, SWT.NONE);
		data = new GridData(SWT.BEGINNING, SWT.BEGINNING, false,
				false, 1, 1);
		data.widthHint = 100;
		label.setLayoutData(data);

		
		//transition MethodName
		label = new Label(composite, SWT.NONE);
		label.setText("MethodName: ");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false,
				false, 1, 1));
		
		textTransName = new Text(composite, SWT.BORDER);
		data = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1, 1);
		data.widthHint = 200;
		textTransName.setLayoutData(data);
		
		//transition condition
		label = new Label(composite, SWT.NONE);
		label.setText("TransCondition: ");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false,
				false, 1, 1));
		
		textTransCondition = new Text(composite, SWT.BORDER);
		data = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1, 1);
		data.widthHint = 200;
		textTransCondition.setLayoutData(data);
		
		//postState
		label = new Label(composite, SWT.NONE);
		label.setText("postState: ");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false,
				false, 1, 1));
		
		textPost = new Text(composite, SWT.BORDER);
		data = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1, 1);
		data.widthHint = 200;
		textPost.setLayoutData(data);

		postExceptionCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);		
		postExceptionCombo.add(ComboKey.NOTEXCEPTIONSTATE);
		postExceptionCombo.add(ComboKey.EXCEPTIONSTATE);
		postExceptionCombo.select(0);
		data = new GridData(SWT.BEGINNING, SWT.BEGINNING, false,
				false, 1, 1);
		data.widthHint = 100;
		postExceptionCombo.setLayoutData(data);
		postExceptionCombo.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if(postExceptionCombo.getText().equals(ComboKey.EXCEPTIONSTATE)){
					postState.setIs_exception(true);
				}else{
					postState.setIs_exception(false);
				}
			}});
		if(postExceptionCombo.getText().equals(ComboKey.EXCEPTIONSTATE)){
			postState.setIs_exception(true);
		}else{
			postState.setIs_exception(false);
		}
		
		label = new Label(composite, SWT.NONE);
		data = new GridData(SWT.BEGINNING, SWT.BEGINNING, false,
				false, 1, 1);
		data.widthHint = 100;
		label.setLayoutData(data);
		
		setPageComplete(false);
		addActions();
		
		
	}
	
	private void addActions() {

		ModifyListener listener = new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setPageComplete(isInputValid());
			}};
		textPre.addModifyListener(listener);
		textTransName.addModifyListener(listener);
		textTransCondition.addModifyListener(listener);
		textPost.addModifyListener(listener);
	}
	
	private boolean isInputValid() {
		if (textPre.getText().isEmpty()) {
			this.setErrorMessage("Input PreState.");
			return false;
		}
		
		if (textTransName.getText().isEmpty()) {
			this.setErrorMessage("Input TransitionName.");
			return false;
		}
		
		if (textPost.getText().isEmpty()) {
			this.setErrorMessage("Input PostState.");
			return false;
		}
		
		this.setErrorMessage(null);
		
		preState.setName(textPre.getText());
		postState.setName(textPost.getText());
		transition.setPreState(preState);
		transition.setPostState(postState);
		transition.setMethod(textTransName.getText());
		transition.setCondition(textTransCondition.getText());
		
		return true;
	}
	
	public Transition getTransition(){
		return transition;
	}

}
