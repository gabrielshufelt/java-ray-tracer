# java-ray-tracer
# developed by Gabriel Shufelt

This program simulates a scene with a sphere in the middle, lit by global illumination and a point light source.
Although not very complex, it is an effective ray tracer, that is easy to build on. At its core, it uses Lambertian 
reflection to simulate shading, as opposed to the traditional lighting equation in all modern ray tracers. Implementing 
the lighting equation would require a lot more work and would not be possible to write under 100 lines of code, which 
was the goal of this project. This file gives a short explanation on the math behind the ray tracing and shading algorithms:

# Algorithm
The program starts by creating an image grid 500 x 500 pixels in size. Then, two spheres are added in the scene: one
that will be rendered, and one that is used for global illumination. Colors is then given to both these spheres. After,
a ray is sent out each pixel of the image grid, and checked for an intersection with the sphere in the scene. If there is 
a intersection point, it is found and used to calculate the reflected light ray's direction, which is finally used to 
calculate the diffuse lighting at that pixel (or shading). 

# Mathematical equations
To find the point of intersection between the light rays and the sphere object, the radius of the sphere is set equal to 
the magnitude of the ray:
  
  || ray || = r
  
Because a ray is simply a line, it can be rewritten in its parametric form:

  || p + dt || = r,
    where p is the origin point of the ray, 
    where d is the direction,
    where t is a scalar indicating the distance to that point.
    
Developing the equation above produces a quadratic equation in t:
  
  t^2 || d^2 || + 2t(p*d) + || p^2 || - r^2 = 0
    with coefficients
    a = || d^2 || = 1
    b = 2(p*d)
    c = || p^2 || - r^2
  
Rearranging, omitting || d^2 || (as its length is 1) and isolating for t:
  
  t = -p*d +/- sqrt{(p*d)^2 - (|| p^2 || - r^2 )}
  
t can produce 0, 1, or 2 solutions. if t produces 0 solutions, then the sphere and light do not intersect. If t renders
1 solution, then the ray intersects with the sphere at a single point (on the edge of the sphere). Finally, if t gives
2 solutions, then the smallest value is chosen, as it is the closest one and the one of interest. t is then used to find
the direction vector of the reflected light. This vector is then used to find the lambertian reflection, using the eqn. : 

  I = C x (L*N) x Is, 
    where I is the intensity of the diffusely reflected light, 
    where L is the normalized light direction vector, 
    dotted with N the surface's normal vector,
    and where Is is the intensity of the light.
