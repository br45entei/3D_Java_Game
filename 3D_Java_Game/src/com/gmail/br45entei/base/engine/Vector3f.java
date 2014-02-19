/**
 * 
 */
package com.gmail.br45entei.base.engine;

/**
 * @author Brian_Entei
 *
 */
public class Vector3f {
	
	private float x;
	private float y;
	private float z;
	
	Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}

	public Vector3f toDegrees() {
		return new Vector3f((float) Math.toDegrees(this.getX()), (float) Math.toDegrees(this.getY()), (float) Math.toDegrees(this.getZ()));
	}

	public Vector3f toRadians() {
		return new Vector3f((float) Math.toRadians(this.getX()), (float) Math.toRadians(this.getY()), (float) Math.toRadians(this.getZ()));
	}

	public float length() {
		return (float) Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
	}

	/**Calculates the magnitude, or distance, between this Vector3f and the given vector3f. Uses the following code:<br><br><em>
	 * double distance = Math.sqrt(<br>
	 * Math.pow(this.getX() - vector3fToCompare.getX(), 2) + <br>
	 * Math.pow(this.getY() - vector3fToCompare.getY(), 2) + <br>
	 * Math.pow(this.getZ() - vector3fToCompare.getZ(), 2)<br>
	 * );<br>
	 * return distance;</em>
	 * @param vector3fToCompare Vector3f
	 * @return
	 */
	public double getDistanceBetweenVector3f(Vector3f vector3fToCompare) {
		double distance = Math.sqrt(
				Math.pow(this.getX() - vector3fToCompare.getX(), 2) + 

				Math.pow(this.getY() - vector3fToCompare.getY(), 2) + 

				Math.pow(this.getZ() - vector3fToCompare.getZ(), 2)
		);
		return distance;
	}

	public float dot(Vector3f r) {
		return ((this.x * r.getX()) + (this.y * r.getY()) + (this.z * r.getZ()));
	}
	
	public Vector3f cross(Vector3f r) {
		float x_ = (this.y * r.getZ()) - (this.z * r.getY());
		float y_ = (this.z * r.getX()) - (this.x * r.getZ());
		float z_ = (this.x * r.getY()) - (this.y * r.getX());
		
		return new Vector3f(x_, y_, z_);
	}
	
	public Vector3f normalize() {
		float length = this.length();
		
		x /= length;
		y /= length;
		z /= length;
		
		return this;
	}

	public Vector3f rotate(float angle, Vector3f axis) {
		float sinHalfAngle = (float)Math.sin(Math.toRadians(angle / 2));
		float cosHalfAngle = (float)Math.cos(Math.toRadians(angle / 2));

		float rX = axis.getX() * sinHalfAngle;
		float rY = axis.getY() * sinHalfAngle;
		float rZ = axis.getZ() * sinHalfAngle;
		float rW = cosHalfAngle;

		Quaternion rotation = new Quaternion(rX, rY, rZ, rW);
		Quaternion conjugate = rotation.conjugate();

		Quaternion w = rotation.mult(this).mult(conjugate);

		return new Vector3f(w.getX(), w.getY(), w.getZ());
	}

	public Vector3f add(Vector3f r) {
		return new Vector3f(this.x + r.getX(), this.y + r.getY(), this.z + r.getZ());
	}
	
	public Vector3f add(float r) {
		return new Vector3f(this.x + r, this.y + r, this.z + r);
	}

	public Vector3f sub(Vector3f r) {
		return new Vector3f(this.x - r.getX(), this.y - r.getY(), this.z - r.getZ());
	}
	
	public Vector3f sub(float r) {
		return new Vector3f(this.x - r, this.y - r, this.z - r);
	}

	public Vector3f mult(Vector3f r) {
		return new Vector3f(this.x * r.getX(), this.y * r.getY(), this.z * r.getZ());
	}
	
	public Vector3f mult(float r) {
		return new Vector3f(this.x * r, this.y * r, this.z * r);
	}

	public Vector3f div(Vector3f r) {
		return new Vector3f(this.x / r.getX(), this.y / r.getY(), this.z / r.getZ());
	}
	
	public Vector3f div(float r) {
		return new Vector3f(this.x / r, this.y / r, this.z / r);
	}

	public Vector3f abs() {
		return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
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

	/**
	 * @return the z
	 */
	public float getZ() {
		return this.z;
	}

	/**
	 * @param z the z to set
	 */
	public void setZ(float z) {
		this.z = z;
	}
}
