package modeling.com.test.popmenu.dialog;

import model.Transition;

import org.eclipse.jface.wizard.Wizard;

public class GenerateTransitionWizard_old extends Wizard{

	private GenerateTransitionWizardPage_old page;
//	private Transition transition;
	
	public GenerateTransitionWizard_old() {
//		transition = new Transition();
		page = new GenerateTransitionWizardPage_old("page.type");
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
