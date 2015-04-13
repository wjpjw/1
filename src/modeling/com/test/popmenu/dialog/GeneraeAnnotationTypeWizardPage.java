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

public class GeneraeAnnotationTypeWizardPage extends WizardPage {

	private Combo typeCombo;	
	private String type;	
	
	protected GeneraeAnnotationTypeWizardPage(String pageName) {
		super(pageName);
		this.setTitle("Select annotation type (state/transition)");
		this.setMessage("Select the type of the annotation to create.");
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		this.setControl(composite);
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("Type: ");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1, 1));
		
		typeCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
		typeCombo.add(ComboKey.STATETYPE);
		typeCombo.add(ComboKey.TRANSITIONTYPE);
		if (type != null) {
			String [] items = typeCombo.getItems();
			for (int i = 0; i < items.length; i ++) {
				if (items[i].equalsIgnoreCase(type)) {
					typeCombo.select(i);
					break;
				}
			}
		} else {
			typeCombo.select(0);
		}
		typeCombo.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1, 1));
		typeCombo.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				type = typeCombo.getText().toLowerCase();
			}});
		type = typeCombo.getText().toLowerCase();
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}	
	
	
}
