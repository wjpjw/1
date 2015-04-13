package modeling.com.test.popmenu.dialog;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class GenerateStateNumberWizardPage extends WizardPage {

	private Combo numberCombo;	
	private int numberOfState;	
	
	protected GenerateStateNumberWizardPage(String pageName) {
		super(pageName);
		this.setTitle("Select the number of state");
		this.setMessage("Select the number of state to create.");
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		this.setControl(composite);
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("Number of State: ");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1, 1));
		
		numberCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
		numberCombo.add("1");
		numberCombo.add("2");
		numberCombo.add("3");
		numberCombo.add("4");
		numberCombo.add("5");
		numberCombo.add("6");
		numberCombo.add("7");
		numberCombo.add("8");
		numberCombo.add("9");
		numberCombo.add("10");
		
		numberCombo.select(0);
		
		numberCombo.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1, 1));
		numberCombo.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				numberOfState = Integer.parseInt(numberCombo.getText());
			}});
		numberOfState = Integer.parseInt(numberCombo.getText());
	}

	public int getNumberOfState() {
		return numberOfState;
	}
	
	public void setNumberOfState(int number) {
		this.numberOfState = number;
	}	
	

}
