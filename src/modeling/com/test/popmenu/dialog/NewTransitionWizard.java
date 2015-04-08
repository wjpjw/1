package modeling.com.test.popmenu.dialog;

import model.Transition;

import org.eclipse.jface.wizard.Wizard;

public class NewTransitionWizard extends Wizard{

	private NewTransitionWizardPage page;
//	private Transition transition;
	
	public NewTransitionWizard() {
//		transition = new Transition();
		page = new NewTransitionWizardPage("page.type");
		addPage(page);
	}
	
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	// Referred by DAY9 <<<
	public boolean canFinish() {
		
		return page.isPageComplete();
		
	}
	
	public Transition getTransition(){
		return page.getTransition();
	}
	
}
