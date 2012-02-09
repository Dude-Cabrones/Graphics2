package world;

import impl.LightSource;
import impl.MyPoint3D;
import impl.MyRay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import shapes.MyShape;

public class World extends JPanel{

	/**
	 * dummy serialization to suppress warnings
	 */
	private static final long serialVersionUID = 1L;
	
	// screen resolution parameters
	private int widthPix;
	private int heightPix;
	
	// background color
	private Color bgColor;
	
	// viewer
	private MyPoint3D viewer;
	
	// view plane coordinates
	private float zViewPlane;
	private float leftViewPlane;
	private float rightViewPlane;
	private float bottomViewPlane;
	private float topViewPlane;
	
	// objects in our world
	private LinkedList<MyShape> objects;
	
	// light sources in our world
	private LinkedList<LightSource> lights;
	
	// pixel memory map
	int[] memory;
	
	public World(MyPoint3D viewer, float zViewPlane, float leftViewPlane, 
			float rightViewPlane, float bottomViewPlane, float topViewPlane, 
			int widthPix, int heightPix, Color bgColor) {
		this.setSize(widthPix, heightPix);
		this.viewer = viewer;
		this.zViewPlane = zViewPlane;
		this.leftViewPlane = leftViewPlane;
		this.rightViewPlane = rightViewPlane;
		this.bottomViewPlane = bottomViewPlane;
		this.topViewPlane = topViewPlane;
		this.widthPix = widthPix;
		this.heightPix = heightPix;
		this.bgColor = bgColor;
		objects = new LinkedList<MyShape>();
		lights = new LinkedList<LightSource>();
		memory = new int[widthPix*heightPix];
	}
	
	public void addShape(MyShape shape) {
		objects.add(shape);
	}
	
	public void addLight(LightSource light) {
		lights.add(light);
	}
	
	private MyRay shootPixelRay(int x, int y) {
		MyRay out = new MyRay();
		
		float xPart, yPart;
		xPart = (rightViewPlane - leftViewPlane)/widthPix;
		yPart = (topViewPlane - bottomViewPlane)/heightPix;

		xPart = leftViewPlane + (float)0.5*xPart + ((float)x)*xPart;
		yPart = bottomViewPlane + (float)0.5*yPart + ((float)y)*yPart;
		
		// get ray direction and origin: perspective
		//out.setOrigin(viewer);
		//MyPoint3D dir = new MyPoint3D(xPart - viewer.getX(), yPart - viewer.getY(), zViewPlane - viewer.getZ());
		
		// get ray direction and origin: perpendicular
		out.setOrigin(new MyPoint3D(xPart,yPart,viewer.getZ()));
		MyPoint3D dir = new MyPoint3D(0,0,1);
		
		// normalize
		dir.normalize();
		out.setDirection(dir);
		return out;
	}
	
	private MyRay shootRay(MyPoint3D from, MyPoint3D dir) {
		MyRay ray = new MyRay();
		ray.setOrigin(from);
		ray.setDirection(dir);
		return ray;
	}
	
	private float getDiffuseComponent(LightSource source, MyPoint3D normal, 
			MyPoint3D intersection, MyShape shape) {
		// TODO: Calculate Factor here that will be multiplied with real color
		return (float)source.getPower()*(float)source.getDirection().
				dotProduct(normal)*(float)shape.getMaterial().getDiffuseC();
	}
	
	private float getAmbientComponent(MyShape shape) {
		return shape.getMaterial().getAmbientC();
	}
	
	private void raytraceImage(int width, int height) {
		// shoot ray through each pixel
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				MyRay ray = shootPixelRay(x, y);
				float maxDist = Float.POSITIVE_INFINITY;
				
				// check each object for intersections 
				// --> get the one with shortest distance
				MyShape currentShape = null;
				for(MyShape shape : objects) {
					float hitDist = shape.rayIntersect(ray);
					if(hitDist > zViewPlane - viewer.getZ() && maxDist > hitDist) {
						maxDist = hitDist;
						currentShape = shape;
					}
				}
				
				// set pixel in buffer
				if(maxDist < Float.POSITIVE_INFINITY) {
					// we got an intersection
					// -> check for light source
					// shoot from intersection point in points surface normal direction
					MyPoint3D intersection = ray.getOrigin().add(ray.getDirection().mul(maxDist));
					MyPoint3D normal = currentShape.getNormal(intersection);
					
					
					float diffLight = 0;
					// check if normal points towards any light source
					for(LightSource source : lights) {
						if(source.intersects(currentShape.getNormal(intersection))) {
							// now we shoot a ray from the intersection point and see 
							// if any other object is in our way --> shadowed?
							MyRay ray2 = shootRay(intersection, normal);
							boolean isShadowed = false;
							for(MyShape shape : objects) {
								if(shape != currentShape) {
									// check if we got an intersection
									if(shape.rayIntersect(ray2) > 0) {
										isShadowed = true;
										break;
									}
								}
							}
							// in case we are not shadowed -> calculate new light
							if(!isShadowed) {
								// TODO: improve
								// get diffused light component
								diffLight += getDiffuseComponent(source, normal, intersection, currentShape);
							}
						}
					}
					// TODO improve
					// get ambient light component
					float ambientLight = getAmbientComponent(currentShape);
					
					// add lights
					int[] light = new int[4];
					light[0] = currentShape.getMaterial().getColor().getRed();
					light[1] = currentShape.getMaterial().getColor().getGreen();
					light[2] = currentShape.getMaterial().getColor().getBlue();
					light[3] = currentShape.getMaterial().getColor().getAlpha();
					
					for(int i=0; i<3;i++) {
						// TODO improve
						light[i] = (int)(light[i] - light[i]*(ambientLight+diffLight));
						if(light[i] > 255) {
							light[i] = 255;
						}
						if(light[i] < 0) {
							light[i] = 0;
						}
					}
					// set light in memory
					memory[x+y*width] = new Color(light[0], light[1], 
									light[2], light[3]).getRGB();
				} else {
					// no intersection -> set background color in memory
					memory[x+y*width] = bgColor.getRGB();
				}
			}
		}
		System.out.println("Finished Raytracing!!!");
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// raytrace
		System.out.println("START RAYTRACING");
		raytraceImage(widthPix, heightPix);
		// display map
		Image img = createImage(new MemoryImageSource(widthPix, heightPix, memory, 0, widthPix));
		g2.drawImage(img, 0, 0, null);
	}
	
	public void display() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(widthPix, heightPix);
		frame.getContentPane().add(this);
		frame.setVisible(true);
	}
}