package impl;


public class CalcTuple {

	/**
	 * Simple wrapper class...
	 */
	
	private double root;
	private double intersectionA;
	private double intersectionB;
	
	public CalcTuple(double root, double intersectionA,
			double intersectionB) {
		super();
		this.root = root;
		this.intersectionA = intersectionA;
		this.intersectionB = intersectionB;
	}

	public double getRoot() {
		return root;
	}

	public double getIntersectionA() {
		return intersectionA;
	}

	public double getIntersectionB() {
		return intersectionB;
	}
}
