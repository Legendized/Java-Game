package com.Game.main;

public final class Utilities {
	public static char toChar(int c) {
	    return (char)c;
	}
	
	public static float Lerp(float a, float b, float slide) {
		return a + slide * (b - a);
	}
	
	public static double Lerp(double a, double b, double slide) {
		return a + slide * (b - a);
	}
	
	public static float Squared(float a) {
		return (float) Math.pow(a, 2);
	}
	
	public static double Squared(double d) {
		return Math.pow(d, 2);
	}
	
	public static double GetMagnitude(double x, double y) {
		return Math.sqrt((x*x)+(y*y));
	}
	
	public static float GetDistanceSquared2D(float ax, float ay, float bx, float by) {
		return (float) Math.sqrt(Squared(ax - bx)+Squared(ay - by));
		
	}

	public static double GetDistanceSquared2D(double x, double y, double x2, double y2) {
		// TODO Auto-generated method stub
		return Math.sqrt(Squared(x - x2)+Squared(y - y2));
	}
}
