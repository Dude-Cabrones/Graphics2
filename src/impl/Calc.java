package impl;

public class Calc {

	public static CalcTuple CalcQuadRoot(double a, double b, double c) {
		double x1, x2;
		double det = b*b - 4*a*c;
		if(det < 0) {
			return new CalcTuple(0, 0.0, 0.0);
		}
		double sign;
		if(b<0) {
			sign = -1.0;
		} else {
			sign = 1.0;
		}
		det = Math.sqrt(det);
		double q = -0.5*(b+sign*det);
		x1 = q/a;
		x2 = c/q;
		
		// sort
		if(x1 > x2) {
			q = x2;
			x2 = x1;
			x1 = q;
		}
		
		if(x1 == x2) {
			return new CalcTuple(1, x1, x2);
		} else {
			return new CalcTuple(2, x1, x2);
		}
	}
}