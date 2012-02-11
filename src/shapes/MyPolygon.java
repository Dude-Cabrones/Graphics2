package shapes;

import java.util.LinkedList;

import impl.CalcTuple;
import impl.MyMaterial;
import impl.MyPoint3D;
import impl.MyRay;

public class MyPolygon implements MyShape{

	private LinkedList<MyPoint3D> keyPoints;
	private MyMaterial material;
	
	public MyPolygon(LinkedList<MyPoint3D> keyPoints, MyMaterial material) {
		super();
		this.keyPoints = keyPoints;
		this.material = material;
	}
	
	@Override
	public MyMaterial getMaterial() {
		return material;
	}

	@Override
	public MyPoint3D getNormal(MyPoint3D point) {
		// (V1 - V0)x(V2 - V0)
		return (keyPoints.get(1).sub(keyPoints.get(0))).
				crossProduct((keyPoints.get(2).sub(keyPoints.get(1)))).normalize();
	}

	@Override
	public LinkedList<MyPoint3D> getKeyPoints() {
		return keyPoints;
	}
	
	@Override
	public CalcTuple rayIntersect(MyRay ray) {
		CalcTuple resultTuple = new CalcTuple();
		// (N*(PlanePoint-RayOrig))/(N*RayDir)
		float t = (getNormal(null).dotProduct(keyPoints.getFirst().sub(ray.getOrigin())))
					/(getNormal(null).dotProduct(ray.getDirection()));
		// intersection point with polygon plane
		MyPoint3D intersection = ray.getOrigin().add(ray.getDirection().mul(t));
		
		if(t > 0 && isInsidePolygon(intersection)) {
			resultTuple.setRoots(1);
			resultTuple.setClosestIntersection(t);
		}
		else {
			resultTuple.setRoots(-1);
			resultTuple.setClosestIntersection(-1);
		}
		return resultTuple;
	}
	
	private boolean isInsidePolygon(MyPoint3D intersection) {
		// odd/even test
		// calculate in x-y coordinates - neglect z coordinate
		int cnt = 0;
		for(int i=0; i<keyPoints.size(); i++) {
			MyPoint3D pointA = keyPoints.get(i);
			MyPoint3D pointB = null;
			if(i != keyPoints.size()-1) {
				pointB = keyPoints.get(i+1);
			} else {
				pointB = keyPoints.get(0);
			}
			if(pointA.getY() > intersection.getY() != pointB.getY() > intersection.getY() &&
					intersection.getX() < (pointB.getX()-pointA.getX())*(intersection.getY()-pointA.getY())/
					(pointB.getY()-pointA.getY()) + pointA.getX()) {
				cnt++;
			}
		}
		return cnt%2 == 1;
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
