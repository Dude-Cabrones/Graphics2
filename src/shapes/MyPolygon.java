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
		// TODO: improve
		// (V3 - V2)x(V1 - V2)
		return (keyPoints.get(2).sub(keyPoints.get(1))).
				crossProduct((keyPoints.get(0).sub(keyPoints.get(1)))).normalize().mul(-1);
	}

	@Override
	public LinkedList<MyPoint3D> getKeyPoints() {
		return keyPoints;
	}
	
	@Override
	public CalcTuple rayIntersect(MyRay ray) {
		CalcTuple resultTuple = new CalcTuple();
		// (N*(Q-E))/(E*D)
		float t = (getNormal(null).dotProduct((keyPoints.get(0).sub(ray.getOrigin())))
				/(ray.getOrigin().dotProduct(ray.getDirection())));
		// intersection point with polygon plane
		MyPoint3D intersection = ray.getOrigin().add(ray.getDirection().mul(t));
//		System.out.println("x:"+intersection.getX()+" y:"+intersection.getY()+" z:"+intersection.getZ());
		
		if(t >= 0) {
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
			if(intersection.getX() > pointA.getX() || intersection.getX() > pointB.getX()) {
				if(intersection.getY() <= pointA.getY() && intersection.getY() >= pointB.getY()
						|| intersection.getY() >= pointA.getY() && intersection.getY() <= pointB.getY()) {
					System.out.println("CNT");
					cnt++;
				}
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
