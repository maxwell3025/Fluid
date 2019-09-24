package vectors;

public class Point2D {
public float x,y;
	public Point2D(float x, float y) {
		this.x=x;
		this.y=y;
	}
	public Point2D add(Point2D other){
		return new Point2D(x+other.x,y+other.y);
	}
	public Point2D sub(Point2D other){
		return new Point2D(x-other.x,y-other.y);
	}
	public Point2D mul(float other){
		return new Point2D(x*other,y*other);
	}
	public Point2D div(float other){
		return new Point2D(x/other,y/other);
	}

}
