package visualization.views;



import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.SWT;

public class ModelDispView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "wjp_view.views.WJPView";

    private Label label;
    
	/**
	 * The constructor.
	 */
	public ModelDispView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
        label = new Label(parent, SWT.WRAP);
        ImageData newImageData = new ImageData("C:/Users/Administrator/Desktop/statecharts/drag.PNG").scaledTo(800, 600);
        Image tmpImage=new Image(Display.getCurrent(), newImageData);
        label.setImage(tmpImage);
  		

	}
	
	private void showMessage(String title, String message) {
		MessageDialog.openInformation(label.getShell(),title,message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
}