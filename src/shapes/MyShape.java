package shapes;

import impl.MyMaterial;
import impl.MyPoint3D;
import impl.MyRay;


public interface MyShape {

	public float rayIntersect(MyRay ray);
	public MyPoint3D getNormal(MyPoint3D point);
	
	// getter and setter methods
	public MyPoint3D getPosition();
	public void setPosition(MyPoint3D position);
	public MyMaterial getMaterial();
	public void setMaterial(MyMaterial material);
}
