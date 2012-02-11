package impl;

public class MyPoint3D {

	private float x;
	private float y;
	private float z;
	
	public MyPoint3D() {
		super();
		x = 0;
		y = 0;
		z = 0;
	}
	
	public MyPoint3D(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public MyPoint3D add(MyPoint3D point) {
		float nx = x + point.getX();
		float ny = y + point.getY();
		float nz = z + point.getZ();
		return new MyPoint3D(nx, ny, nz);
	}
	
	public MyPoint3D sub(MyPoint3D point) {
		float nx = x - point.getX();
		float ny = y - point.getY();
		float nz = z - point.getZ();
		return new MyPoint3D(nx, ny, nz);
	}
	
	public MyPoint3D crossProduct(MyPoint3D point) {
		float nx = y*point.getZ() - z*point.getY();
		float ny = z*point.getX() - x*point.getZ();
		float nz = x*point.getY() - y*point.getX();
		return new MyPoint3D(nx,ny,nz);
	}
	
	public MyPoint3D mul(float t) {
		return new MyPoint3D(t*x, t*y, t*z);
	}
	
	public float dotProduct(MyPoint3D point) {
		return x*point.getX() + y*point.getY() + z*point.getZ();
	}
	
	public float getLength() {
		return (float)Math.sqrt(x*x + y*y + z*z);
	}
	
	public MyPoint3D normalize() {
		float div = x*x + y*y + z*z;
		x = x/div;
		y = y/div;
		z = z/div;
		return this;
	}
	
	public MyPoint3D div(float t) {
		return new MyPoint3D(x/t, y/t, z/t);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}
}
