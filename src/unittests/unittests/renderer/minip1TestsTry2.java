package unittests.unittests.renderer;

import static java.awt.Color.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import geometries.Geometry;
import geometries.Polygon;
import geometries.Sphere;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import primitives.Double3;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTraceBasic;
import scene.Scene;

class minip1TestsTry2 {

	 private primitives.Color spCL = new primitives.Color(800, 500, 0); // Sphere test Color of Light
	    private Point spPL = new Point(-50, -50, 25); // Sphere test Position of Light
	    private Point spPL2 = new Point(50, 50, 25); // Sphere test Position of Light


	    private Scene scene1 = new Scene("Test scene") //
	            .setAmbientLight(new AmbientLight(primitives.Color.RED, new Double3(0.15)));

	    private Camera camera1 = new Camera(new Point(-100, 100, 80), new Vector(5,-5,-4), new Vector(4,-4,10)) //
	            .setVPSize(150, 150) //
	            .setVUPDistance(140);

	    private Geometry sphere1 = new Sphere( 10d,new Point(0,0,0)) //
	            .setEmission(primitives.Color.PINK.reduce(2)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    private Geometry sphere2 = new Sphere(10d,new Point(20,0,0)) //
	            .setEmission(primitives.Color.BLUE.reduce(2)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    private Geometry sphere3 = new Sphere(10d,new Point(40,0,0)) //
	            .setEmission(primitives.Color.PINK.reduce(2)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    private Geometry sphere4 = new Sphere(10d,new Point(60,0,0)) //
	            .setEmission(primitives.Color.BLUE.reduce(2)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    private Geometry sphere5 = new Sphere(10d,new Point(80,0,0)) //
	            .setEmission(primitives.Color.PINK.reduce(2)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    private Geometry sphere6 = new Sphere(10d,new Point(0,-20,0)) //
	            .setEmission(primitives.Color.BLUE.reduce(2)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    private Geometry sphere7 = new Sphere(10d,new Point(0,-40,0)) //
	            .setEmission(primitives.Color.PINK.reduce(2)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    private Geometry sphere8 = new Sphere(10d,new Point(0,-60,0)) //
	            .setEmission(primitives.Color.BLUE.reduce(2)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    private Geometry sphere9 = new Sphere(10d,new Point(0,-80,0)) //
	            .setEmission(primitives.Color.PINK.reduce(2)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    private Geometry sphere10 = new Sphere(5d,new Point(0,0,11)) //
	            .setEmission(primitives.Color.BLUE.reduce(2)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    private Geometry sphere11 = new Sphere(5d,new Point(20,0,10.15)) //
	            .setEmission(primitives.Color.PINK.reduce(2)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    private Geometry sphere12 = new Sphere(5d,new Point(40,0,10.15)) //
	            .setEmission(primitives.Color.BLUE.reduce(2)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    private Geometry sphere13 = new Sphere(5d,new Point(60,0,10.15)) //
	            .setEmission(primitives.Color.PINK.reduce(1.5)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    private Geometry sphere14 = new Sphere( 5d,new Point(80,0,10.15)) //
	            .setEmission(primitives.Color.BLUE.reduce(1.5)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    private Geometry sphere15 = new Sphere(5d,new Point(0,-20,10.15)) //
	            .setEmission(primitives.Color.PINK.reduce(1.5)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    private Geometry sphere16 = new Sphere(5d,new Point(0,-40,10.15)) //
	            .setEmission(primitives.Color.BLUE.reduce(1.5)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    private Geometry sphere17 = new Sphere( 5d,new Point(0,-60,10.15)) //
	            .setEmission(primitives.Color.PINK.reduce(1.5)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    private Geometry sphere18 = new Sphere(5d,new Point(0,-80,10.15)) //
	            .setEmission(primitives.Color.BLUE.reduce(1.5)) //
	            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300));
	    @Test
	    public void mp1() {
	        Vector trDL1 = new Vector(-13,-7,-10);

	        Point floor1 = new Point(-50,50,-10);
	        Point floor2 = new Point(-50,-110 ,-10);
	        Point floor3 = new Point(110,-110,-10);
	        Point floor4 = new Point(110,50,-10);
	        Geometry floor = new Polygon(floor1 ,floor2 ,floor3 ,floor4)
	                .setEmission(primitives.Color.BLUE.reduce(2)) 
	                .setMaterial(new Material().setkD(0.5).setkS(0.9).setShininess(300));
	        scene1.geometries.add(floor);

	        scene1.geometries.add(floor, sphere1, sphere2, sphere3, sphere4, sphere5, sphere6, sphere7, sphere8,
	                sphere9, sphere10,sphere11, sphere12, sphere13, sphere14, sphere15, sphere16, sphere17, sphere18);
	        primitives.Color c1=new primitives.Color(200,50,80);
	        scene1.lights.add(new DirectionalLight(c1, trDL1));
	        scene1.lights.add(new PointLight(spCL, spPL).setkL(0.001).setkq(0.0002));
	        scene1.lights.add(new PointLight(spCL, spPL2).setkL(0.001).setkq(0.0002));

	        ImageWriter imageWriter = new ImageWriter("improved picture 2", 1000, 1000);
	       
	        
	       camera1.setImageWriter(imageWriter)
	                .setRayTracer(new RayTraceBasic(scene1))
	                .setNumOfRays(1024)
	                .setAntialising(true).setAdaptiveSuperSamplingFlag(true).setMultithreading(4).
	                renderImage()
	                .writeToImage();
	    }

}
