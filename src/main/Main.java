package main;

import impl.LightSource;
import impl.MyMaterial;
import impl.MyPoint3D;

import java.awt.Color;

import shapes.MySphere;
import world.World;

public class Main {

	public static void main(String[] args) {
		
		// set up viewer and view plane
		MyPoint3D viewer = new MyPoint3D(0, 0, -10);
		World world = new World(viewer, 1, -200, 200, -200, 200, 300, 300, Color.gray);
	
		// add light source
		LightSource sourceA = new LightSource();
		sourceA.setDirection(new MyPoint3D(1,0,0));
		sourceA.setBrightness(1.0);
		world.addLight(sourceA);
		
		// add light source
		LightSource sourceB = new LightSource();
		sourceB.setDirection(new MyPoint3D(0,1,0));
		sourceB.setBrightness(0.5);
		world.addLight(sourceB);
		
		// add light source
		LightSource sourceC = new LightSource();
		sourceC.setDirection(new MyPoint3D(0,0,1));
		sourceC.setBrightness(1.0);
		//world.addLight(sourceC);
		
		// set up quadratic test shape
		//MyPoint3D ta = new MyPoint3D(12, 384, 600);
		//MyPoint3D tb = new MyPoint3D(530, -384, 600);
		//MyPoint3D tc = new MyPoint3D(188, -100, 400);
		//MyPoint3D td = new MyPoint3D(-512, 384, 400);
		//world.addShape(new MyQuadSurface(ta,tb,tc,td,100,100,100));
	
		// create materials
		MyMaterial matA = new MyMaterial();
		matA.setAmbientC(0.2);
		matA.setDiffuseC(10.0);
		matA.setColor(Color.red);
		
		MyMaterial matB = new MyMaterial();
		matB.setAmbientC(0.4);
		matB.setDiffuseC(10.9);
		matB.setColor(Color.blue);
		
		MyMaterial matC = new MyMaterial();
		matC.setAmbientC(0.6);
		matC.setDiffuseC(10.0);
		matC.setColor(Color.yellow);
		
		// add test sphere
		MyPoint3D center = new MyPoint3D(100, 20, 40);
		MySphere sphereA = new MySphere(center, 30, matA);
		world.addShape(sphereA);
		
		// add test sphere
		world.addShape(new MySphere(new MyPoint3D(-100, -100, 50), 40, matB));
		
		// add test sphere
		world.addShape(new MySphere(new MyPoint3D(0, 0, 30), 29, matC));
		
		// render test shapes
		world.display();
	}
}
