package impl;


public class CalcTuple {

	/**
	 * Simple wrapper class...
	 */
	
	private int roots;
	private float closestIntersection;
	private float intersectionA;
	private float intersectionB;
	
	public CalcTuple() {
		super();
	}
	
	public CalcTuple(int roots, float intersectionA,
			float intersectionB) {
		super();
		this.roots = roots;
		this.intersectionA = intersectionA;
		this.intersectionB = intersectionB;
	}
	
	public float getClosestIntersection() {
		return closestIntersection;
	}

	public void setClosestIntersection(float closestIntersection) {
		this.closestIntersection = closestIntersection;
	}

	public int getRoots() {
		return roots;
	}
	
	public void setRoots(int roots) {
		this.roots = roots;
	}

	public float getIntersectionA() {
		return intersectionA;
	}

	public float getIntersectionB() {
		return intersectionB;
	}
}
