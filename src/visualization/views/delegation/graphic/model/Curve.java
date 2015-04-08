package visualization.views.delegation.graphic.model;

import java.util.HashMap;

import model.State;
import model.Transition;

import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Point;

import config.Config;

public class Curve {
	private Path path=new Path(null);
	private Path arrow1=new Path(null);
	private Path arrow2=new Path(null);
	private HashMap<State, Point> state_position_map;
	private State prestate,poststate;
	private TransitionStatePair state_pair;
	private Transition t;
	public Point start,dest,control1,control2,control3,control4,mid;
	public static int arrow_len=20;
	public static int arrow_width=5;
	private Direction direction=Direction.ToEast;
	private Vector tangent_on_dest;  
	enum Direction{
		ToEast,ToWest,ToNorth,ToSouth
	}
	public Curve(TransitionStatePair state_pair, HashMap<State, Point> state_position_map, Transition t) {
		this.state_position_map=state_position_map;
		this.state_pair=state_pair;
		this.t=t;
		this.prestate=state_pair.preState;
		this.poststate=state_pair.postState;
		determine_start_and_dest_points();
		determine_control_points();
		path.moveTo(start.x, start.y);
		path.cubicTo(control1.x, control1.y, control2.x, control2.y, mid.x,mid.y);
		path.cubicTo(control3.x, control3.y, control4.x, control4.y, dest.x, dest.y);
		determine_arrows();
		
		
	
	}
	private void determine_arrows(){
		if(is_straight()){
			tangent_on_dest=new Vector(dest.x-start.x,dest.y-start.y).scale_to_min();
		}
		else{
			tangent_on_dest=new Vector(dest.x-control4.x,dest.y-control4.y).scale_to_min();//ÇÐÏß
		}
		Vector normal_on_dest=tangent_on_dest.vertical();//·¨Ïß
		arrow1.moveTo(dest.x, dest.y);
		arrow2.moveTo(dest.x, dest.y);
		
		Point orthocenter=new Point(dest.x-tangent_on_dest.int_x(arrow_len), dest.y-tangent_on_dest.int_y(arrow_len));
		Point arrow_tail1=new Point(orthocenter.x+normal_on_dest.int_x(arrow_width), orthocenter.y+normal_on_dest.int_y(arrow_width));
		Point arrow_tail2=new Point(orthocenter.x-normal_on_dest.int_x(arrow_width), orthocenter.y-normal_on_dest.int_y(arrow_width));
		arrow1.lineTo(arrow_tail1.x, arrow_tail1.y);
		arrow2.lineTo(arrow_tail2.x, arrow_tail2.y);
	}
	public Path get_path(){
		return path;
	}
	public Path get_arrow_path1(){
		return arrow1;
	}
	public Path get_arrow_path2(){
		return arrow2;
	}
	public Point get_text_point(){
		return mid;
	}
	public boolean is_straight(){
		return state_pair.get_index(t)==0;
	}
	private Point pos_on_circle_for_start(int i,int nr, Point center){
		double angle_revision=0;
		if(direction==Direction.ToSouth){
			angle_revision=Math.PI/4;
		}
		else if(direction==Direction.ToWest){
			angle_revision=Math.PI;
		}
		else if(direction==Direction.ToNorth){
			angle_revision=-Math.PI/2;
		}
		int radius=Config.get_transition_cubic_control_point_distribution_circle_radius();
		double angle=0.01*2*Math.PI*i/nr+angle_revision;
		int x=center.x+(int)(radius*Math.cos(angle));
		int y=center.y+(int)(radius*Math.sin(angle));
		return new Point(x, y);
	}
	private Point pos_on_circle_for_dest(int i,int nr, Point center){
		double angle_revision=0;
		if(direction==Direction.ToEast){
			angle_revision=Math.PI;
		}
		else if(direction==Direction.ToSouth){
			angle_revision=-Math.PI/2;
		}
		else if(direction==Direction.ToNorth){
			angle_revision=Math.PI/4;
		}
		int radius=Config.get_transition_cubic_control_point_distribution_circle_radius();
		double angle=0.25*2*Math.PI*i/nr+angle_revision;
		int x=center.x+(int)(radius*Math.cos(angle));
		int y=center.y+(int)(radius*Math.sin(angle));
		return new Point(x, y);
	}
	private void determine_control_points(){
		control1=pos_on_circle_for_start(state_pair.get_index(t), state_pair.get_nr(), start);
		control4=pos_on_circle_for_dest(state_pair.get_index(t), state_pair.get_nr(), dest);
		mid=mid_point(control1, control4);
		control2=mid_point(control1, mid);
		control3=mid_point(mid, control4);
	}
	private Point mid_point(Point a,Point b){
		return new Point((a.x+b.x)/2, (a.y+b.y)/2);
	}
	private void determine_start_and_dest_points(){
		Point prestate_pos=state_position_map.get(prestate);
		Point poststate_pos=state_position_map.get(poststate);
		Point vector=new Point(poststate_pos.x-prestate_pos.x, poststate_pos.y-prestate_pos.y);
		if(vector.x>=0){
			if(Math.abs(vector.x)>=Math.abs(vector.y)){
				start=pos_on_state_rectangle_east(prestate);
				dest=pos_on_state_rectangle_west(poststate);
				direction=Direction.ToEast;
			}
		}
		else{
			if(Math.abs(vector.x)>=Math.abs(vector.y)){
				start=pos_on_state_rectangle_west(prestate);
				dest=pos_on_state_rectangle_east(poststate);
				direction=Direction.ToWest;
			}
		}
		if(vector.y>=0){
			if(Math.abs(vector.y)>=Math.abs(vector.x)){
				start=pos_on_state_rectangle_south(prestate);
				dest=pos_on_state_rectangle_north(poststate);
				direction=Direction.ToSouth;
			}
		}
		else{
			if(Math.abs(vector.y)>=Math.abs(vector.x)){
				start=pos_on_state_rectangle_north(prestate);
				dest=pos_on_state_rectangle_south(poststate);
				direction=Direction.ToNorth;
			}
		}
	}
	private Point pos_on_state_rectangle_east(State state){
		Point center= state_position_map.get(state);
		return new Point(center.x+Config.get_state_rectangle_size().x/2, center.y);
	}
	private Point pos_on_state_rectangle_west(State state){
		Point center= state_position_map.get(state);
		return new Point(center.x-Config.get_state_rectangle_size().x/2, center.y);
	}
	private Point pos_on_state_rectangle_north(State state){
		Point center= state_position_map.get(state);
		return new Point(center.x, center.y-Config.get_state_rectangle_size().y/2);
	}
	private Point pos_on_state_rectangle_south(State state){
		Point center= state_position_map.get(state);
		return new Point(center.x, center.y+Config.get_state_rectangle_size().y/2);
	}

}
