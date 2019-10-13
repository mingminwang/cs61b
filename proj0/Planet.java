/** This is the Planet class*/

import java.lang.Math; // importing java.lang package 

public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	
	/** First constructor to initialize Planet using provided values*/
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
              
    /** Constructor to make a copy of Planet object just defined above*/          
    public Planet(Planet p) {
    
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
    
    }
    
    /** CalcDistance method to calculate the distance between two planets*/
    public double calcDistance(Planet p) {
    	double r = Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) +
    		(this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
    	return r;
    }
    
    /** The calcForceExertedBy method takes in a planet, and returns a 
    *   double describing the force exerted on this planet by the given 
    *   planet.*/
    public double calcForceExertedBy (Planet p) {
    	
    	double G = 6.67E-11; // Gravitational constant
    	double Force = G * p.mass * this.mass / (this.calcDistance(p) * 
    		this.calcDistance(p));
    	return Force;
    }
    
    /** Calculate force resolved on X direction*/
    public double calcForceExertedByX (Planet p) {
    	double ForceX = this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) /
    	this.calcDistance(p);
    	return ForceX;
    }
    
    /** Calculate force resolved on Y direction*/
    public double calcForceExertedByY (Planet p) {
    	double ForceY = this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) /
    	this.calcDistance(p);
    	return ForceY;
    }
    
    /** net force from all planets along X direction */
    public double calcNetForceExertedByX(Planet[] AllPlanets) {
    	double netForceX = 0;
    	for (int i = 0; i < AllPlanets.length; i++) {
    		if (! (this.equals(AllPlanets[i]))){
    			netForceX = netForceX + this.calcForceExertedByX(AllPlanets[i]);
    		}
    	}
    	return netForceX;
    }
    
        /** net force from all planets along Y direction */
    public double calcNetForceExertedByY(Planet[] AllPlanets) {
    	double netForceY = 0;
    	for (int i = 0; i < AllPlanets.length; i++) {
    		if (! (this.equals(AllPlanets[i]))){
    			netForceY = netForceY + this.calcForceExertedByY(AllPlanets[i]);
    		}
    	}
    	return netForceY;
    }
    
    /** Update position and velocity*/
    public void update(double dt, double fX, double fY) {
    	double aX = fX / mass;
    	double aY = fY / mass;
    	xxVel = xxVel + aX * dt;
    	yyVel = yyVel + aY * dt;
    	xxPos = xxPos + xxVel * dt;
    	yyPos = yyPos + yyVel * dt;
    }
    
    /* draw method**/
    public void draw() {
    	String imageToDraw = "./images/" + imgFileName;
    	StdDraw.picture(xxPos, yyPos, imageToDraw);
    }
    
}