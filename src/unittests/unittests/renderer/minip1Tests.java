package unittests.unittests.renderer;
import static java.awt.Color.RED;

import java.util.ArrayList;
import java.util.List;
import static java.awt.Color.WHITE;

import org.junit.jupiter.api.Test;

import geometries.Geometry;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import primitives.Color;
import primitives.Double3;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTraceBasic;
import scene.Scene;
class minip1Tests {

	private Scene scene = new Scene("Test scene");


	
	
	/** Produce a picture of a sphere lighted by a spot light */
	/*   @Test
	   public void twoSpheresOnMirrors() {
	      Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
	         .setVPSize(2500, 2500).setVUPDistance(10000); //

	      scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

	      scene.geometries.add( //
	                           new Sphere(400d,new Point(-950, -900, -1000)).setEmission(new Color(0, 50, 100)) //
	                              .setMaterial(new Material().setkD(0.25).setkS(0.25).setShininess(20)
	                                 .setkT(new Double3(0.5, 0, 0))),
	                           new Sphere(200d,new Point(-950, -900, -1000)).setEmission(new Color(100, 50, 20)) //
	                              .setMaterial(new Material().setkD(0.25).setkS(0.25).setShininess(20)),
	                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
	                                        new Point(670, 670, 3000)) //
	                              .setEmission(new Color(20, 20, 20)) //
	                              .setMaterial(new Material().setkR(1d)),
	                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
	                                        new Point(-1500, -1500, -2000)) //
	                              .setEmission(new Color(20, 20, 20)) //
	                              .setMaterial(new Material().setkR(new Double3(0.5, 0, 0.4))));

	      scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
	         .setkL(0.00001).setkq(0.000005));

	      ImageWriter imageWriter = new ImageWriter("before improvment", 500, 500);
	      camera.setImageWriter(imageWriter) //
	         .setRayTracer(new RayTraceBasic(scene)) //
	         .renderImage() //
	         .writeToImage();
	   }
	
	
	   /** Produce a picture of a sphere lighted by a spot light */
	  // @Test
	   /*public void twoSpheresOnMirrorsAntializsing() {
	      Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
	         .setVPSize(2500, 2500).setVUPDistance(10000); //

	      scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

	      scene.geometries.add( //
	                           new Sphere(400d,new Point(-950, -900, -1000)).setEmission(new Color(0, 50, 100)) //
	                              .setMaterial(new Material().setkD(0.25).setkS(0.25).setShininess(20)
	                                 .setkT(new Double3(0.5, 0, 0))),
	                           new Sphere(200d,new Point(-950, -900, -1000)).setEmission(new Color(100, 50, 20)) //
	                              .setMaterial(new Material().setkD(0.25).setkS(0.25).setShininess(20)),
	                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
	                                        new Point(670, 670, 3000)) //
	                              .setEmission(new Color(20, 20, 20)) //
	                              .setMaterial(new Material().setkR(1d)),
	                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
	                                        new Point(-1500, -1500, -2000)) //
	                              .setEmission(new Color(20, 20, 20)) //
	                              .setMaterial(new Material().setkR(new Double3(0.5, 0, 0.4))));

	      scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
	         .setkL(0.00001).setkq(0.000005));
	      camera.setAntialising(true).setnumOfRaysSuperSampeling(400);
	      ImageWriter imageWriter = new ImageWriter("improved-antialising", 500, 500);
	      camera.setImageWriter(imageWriter) //
	         .setRayTracer(new RayTraceBasic(scene)) //
	         .renderImage() //
	         .writeToImage();
	   }*/
	   
	   
	   @Test
	   public void fullImprovedPicture()
	   {
		   Geometry front=new Polygon(new Point(2, 2, 0),
				    new Point(2, 3, 0),
				    new Point(6, 2, 3),
				    new Point(6, 2, 0),
				    new Point(4.5, 2, 0),
				    new Point(4.5, 2, 1.25),
				    new Point(3.5, 2, 1.25),
				    new Point(3.5, 2, 0));
		   
		   Geometry wall1=new Polygon(new Point(6,2,3),new Point(6,4,3),new Point(6,4,0),new Point(6,2,0));
		   Geometry backWall=new Polygon(new Point(2,4,3),new Point(2,4,0),new Point(6,4,0),new Point(6,4,3));
		   Geometry wall2=new Polygon(new Point(2,2,0),new Point(2,4,0),new Point(2,4,3),new Point(2,2,3));
		    Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			         .setVPSize(2500, 2500).setVUPDistance(10000); //
		      scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
		      scene.geometries.add(/*triangles for the roof*/
		    		  new Triangle(new Point(4,3,4),new Point(2,2,3),new Point(6,2,3)).setEmission(new Color(255,0,0))
                      .setMaterial(new Material().setkD(0.25).setkS(0.25).setShininess(20)
                              .setkT(new Double3(0.5, 0, 0))),
                      new Triangle(new Point(0,5,0), new Point(8,0,0), new Point(8,5,0)).setEmission(new Color(255,0,0))
                      .setMaterial(new Material().setkD(0.25).setkS(0.25).setShininess(20)
                              .setkT(new Double3(0.5, 0, 0))),
                      new Triangle(new Point(4,3,4), new Point(6,4,3), new Point(2,2,3)).setEmission(new Color(255,0,0))
                      .setMaterial(new Material().setkD(0.25).setkS(0.25).setShininess(20)
                              .setkT(new Double3(0.5, 0, 0))),
                      new Triangle(new Point(4,3,4), new Point(2,4,3), new Point(2,2,3)).setEmission(new Color(255,0,0))
                      .setMaterial(new Material().setkD(0.25).setkS(0.25).setShininess(20)
                              .setkT(new Double3(0.5, 0, 0))),
                      /*for the walls*/
                      front.setEmission(new Color(204,204,204)) .setMaterial(new Material().setkD(0.25).setkS(0.25).setShininess(20)
                              .setkT(new Double3(0.5, 0, 0))),
                      wall1.setEmission(new Color(204,204,204)).setMaterial(new Material().setkD(0.25).setkS(0.25).setShininess(20)
                              .setkT(new Double3(0.5, 0, 0))),
                      backWall.setEmission(new Color(204,204,204)).setMaterial(new Material().setkD(0.25).setkS(0.25).setShininess(20)
                              .setkT(new Double3(0.5, 0, 0))),
		    		  wall2.setEmission(new Color(204,204,204)).setMaterial(new Material().setkD(0.25).setkS(0.25).setShininess(20)
                              .setkT(new Double3(0.5, 0, 0)))
		    		 
		    		  
		    		  );
		    		 
                      
		    		  
		    
		      scene.lights.add(new DirectionalLight(new Color(RED), new Vector(1, 1, -0.5)));
			   scene.lights.add(new PointLight(new Color(WHITE), new Point(-750, -750, -150))
			         .setkL(0.001).setkq(0.0002));
			   scene.lights.add(new SpotLight(new Color(RED), new Point(-70, -70, -50), new Vector(1, 1.1, 1))
			         .setkL(0.001).setkq(0.0001));
               camera.setAntialising(true).setnumOfRaysSuperSampeling(40);
               ImageWriter imageWriter = new ImageWriter("full improved picture", 500, 500);
               camera.setImageWriter(imageWriter) //
                 	         .setRayTracer(new RayTraceBasic(scene)) //
                 	         .renderImage() //
                 	         .writeToImage();
                      
                      
	   }
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
}
