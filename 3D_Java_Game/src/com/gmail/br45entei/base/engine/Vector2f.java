/**
 * 
 */
package com.gmail.br45entei.base.engine;

/**
 * @author Brian_Entei
 *
 */
public class Vector2f {
	
	private float x;
	private float y;
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
	public float length() {
		return (float) Math.sqrt((this.x * this.x) + (this.y * this.y));
	}
	
	public float dot(Vector2f r) {
		return ((this.x * r.getX()) + this.y * r.getY());
	}
	
	public Vector2f normalize() {
		float length = this.length();
		
		x /= length;
		y /= length;
		
		return this;
	}
	
	public Vector2f rotate(float angle) {
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);
		
		return new Vector2f((float) ((this.x * cos) - (y * sin)), (float) ((this.x * sin) + (this.y * cos)));
	}
	
	public Vector2f add(Vector2f r) {
		return new Vector2f(this.x + r.getX(), this.y + r.getY());
	}
	
	public Vector2f add(float r) {
		return new Vector2f(this.x + r, this.y + r);
	}
	
	public Vector2f sub(Vector2f r) {
		return new Vector2f(this.x - r.getX(), this.y - r.getY());
	}
	
	public Vector2f sub(float r) {
		return new Vector2f(this.x - r, this.y - r);
	}
	
	public Vector2f mult(Vector2f r) {
		return new Vector2f(this.x * r.getX(), this.y * r.getY());
	}
	
	public Vector2f mult(float r) {
		return new Vector2f(this.x * r, this.y * r);
	}
	
	public Vector2f div(Vector2f r) {
		return new Vector2f(this.x / r.getX(), this.y / r.getY());
	}
	
	public Vector2f div(float r) {
		return new Vector2f(this.x / r, this.y / r);
	}

	public Vector2f abs() {
		return new Vector2f(Math.abs(x), Math.abs(y));
	}

	/**
	 * @return the x
	 */
	public float getX() {
		return this.x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return this.y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}

}
