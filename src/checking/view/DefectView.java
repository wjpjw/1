package checking.view;

import java.util.ArrayList;

import modeling.model.State;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;

import checking.factory.ServiceFactory;
import checking.model.Defect;

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
		ArrayList<Defect> defects = ServiceFactory.getServiceInstance().getDefects();
		
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
