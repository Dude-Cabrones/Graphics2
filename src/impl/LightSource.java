package impl;

import impl.MyColor;

public class LightSource {

	private MyPoint3D origin;
	private MyPoint3D direction;
	private float power;
	private MyColor color;
	
	public boolean intersects(MyPoint3D dir) {
		// check if the light source and the given direction go in opposite directions
		// return direction.dotProduct(dir) < 0.0;
		return true;
	}
	
	public MyPoint3D getOrigin() {
		return origin;
	}

	public void setOrigin(MyPoint3D origin) {
		this.origin = origin;
	}

	public float getPower() {
		return power;
	}

	public void setBrightness(float power) {
		this.power = power;
	}

	public MyPoint3D getDirection() {
		return direction;
	}

	public void setDirection(MyPoint3D direction) {
		direction.normalize();
		this.direction = direction;
	}

	public void setColor(MyColor color) {
		this.color = color;
	}

	public MyColor getColor() {
		return color;
	}
}
