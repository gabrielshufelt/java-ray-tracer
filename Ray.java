package raytracer;

public class Ray {
	public Vector o, d;
	public Ray(Vector o, Vector d) {this.o = o;	this.d = d;}
	public Ray add(Ray r) {return new Ray(o.add(r.o), d.add(r.d));}
	public Ray sub(Ray r) {return new Ray(o.sub(r.o), d.sub(r.d));}
	public Ray mult(double k) {return new Ray(o.mult(k), d.mult(k));}
	public Ray div(double k) {return new Ray(o.div(k), d.div(k));}
}
