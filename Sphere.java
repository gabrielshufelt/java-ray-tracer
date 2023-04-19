package raytracer;

public class Sphere {
	public Vector c;
	public double r;
	public Sphere(Vector c, double r) {this.c = c;this.r = r;}
	public Vector getNormal(Vector p) {return (p.sub(c)).div(r);}
	public boolean intersects(Ray ray) {
		Vector p = ray.o.sub(c);
		double b = 2*p.dot(ray.d);
		double c = p.dot(p)-(r*r);
		double disc = b*b - 4*c;
		if (disc < 0.0001) {return false;}
		return true;		
	}
	public double intersection(Ray ray) {
		Vector p = ray.o.sub(c);
		double b = 2*p.dot(ray.d);
		double c = p.dot(p)-(r*r);
		double disc = Math.sqrt(b*b - 4*c);
		double t0 = -b-disc;
		double t1 = -b+disc;
		if (t0 < t1) {return t0;}
		else {return t1;}
	}
}