package checking.view;

import java.util.ArrayList;

import model.Defect;
import model.State;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;

public class DefectView extends ViewPart {

	public static final String ID = "checking.view.DefectView"; //$NON-NLS-1$
	private Table table;

	public DefectView() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		 GridLayout g1=new GridLayout(); 
		  g1.numColumns=2; 
//		parent.setLayout(new GridLayout(1,true));
		  parent.setLayout(g1);
		table = new Table(parent, SWT.BORDER | SWT.FULL_SELECTION);
		final GridData gd=new GridData(GridData.FILL_BOTH); 
		gd.horizontalSpan=2; 
		table.setLayoutData(gd);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("×´Ì¬Ãû");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(200);
		tblclmnNewColumn_1.setText("ÃèÊö");
		
		
		//test
		ArrayList<Defect> defects = new ArrayList<Defect>();
		
		State state1 = new State();
		state1.setName("s1");
		Defect defect1 = new Defect();
		defect1.setRelatedState(state1);
		defect1.setDescription("s1");
		
		State state2 = new State();
		state2.setName("s2");
		Defect defect2 = new Defect();
		defect2.setRelatedState(state2);
		defect2.setDescription("s2");
		
		State state3 = new State();
		state3.setName("s3");
		Defect defect3 = new Defect();
		defect3.setRelatedState(state3);
		defect3.setDescription("s3");
		
		defects.add(defect1);
		defects.add(defect2);
		defects.add(defect3);
		
		for(int i=0;i<defects.size();i++)
		{
			TableItem item = new TableItem(table,SWT.NONE);
			item.setText(new String[]{defects.get(i).getRelatedState().getName(),defects.get(i).getDescription()}); 
		}
		
		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
