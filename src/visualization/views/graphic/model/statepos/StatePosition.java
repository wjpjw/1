package visualization.views.graphic.model.statepos;

import java.util.ArrayList;
import java.util.HashMap;

import modeling.model.State;

import org.eclipse.swt.graphics.Point;

import visualization.config.Config;

public class StatePosition {
	public HashMap<State, Point> state_position_map=new HashMap<State, Point>();
	private static Point center=new Point(Config.get_canvas_size().x/2, Config.get_canvas_size().y/2);
	private static int radius=Config.get_canvas_size().y/2-30;//这里调整状态图相对边框的大小

	public StatePosition() {}
	public void init_from(ArrayList<State> states) {
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

}
