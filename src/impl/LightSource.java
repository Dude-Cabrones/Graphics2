package impl;

public class LightSource {

	private MyPoint3D direction;
	private double brightness;
	
	public boolean intersects(MyPoint3D dir) {
		// check if the light source and the given direction go in opposite directions
		return direction.dotProduct(dir) < 0.0;
	}
	
	public double getBrightness() {
		return brightness;
	}

	public void setBrightness(double brightness) {
		this.brightness = brightness;
	}

	public MyPoint3D getDirection() {
		return direction;
	}

	public void setDirection(MyPoint3D direction) {
		direction.normalize();
		this.direction = direction;
	}
}
