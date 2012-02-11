package shapes;

import java.util.LinkedList;

import impl.Calc;
import impl.CalcTuple;
import impl.MyMaterial;
import impl.MyPoint3D;
import impl.MyRay;

public class MySphere implements MyShape{

	private LinkedList<MyPoint3D> keyPoints;	// sphere center
	private MyMaterial material;	// material
	private float radius;	// radius
	
	public MySphere(MyPoint3D position, float radius, MyMaterial material) {
		super();
		LinkedList<MyPoint3D> points = new LinkedList<MyPoint3D>();
		points.add(position);
		this.keyPoints = points;
		this.radius = radius;
		this.material = material;
	}
	
	@Override
	public CalcTuple rayIntersect(MyRay ray) {
		float boundingSquare = radius*radius;
		MyPoint3D origin = ray.getOrigin().sub(keyPoints.getFirst());
		float a, b, c;
		a = ray.getDirection().dotProduct(ray.getDirection());
		b = 2*(origin.dotProduct(ray.getDirection()));
		c = origin.dotProduct(origin)-boundingSquare;
		
		// solve quad. equation
		CalcTuple tup = Calc.CalcQuadRoot(a, b, c);

		// return closest intersection point (if any)
		if(tup.getRoots() > 0) {
			if(tup.getIntersectionA() >= 0) tup.setClosestIntersection(tup.getIntersectionA());
			else tup.setClosestIntersection(tup.getIntersectionB());
			return tup;
		} else {
			tup.setClosestIntersection(-1);
			return tup;
		}
	}
	
	@Override
	public MyPoint3D getNormal(MyPoint3D point) {
		return point.sub(keyPoints.getFirst()).div(radius);
	}

	public double getRadius() {
		return radius;
	}

	@Override
	public MyMaterial getMaterial() {
		return material;
	}

	@Override
	public LinkedList<MyPoint3D> getKeyPoints() {
		return keyPoints;
	}

	@Override
	public void setMaterial(MyMaterial material) {
		this.material = material;
	}

	@Override
	public void setKeyPoints(LinkedList<MyPoint3D> keyPoints) {
		this.keyPoints = keyPoints;
	}
}
