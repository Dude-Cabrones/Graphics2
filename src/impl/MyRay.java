package impl;


public class MyRay {

	private MyPoint3D origin;
	private MyPoint3D direction;
	
	public MyPoint3D eval(double t) {
		return origin.add(direction.mul(t));
	}

	public void setOrigin(MyPoint3D origin) {
		this.origin = origin;
	}

	public void setDirection(MyPoint3D direction) {
		this.direction = direction;
	}

	public MyPoint3D getOrigin() {
		return origin;
	}

	public MyPoint3D getDirection() {
		return direction;
	}
}
