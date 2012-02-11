package shapes;

import impl.CalcTuple;
import impl.MyMaterial;
import impl.MyPoint3D;
import impl.MyRay;

import java.util.LinkedList;


public interface MyShape {

	public CalcTuple rayIntersect(MyRay ray);
	public MyPoint3D getNormal(MyPoint3D point);
	
	// getter and setter methods
	public LinkedList<MyPoint3D> getKeyPoints();
	public void setKeyPoints(LinkedList<MyPoint3D> keyPoints);
	public MyMaterial getMaterial();
	public void setMaterial(MyMaterial material);
}
