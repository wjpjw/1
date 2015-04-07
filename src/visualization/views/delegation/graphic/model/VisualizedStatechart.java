package visualization.views.delegation.graphic.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;

import config.Config;
import model.SelectedClass;
import model.State;
import model.Transition;

public class VisualizedStatechart{
	public VisualizedStatechart() {}
	private HashMap<State, Point> state_position_map=new HashMap<State, Point>();
	private HashMap<Transition, Curve> transition_curve_map=new HashMap<Transition, Curve>();
	private static Point center=new Point(Config.get_canvas_size().x/2, Config.get_canvas_size().y/2);
	private static int radius=Config.get_canvas_size().y/2-10;
	private ArrayList<State> states=SelectedClass.getInstance().getStatechart().getStates();
	private ArrayList<Transition> transitions=SelectedClass.getInstance().getStatechart().getTransitions();
	private TransitionStatePairList transition_state_pair_list=new TransitionStatePairList();
	public void def_self(){
		auto_def_state_positions();
		auto_def_transition_curves();
	}
	private void auto_def_state_positions(){
		int nr=states.size();
		for(int i=0;i<states.size();i++){
			state_position_map.put(states.get(i), pos_on_circle(i,nr));
		}
	}
	//nr>=1, i>=0, default initial position is at 3 o'clock 
	//center of state rectangle
	private Point pos_on_circle(int i,int nr){
		double angle=2*Math.PI*i/nr;
		int x=center.x+(int)(radius*Math.cos(angle));
		int y=center.y+(int)(radius*Math.sin(angle));
		return new Point(x, y);
	}
	
	private void auto_def_transition_curves() {
		transition_state_pair_list.init_pairs_from(transitions);
		for (int i = 0; i < transitions.size(); i++) {
			Transition transition=transitions.get(i);
			TransitionStatePair state_pair=transition_state_pair_list.get_pair(transition);
			def_transition_curve(state_pair,transition);
		}
	}
	private void def_transition_curve(TransitionStatePair state_pair,Transition transition) {
		transition_curve_map.put(transition, new Curve(state_pair,state_position_map,transition));
	}
	public void draw(GC gc) {
		//[0] bound rectangle
		gc.setLineWidth(10);
	    gc.drawRectangle(0,0,Config.get_canvas_size().x,Config.get_canvas_size().y);
	    //[1] state rectangles
	    gc.setLineWidth(3);
	    for (int i = 0; i < states.size(); i++) {
	    	State state=states.get(i);
	    	Point state_pos=state_position_map.get(state);
	    	if(state_pos==null)return;
	    	int width=Config.get_state_rectangle_size().x;
	    	int height=Config.get_state_rectangle_size().y;
	    	gc.drawRectangle(state_pos.x-width/2,state_pos.y-height/2,width,height);
			gc.drawText(state.getName(), state_pos.x-width+10, state_pos.y-height/2-10);
		}
	    //[2] transition curves
	    gc.setLineWidth(1);
	    for (int i = 0; i < transitions.size(); i++) {
			Transition transition=transitions.get(i);
			Curve transition_curve=transition_curve_map.get(transition);
	    	if(transition_curve==null)return;
	    	if(transition_curve.is_straight()){
		    	Point s=transition_curve.start;
		    	Point d=transition_curve.dest;
		    	gc.drawLine(s.x, s.y, d.x, d.y);
		    	gc.drawText(transition.getMethod()+"("+transition.getCondition()+")",(s.x+d.x)/2,(s.y+d.y)/2);
	    	}
	    	else{
		    	gc.drawPath(transition_curve.get_path());
		    	gc.drawText(transition.getMethod()+"("+transition.getCondition()+")",
		    			transition_curve.get_text_point().x, transition_curve.get_text_point().y);
	    	}
	    	gc.drawPath(transition_curve.get_arrow_path1());
	    	gc.drawPath(transition_curve.get_arrow_path2());
		}
	}
	
}
