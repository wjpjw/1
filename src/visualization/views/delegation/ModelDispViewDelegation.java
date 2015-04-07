package visualization.views.delegation;

import visualization.views.ModelDispView;
import visualization.views.delegation.graphic.model.VisualizedStatechart;

public class ModelDispViewDelegation {
	private ModelDispViewDelegation(){}
	private static ModelDispViewDelegation instance=new ModelDispViewDelegation();
	private VisualizedStatechart visualized_statechart=new VisualizedStatechart();
	public static ModelDispViewDelegation get_instance(){
		return instance;
	}
	private ModelDispView model_disp_view=null;
	//called after modeling procedure
	public void visualize_statechart(){
		visualized_statechart.def_self();
	    if(model_disp_view!=null){
	    	model_disp_view.refresh_label_image();
	    }
	}
	public void set_up_model_disp_view(ModelDispView view){
		model_disp_view=view;
	}
	public VisualizedStatechart get_vs(){
		return visualized_statechart;
	}
	
	
	
	
	
}
