package modeling.com.test.popmenu.dialog;

import java.util.ArrayList;

import model.State;
import model.Transition;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

public class GenerateAnnotationWizard extends Wizard {

	private GeneraeAnnotationTypeWizardPage typePage;
	private GenerateStateNumberWizardPage stateNumberPage;
	private GenerateStateWizardPage statePage;
	private GenerateTransitionWizardPage transitionPage;
	
	public GenerateAnnotationWizard() {
		 typePage = new GeneraeAnnotationTypeWizardPage("page.type");
		 addPage(typePage);
		 stateNumberPage = new GenerateStateNumberWizardPage("page.stateNumber");
		 addPage(stateNumberPage);		 
		 statePage = new GenerateStateWizardPage("page.state");
		 addPage(statePage);
		 transitionPage = new GenerateTransitionWizardPage("page.transition");
		 addPage(transitionPage);
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == typePage) {
			String type = typePage.getType();
			if (type.equals(ComboKey.STATETYPE)) {
				return stateNumberPage;
			} else if (type.equals(ComboKey.TRANSITIONTYPE)){
				return transitionPage;
			}
		} else if (page == stateNumberPage) {
			statePage = new GenerateStateWizardPage("page.state",stateNumberPage.getNumberOfState());
			addPage(statePage);
			return statePage;
		}
		return null;
	}
	
	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		if (page == statePage) {
			return stateNumberPage;
		} else if (page == stateNumberPage || page == transitionPage) {
			return typePage;
		}
		return null;
	}
	
	@Override
	public boolean performFinish() {
//		String type = typePage.getType();
//		if (type.equals(ComboKey.STATETYPE)) {
//			return true;
//		} else if (type.equals("accommodation")) {
//			return true;
//		}
//		return false;
		return true;
	}
	
	@Override
	public boolean canFinish() {
		String type = typePage.getType();
		if (type.equals(ComboKey.STATETYPE)) {
			return (typePage.isPageComplete() && stateNumberPage.isPageComplete() && statePage.isPageComplete());
		} else if (type.equals(ComboKey.TRANSITIONTYPE)) {
			return (typePage.isPageComplete() && transitionPage.isPageComplete());
			
		}else {
			return false;
		}
		
	}

	public void setType(String type) {
		typePage.setType(type);
	}
	
	
	public String getType() {
		return typePage.getType();
	}
	
	public int getNumberOfState() {
		return stateNumberPage.getNumberOfState();
	}
	
	public ArrayList<State> getStatelist(){
		return statePage.getStatelist();
	}
	
	public Transition getTransition(){
		return transitionPage.getTransition();
	}
}
