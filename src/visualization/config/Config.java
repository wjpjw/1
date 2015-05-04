package visualization.config;

import org.eclipse.swt.graphics.Point;

//等会写个python文件作配置文件，读取。
public class Config {
	public static String get_config_directory(){
		return "C:/Users/Administrator/Desktop/statecharts/";
	}
	public static Point get_canvas_size(){
		return new Point(1200, 800);
	}
	public static Point get_state_rectangle_size() {
		return new Point(45,25);
	}
	public static int get_transition_cubic_control_point_distribution_circle_radius()
	{
		return 160;
	}
}
