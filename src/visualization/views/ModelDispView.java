package visualization.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.SWT;

import config.Config;

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
        ImageData newImageData = new ImageData(Config.get_statechart_path());//.scaledTo(800, 600);
        Image tmpImage=new Image(Display.getCurrent(), newImageData);
        label.setImage(tmpImage);
	}
	
	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
}