package raytracer;
import java.io.*;

public class Driver {
	public static void main(String args[]) {
		// create a 500 x 500 image
		int h = 500;
		int w = 500;
		
		// define colors for reflection (white), the background (bg), and the color of the sphere (green)
		Vector white = new Vector(255, 255, 255);
		Vector bg = new Vector(16, 16, 16);
		Vector green = new Vector(34, 139, 34);
		
		// create 2 spheres, one that will be rendered, and one that will act as global illumination
		Sphere s1 = new Sphere(new Vector(w*0.5, h*0.5, 50), 150);
		Sphere light = new Sphere(new Vector(1000, -1000, 0), 100);
		
		File output = new File("out.ppm");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(output));
			// ppm header
			bw.write("P3\n"+w+" "+h+" 255\n");
			// variable for intersection 
			double t;
			Vector pixCol = bg;
			for (int y=0; y<h; ++y) {
				for (int x=0; x<w; ++x) {
					pixCol = bg;
					// a ray is sent from the current camera's pixel, out onto the scene
					// if the ray intersects, determine the point of intersection
					// and the direction of the reflected ray
					Ray ray = new Ray(new Vector(x,y,0), new Vector(0,0,1));
					if (s1.intersects(ray)) {
						t = s1.intersection(ray);
						Vector sln = ray.o.add(ray.d.mult(t));
						Vector l = light.c.sub(sln);
						Vector n = s1.getNormal(sln);
						
						// dt is the amount of shading produced, determined
						// by Lambertian reflection equation
						double dt = l.normalize().dot(n.normalize());
						pixCol = (white.mult(dt).add(green)).mult(0.5);
						clamp(pixCol);
					}
					bw.write((int)pixCol.x+" ");
					bw.write((int)pixCol.y+" ");
					bw.write((int)pixCol.z+"\n");
				}
			}
			bw.flush();
			bw.close();
		}
		catch (IOException e) {e.printStackTrace();}
		System.out.println("Succesfully wrote to output.ppm!");
	}
	// clamp function sets boundaries for colors, assuring that RGB values don't go over 255
	public static void clamp(Vector color) {
		if (color.x > 255) {color.x = 255;}
		else if (color.x < 0) {color.x = 0;}
		if (color.y > 255) {color.y = 255;}
		else if (color.y < 0) {color.y = 0;}
		if (color.z > 255) {color.z = 255;}
		else if (color.z < 0) {color.z = 0;}
	}
}
