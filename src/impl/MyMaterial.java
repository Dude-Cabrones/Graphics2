package impl;

import java.awt.Color;

public class MyMaterial {

	private double diffuseC;
	private double ambientC;
	private Color color;
	
	public double getDiffuseC() {
		return diffuseC;
	}
	public void setDiffuseC(double diffuseC) {
		this.diffuseC = diffuseC;
	}
	public double getAmbientC() {
		return ambientC;
	}
	public void setAmbientC(double ambientC) {
		this.ambientC = ambientC;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
