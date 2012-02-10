package impl;

import java.awt.Color;

public class MyMaterial {

	private float diffuseC;
	private float ambientC;
	private float specularC;
	private float reflectionProperty;	// < 32: rough	>32: smooth
	private Color color;
	
	public float getReflectionProperty() {
		return reflectionProperty;
	}
	public void setReflectionProperty(float reflectionProperty) {
		this.reflectionProperty = reflectionProperty;
	}
	public float getSpecularC() {
		return specularC;
	}
	public void setSpecularC(float specularC) {
		this.specularC = specularC;
	}
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
