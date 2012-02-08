package impl;

public class MyPoint3D {

	private double x;
	private double y;
	private double z;
	
	public MyPoint3D() {
		super();
		x = 0;
		y = 0;
		z = 0;
	}
	
	public MyPoint3D(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public MyPoint3D add(MyPoint3D point) {
		double nx = x + point.getX();
		double ny = y + point.getY();
		double nz = z + point.getZ();
		return new MyPoint3D(nx, ny, nz);
	}
	
	public MyPoint3D sub(MyPoint3D point) {
		double nx = x - point.getX();
		double ny = y - point.getY();
		double nz = z - point.getZ();
		return new MyPoint3D(nx, ny, nz);
	}
	
	public MyPoint3D mul(double t) {
		return new MyPoint3D(t*x, t*y, t*z);
	}
	
	public double dotProduct(MyPoint3D point) {
		return x*point.getX() + y*point.getY() + z*point.getZ();
	}
	
	public void normalize() {
		double div = x*x + y*y + z*z;
		x = x/div;
		y = y/div;
		z = z/div;
	}
	
	public MyPoint3D div(double t) {
		return new MyPoint3D(x/t, y/t, z/t);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
}
