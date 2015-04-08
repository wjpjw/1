package visualization.views.delegation.graphic.model;

public class Vector {
	public double x;
	public double y;
	public Vector(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	};
	public Vector(Vector v){
		this.x=v.x;
		this.y=v.y;
	}
	public void add(Vector v){
		this.x+=v.x;
		this.y+=v.y;
	}
	public int int_x(int scale){
		return (int)(x*scale);
	}
	public int int_y(int scale){
		return (int)(y*scale);
	}
	public double size(){
		return Math.pow(x*x+y*y, 0.5);
	}
	public Vector scale_to_min(){
		return new Vector(x/size(),y=y/size());
	}
	public Vector vertical() {
		return new Vector(y,-x);
	}
}
