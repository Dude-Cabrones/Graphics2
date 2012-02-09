package impl;


public class CalcTuple {

	/**
	 * Simple wrapper class...
	 */
	
	private int roots;
	private float intersectionA;
	private float intersectionB;
	
	public CalcTuple(int roots, float intersectionA,
			float intersectionB) {
		super();
		this.roots = roots;
		this.intersectionA = intersectionA;
		this.intersectionB = intersectionB;
	}

	public int getRoots() {
		return roots;
	}

	public float getIntersectionA() {
		return intersectionA;
	}

	public float getIntersectionB() {
		return intersectionB;
	}
}
