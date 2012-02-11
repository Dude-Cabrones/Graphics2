package main;

import impl.LightSource;
import impl.MyColor;
import impl.MyMaterial;
import impl.MyPoint3D;

import java.awt.Color;
import java.util.LinkedList;

import shapes.MyPolygon;
import shapes.MySphere;
import world.World;

public class Main {

	public static void main(String[] args) {
		
		// set up viewer and view plane
		MyPoint3D viewer = new MyPoint3D(0, 0, -10);
		World world = new World(viewer, 1, -400, 400, -400, 400, 800, 600);
	
		// add light source
		LightSource sourceA = new LightSource();
		sourceA.setDirection(new MyPoint3D(1,1,0));
		sourceA.setOrigin(new MyPoint3D(-100, 0, 0));
		sourceA.setBrightness(1.0f);
		sourceA.setColor(new MyColor(0,255,255));
		world.addLight(sourceA);
		
		// add light source
		LightSource sourceB = new LightSource();
		sourceB.setDirection(new MyPoint3D(0,1,0));
		sourceB.setOrigin(new MyPoint3D(100, 100, 30));
		sourceB.setBrightness(0.5f);
		sourceB.setColor(new MyColor(255,255,255));
		world.addLight(sourceB);
		
		// add light source
		LightSource sourceC = new LightSource();
		sourceC.setDirection(new MyPoint3D(0,0,1));
		sourceC.setOrigin(new MyPoint3D(0, 100, 20));
		sourceC.setBrightness(1.0f);
		sourceC.setColor(new MyColor(255,255,255));
		//world.addLight(sourceC);
		
		// set up quadratic test shape
		//MyPoint3D ta = new MyPoint3D(12, 384, 600);
		//MyPoint3D tb = new MyPoint3D(530, -384, 600);
		//MyPoint3D tc = new MyPoint3D(188, -100, 400);
		//MyPoint3D td = new MyPoint3D(-512, 384, 400);
		//world.addShape(new MyQuadSurface(ta,tb,tc,td,100,100,100));
	
		// create materials
		MyMaterial matA = new MyMaterial();
		matA.setAmbientC(0.1f);
		matA.setDiffuseC(0.7f);
		matA.setSpecularC(0.4f);
		matA.setReflectionProperty(32);
		matA.setColor( new MyColor(Color.red));
		
		MyMaterial matB = new MyMaterial();
		matB.setAmbientC(0.1f);
		matB.setDiffuseC(0.4f);
		matB.setSpecularC(0.4f);
		matB.setReflectionProperty(32);
		matB.setColor( new MyColor(Color.blue));
		
		MyMaterial matC = new MyMaterial();
		matC.setAmbientC(0.1f);
		matC.setDiffuseC(0.4f);
		matC.setSpecularC(1f);
		matC.setReflectionProperty(32);
		matC.setColor( new MyColor(Color.YELLOW) );
		
		MyMaterial matD = new MyMaterial();
		matD.setAmbientC(0.1f);
		matD.setDiffuseC(0.4f);
		matD.setSpecularC(1f);
		matD.setReflectionProperty(32);
		matD.setColor(new MyColor(Color.GREEN));
		
		// set up polygon shape
		MyPoint3D ta = new MyPoint3D(50, 50, 50);
		MyPoint3D tb = new MyPoint3D(-50, 50, 50);
		MyPoint3D tc = new MyPoint3D(0, 0, 50);
		LinkedList<MyPoint3D> keyPoints = new LinkedList<MyPoint3D>();
		keyPoints.add(ta); keyPoints.add(tb); keyPoints.add(tc);
		//world.addShape(new MyPolygon(keyPoints, matD));

		
		// add test sphere
		MyPoint3D center = new MyPoint3D(80, 20, 30);
		MySphere sphereA = new MySphere(center, 25, matA);
		world.addShape(sphereA);
		
		// add test sphere
		world.addShape(new MySphere(new MyPoint3D(-100, -100, 50), 40, matB));
		
		// add test sphere
		world.addShape(new MySphere(new MyPoint3D(0, 0, 200), 100, matC));
		
		// render test shapes
		world.display();
	}
}
