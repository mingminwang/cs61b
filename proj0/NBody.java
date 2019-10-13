/** NBody is a class that will actually run your simulation. This class will have NO constructor. 
*   The goal of this class is to simulate a universe specified in one of the data files.*/

public class NBody {
	/* read the radius from txt file**/
	public static double readRadius (String path) {
		In in = new In(path);
		in.readDouble();
		double Radius = in.readDouble();
		return Radius;
	}

	/** Given a file name, it should return an array of Planets corresponding to the planets in the file*/
	public static Planet[] readPlanets(String path) {
		In in = new In(path);
		int N = in.readInt();
		in.readDouble();
		Planet[] p = new Planet[N];
		for (int i = 0; i < N; i++) {
			p[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),  
				in.readDouble(), in.readString());
		}
		return p;
	}
	
	/** Main method */
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double UniverseRadius = readRadius(filename);
		Planet[] ps = readPlanets(filename); 
		
		/** Sets up the universe scale */
		StdDraw.setScale(-UniverseRadius, UniverseRadius);
		
		/* Clears the drawing window. */
		StdDraw.clear();
		StdDraw.picture(0, 0, "./images/starfield.jpg");
		
		for(int i = 0; i < ps.length; i++) {
			ps[i].draw();
		}
		
		double t = 0;
		double[] xForces = new double[ps.length];
		double[] yForces = new double[ps.length];
		while(t <= T){
			for(int j = 0; j < ps.length; j++) {
				xForces[j] = ps[j].calcNetForceExertedByX(ps);
				yForces[j] = ps[j].calcNetForceExertedByY(ps);
				ps[j].update(dt, xForces[j], yForces[j]);
				StdDraw.picture(0, 0, "./images/starfield.jpg");
				for(int k = 0; k < ps.length; k++) {
					ps[k].draw();
				}
				StdDraw.show(10);
			}
			t = t + dt;
		}
		
		StdOut.printf("%d\n", ps.length);
		StdOut.printf("%.2e\n", UniverseRadius);
		for (int i = 0; i < ps.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   				ps[i].xxPos, ps[i].yyPos, ps[i].xxVel, ps[i].yyVel, ps[i].mass, ps[i].imgFileName);	
}	
	}
	
}