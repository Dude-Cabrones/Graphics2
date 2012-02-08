package impl;

import java.awt.Color;

public class MyIntersectInfo {

	/// Position on ray where the cut occurred
    private double rayPosition;
    
    /// Normal of surface at cut position
    private MyPoint3D normal;
    
    /// Texture coordinates
    private MyPoint3D texture;
    
    /// Material of surface at intersection position
    private MyMaterial material;
    
    /// The local color of the intersection. This must be optional
    /// for all materials!!! Some Objects may always return black here.
    private Color localColor;

    public MyIntersectInfo() {
    	super();
    }
    
	public MyIntersectInfo(double rayPosition, MyPoint3D normal,
			MyPoint3D texture, MyMaterial material, Color localColor) {
		super();
		this.rayPosition = rayPosition;
		this.normal = normal;
		this.texture = texture;
		this.material = material;
		this.localColor = localColor;
	}

	public double getRayPosition() {
		return rayPosition;
	}

	public void setRayPosition(double rayPosition) {
		this.rayPosition = rayPosition;
	}

	public MyPoint3D getNormal() {
		return normal;
	}

	public void setNormal(MyPoint3D normal) {
		this.normal = normal;
	}

	public MyPoint3D getTexture() {
		return texture;
	}

	public void setTexture(MyPoint3D texture) {
		this.texture = texture;
	}

	public MyMaterial getMaterial() {
		return material;
	}

	public void setMaterial(MyMaterial material) {
		this.material = material;
	}

	public Color getLocalColor() {
		return localColor;
	}

	public void setLocalColor(Color localColor) {
		this.localColor = localColor;
	}
}
