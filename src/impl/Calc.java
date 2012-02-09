package impl;

public class Calc {

	public static CalcTuple CalcQuadRoot(float a, float b, float c) {
		float x1, x2;
		float det = b*b - 4*a*c;
		if(det < 0) {
			return new CalcTuple(0, 0, 0);
		}
		double sign;
		if(b<0) {
			sign = -1.0;
		} else {
			sign = 1.0;
		}
		det = (float)Math.sqrt(det);
		float q = (float)-0.5*(b+(float)sign*det);
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