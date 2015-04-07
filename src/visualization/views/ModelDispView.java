package visualization.views;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.SWT;

import visualization.views.delegation.ModelDispViewDelegation;

public class ModelDispView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "wjp_view.views.WJPView";

    private Canvas canvas=null;
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
        ModelDispViewDelegation.get_instance().set_up_model_disp_view(this);
		canvas= new Canvas(parent, SWT.WRAP);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				ModelDispViewDelegation.get_instance().get_vs().draw(arg0.gc);
			}
		});
	}
	
	public void refresh_label_image(){
		if(canvas!=null){
			canvas.redraw();
		}
	}
	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
}