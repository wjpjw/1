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
	public double size(){
		return Math.pow(x*x+y*y, 0.5);
	}
	public void scale_to_meta(){
		x/=size();
		y/=size();
	}
	public Vector vertical() {
		return new Vector(y,-x);
	}
}
