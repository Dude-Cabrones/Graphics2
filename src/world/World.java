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
	private double zViewPlane;
	private double leftViewPlane;
	private double rightViewPlane;
	private double bottomViewPlane;
	private double topViewPlane;
	
	// objects in our world
	private LinkedList<MyShape> objects;
	
	// light sources in our world
	private LinkedList<LightSource> lights;
	
	// coefficients
	//private static final double diffuseC = 0.5;
	//private static final double ambientC = 0.1;
	
	// pixel memory map
	int[] memory;
	
	public World(MyPoint3D viewer, double zViewPlane, double leftViewPlane, 
			double rightViewPlane, double bottomViewPlane, double topViewPlane, 
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
		
		double xPart, yPart;
		xPart = (rightViewPlane - leftViewPlane)/widthPix;
		yPart = (topViewPlane - bottomViewPlane)/heightPix;

		xPart = leftViewPlane + 0.5*xPart + ((double)x)*xPart;
		yPart = bottomViewPlane + 0.5*yPart + ((double)y)*yPart;
		
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
	
	private void raytraceImage(int width, int height) {
		// shoot ray through each pixel
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				MyRay ray = shootPixelRay(x, y);
				double maxDist = Double.POSITIVE_INFINITY;
				
				// check each object for intersections 
				// --> get the one with shortest distance
				MyShape currentShape = null;
				for(MyShape shape : objects) {
					double hitDist = shape.rayIntersect(ray);
					if(hitDist > zViewPlane - viewer.getZ() && maxDist > hitDist) {
						maxDist = hitDist;
						currentShape = shape;
					}
				}
				
				// set pixel in buffer
				if(maxDist < Double.POSITIVE_INFINITY) {
					// we got an intersection
					// -> check for light source
					// shoot from intersection point in points surface normal direction
					MyPoint3D intersection = ray.getOrigin().add(ray.getDirection().mul(maxDist));
					// check if normal points towards any light source
					int curLight[] = new int[4];
					curLight[0] = currentShape.getMaterial().getColor().getRed();
					curLight[1] = currentShape.getMaterial().getColor().getGreen();
					curLight[2] = currentShape.getMaterial().getColor().getBlue();
					curLight[3] = currentShape.getMaterial().getColor().getAlpha();
					
					int diffLight[] = new int[4];
					for(int i=0; i<4; i++) {
						diffLight[i] = curLight[i];
					}
					boolean hasLightSource = false;
					for(LightSource source : lights) {
						if(source.intersects(currentShape.getNormal(intersection))) {
							// now we shoot a ray from the intersection point and see 
							// if any other object is in our way --> shadowed?
							MyRay ray2 = shootRay(intersection, currentShape.getNormal(intersection));
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
								// add light from light source
								hasLightSource = true;
								double dotProd = -source.getDirection().dotProduct(currentShape.getNormal(intersection));
								for(int i=0; i<4; i++) {
									diffLight[i] *= dotProd*source.getBrightness();
									if(diffLight[i] > 255) {
										diffLight[i] = 255;
									}
									if(diffLight[i] < 0) {
										diffLight[i] = 0;
									}
								}
							}
						}
					}
					// add gathered diffused light
					// TODO improve
					// finally add ambient light
					int[] ambient = new int[4];
					for(int i=0; i<4; i++) {
						ambient[i] += curLight[i]*currentShape.getMaterial().getAmbientC();
						if(ambient[i] > 255) {
							ambient[i] = 255;
						}
					}
					// add lights
					int[] light = new int[4];
					for(int i=0; i<4;i++) {
						// TODO improve
						if(hasLightSource) {
							light[i] = diffLight[i] + ambient[i];
						} else {
							light[i] = ambient[i];
						}
						if(light[i] > 255) {
							light[i] = 255;
						}
					}
					memory[x+y*width] = new Color(light[0], light[1], 
									light[2], light[3]).getRGB();
				} else {
					// no intersection -> use background color
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