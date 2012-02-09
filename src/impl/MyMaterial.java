package impl;

import java.awt.Color;

public class MyMaterial {

	private float diffuseC;
	private float ambientC;
	private Color color;
	
	public double getDiffuseC() {
		return diffuseC;
	}
	public void setDiffuseC(float diffuseC) {
		this.diffuseC = diffuseC;
	}
	public float getAmbientC() {
		return ambientC;
	}
	public void setAmbientC(float ambientC) {
		this.ambientC = ambientC;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
