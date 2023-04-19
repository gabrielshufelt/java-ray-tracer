package raytracer;

public class Vector {
	public double x, y, z;
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Vector add(Vector v) {return new Vector(x+v.x, y+v.y, z+v.z);}
	public Vector sub(Vector v) {return new Vector(x-v.x, y-v.y, z-v.z);}
	public Vector mult(double d) {return new Vector(x*d, y*d, z*d);}
	public Vector div(double d) {return new Vector(x/d, y/d, z/d);}
	public Vector normalize() {
		double m = Math.sqrt(x*x+y*y+z*z);
		return new Vector(x/m, y/m, z/m);
	}
	public double dot(Vector v) {return (x*v.x+y*v.y+z*v.z);}
}