package raytracer;
import java.io.*;

public class Driver {
	public static void main(String args[]) {
		int h = 500;
		int w = 500;
		
		Vector white = new Vector(255, 255, 255);
		Vector bg = new Vector(16, 16, 16);
		Vector green = new Vector(34, 139, 34);
		
		Sphere s1 = new Sphere(new Vector(w*0.5, h*0.5, 50), 150);
		Sphere light = new Sphere(new Vector(1000, -1000, 0), 100);
		
		File output = new File("out.ppm");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(output));
			bw.write("P3\n"+w+" "+h+" 255\n");
			double t;
			Vector pixCol = bg;
			for (int y=0; y<h; ++y) {
				for (int x=0; x<w; ++x) {
					pixCol = bg;
					Ray ray = new Ray(new Vector(x,y,0), new Vector(0,0,1));
					if (s1.intersects(ray)) {
						t = s1.intersection(ray);
						Vector sln = ray.o.add(ray.d.mult(t));
						Vector l = light.c.sub(sln);
						Vector n = s1.getNormal(sln);
						
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
	public static void clamp(Vector color) {
		if (color.x > 255) {color.x = 255;}
		else if (color.x < 0) {color.x = 0;}
		if (color.y > 255) {color.y = 255;}
		else if (color.y < 0) {color.y = 0;}
		if (color.z > 255) {color.z = 255;}
		else if (color.z < 0) {color.z = 0;}
	}
}
