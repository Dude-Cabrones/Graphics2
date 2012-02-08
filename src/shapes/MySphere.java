package shapes;

import impl.Calc;
import impl.CalcTuple;
import impl.MyMaterial;
import impl.MyPoint3D;
import impl.MyRay;

public class MySphere implements MyShape{

	private MyPoint3D position;	// sphere center
	private MyMaterial material;	// material
	private double radius;	// radius
	
	public MySphere(MyPoint3D position, double radius, MyMaterial material) {
		super();
		this.position = position;
		this.radius = radius;
		this.material = material;
	}
	
	public double rayIntersect(MyRay ray) {
		double boundingSquare = radius*radius;
		MyPoint3D origin = ray.getOrigin().sub(position);
		double a, b, c;
		a = ray.getDirection().dotProduct(ray.getDirection());
		b = 2*(origin.dotProduct(ray.getDirection()));
		c = origin.dotProduct(origin)-boundingSquare;
		
		// solve quad. equation
		CalcTuple tup = Calc.CalcQuadRoot(a, b, c);

		// return closest intersection point (if any)
		if(tup.getRoot() > 0) {
			return tup.getIntersectionA() >= 0 
				? tup.getIntersectionA() : tup.getIntersectionB();
		} else {
			return -1;
		}
	}
	
	public MyPoint3D getNormal(MyPoint3D point) {
		return point.sub(position).div(radius);
	}

	public double getRadius() {
		return radius;
	}

	@Override
	public MyMaterial getMaterial() {
		return material;
	}

	@Override
	public MyPoint3D getPosition() {
		return position;
	}

	@Override
	public void setMaterial(MyMaterial material) {
		this.material = material;
	}

	@Override
	public void setPosition(MyPoint3D position) {
		this.position = position;
	}
}
