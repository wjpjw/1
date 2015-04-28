package modeling.com.test.popmenu.dialog;

import java.util.ArrayList;

import model.State;
import modeling.com.test.popmenu.key.ComboKey;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class GenerateStateWizardPage extends WizardPage {

	private int numberOfState;
	private ArrayList<Text> textStateNameList;
	private ArrayList<Combo> initComboList;
	private ArrayList<Combo> exceptionComboList;

	private ArrayList<State> stateList;

	protected GenerateStateWizardPage(String pageName) {
		super(pageName);
		this.setTitle("Input state infomation");
		this.setMessage("Input state infomation.");
		numberOfState = 1;
		textStateNameList = new ArrayList<Text>();
		initComboList = new ArrayList<Combo>();
		exceptionComboList = new ArrayList<Combo>();
		stateList = new ArrayList<State>();

	}

	public GenerateStateWizardPage(String pageName, int numberOfState) {
		super(pageName);
		this.setTitle("Input state infomation");
		this.setMessage("Input state infomation.");
		this.numberOfState = numberOfState;
		textStateNameList = new ArrayList<Text>();
		initComboList = new ArrayList<Combo>();
		exceptionComboList = new ArrayList<Combo>();
		stateList = new ArrayList<State>();

	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(4, false);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));
		this.setControl(composite);

		for (int i = 0; i < numberOfState; i++) {
			Label label = new Label(composite, SWT.NONE);
			label.setText("State: ");
			label.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING,
					false, false, 1, 1));

			Text textStateName = new Text(composite, SWT.BORDER);
			GridData data = new GridData(SWT.BEGINNING, SWT.BEGINNING, false,
					false, 1, 1);
			data.widthHint = 200;
			textStateName.setLayoutData(data);
			textStateNameList.add(textStateName);

			Combo initCombo = new Combo(composite, SWT.DROP_DOWN
					| SWT.READ_ONLY);
			initCombo.add(ComboKey.NOTINITSTATE);
			initCombo.add(ComboKey.ISINITSTATE);
			initCombo.select(0);
			data = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1,
					1);
			data.widthHint = 100;
			initCombo.setLayoutData(data);
			initComboList.add(initCombo);

			Combo exceptionCombo = new Combo(composite, SWT.DROP_DOWN
					| SWT.READ_ONLY);
			exceptionCombo.add(ComboKey.NOTEXCEPTIONSTATE);
			exceptionCombo.add(ComboKey.EXCEPTIONSTATE);
			exceptionCombo.select(0);
			data = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1,
					1);
			data.widthHint = 100;
			exceptionCombo.setLayoutData(data);
			exceptionComboList.add(exceptionCombo);

			State state = new State();
			state.setIs_init(false);
			state.setIs_exception(false);
			stateList.add(state);
		}
		addSelectionActions();
		setPageComplete(false);
		addTextmodifyActions();
		
	}

	private void addSelectionActions() {

		SelectionListener exceptioncomboSelectionListener = new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				for (int i = 0; i < numberOfState; i++) {
//					System.out.println("我是exceptioncomboSelectionListener" + i);
					if (exceptionComboList.get(i).getText()
							.equals(ComboKey.EXCEPTIONSTATE)) {
						stateList.get(i).setIs_exception(true);
						initComboList.get(i).select(0);
						stateList.get(i).setIs_init(false);
					} else {
						stateList.get(i).setIs_exception(false);
					}
				}
			}
		};

		SelectionListener initcomboSelectionListener = new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				for (int i = 0; i < numberOfState; i++) {
//					System.out.println("我是initcomboSelectionListener" + i);
					if (initComboList.get(i).getText()
							.equals(ComboKey.ISINITSTATE)) {
						stateList.get(i).setIs_init(true);
					} else {
						stateList.get(i).setIs_init(false);
					}

				}
			}
		};

		for (int i = 0; i < numberOfState; i++) {

			exceptionComboList.get(i).addSelectionListener(
					exceptioncomboSelectionListener);
			initComboList.get(i).addSelectionListener(
					initcomboSelectionListener);
//			System.out.println("我是comboi" + i);
		}

	}

	private void addTextmodifyActions() {

		ModifyListener textModifyListener = new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setPageComplete(isInputValid());
			}
		};
		for (int i = 0; i < numberOfState; i++) {
			textStateNameList.get(i).addModifyListener(textModifyListener);
			// System.out.println("我是i" + i);
		}

	}

	private boolean isInputValid() {
		for (int i = 0; i < numberOfState; i++) {
			if (textStateNameList.get(i).getText().isEmpty()) {
				this.setErrorMessage("Input AllStatename.");
				return false;
			}
		}

		this.setErrorMessage(null);

		for (int i = 0; i < numberOfState; i++) {
			stateList.get(i).setName(textStateNameList.get(i).getText());
		}
		// System.out.println("isInputValid() true");
		return true;
	}

	public ArrayList<State> getStatelist() {
		return stateList;
	}

}
