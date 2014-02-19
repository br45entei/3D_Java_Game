package com.gmail.br45entei.base.engine;

public class SpotLight {
	private PointLight pointLight;
	private Vector3f direction;
	private float cutoff;
	private boolean isVisible;
	private float trueRange;
	

	public SpotLight(PointLight pointLight, Vector3f direction, float cutoff) {
		this.pointLight = pointLight;
		this.direction = direction.normalize();
		this.cutoff = cutoff;
		this.isVisible = this.pointLight.isVisible();
		this.setRange(this.pointLight.getRange());
	}
	
	public SpotLight(PointLight pointLight, Vector3f direction, float cutoff, boolean isVisible) {
		this.pointLight = pointLight;
		this.direction = direction.normalize();
		this.cutoff = cutoff;
		this.isVisible = isVisible;
		this.setRange(this.pointLight.getRange());
	}

	public PointLight getPointLight() {
		return pointLight;
	}
	
	public void setPointLight(PointLight pointLight) {
		this.pointLight = pointLight;
	}
	
	public Vector3f getDirection() {
		return direction;
	}
	
	public void setDirection(Vector3f direction) {
		this.direction = direction.normalize();
	}
	
	public float getCutoff() {
		return cutoff;
	}
	
	public void setCutoff(float cutoff) {
		this.cutoff = cutoff;
	}

	/**
	 * @return whether or not this SpotLight is visible.
	 */
	public boolean isVisible() {
		return isVisible;
	}

	/**
	 * @param isVisible sets whether or not this SpotLight is visible.
	 */
	public void setVisible(boolean isVisible) {
		this.pointLight.setVisible(isVisible);
		this.isVisible = isVisible;
	}

	/**
	 * @return the trueRange
	 */
	public float getRange() {
		return this.trueRange;
	}

	/**
	 * @param newRange the newRange to set
	 */
	private void setRange(float newRange) {
		this.trueRange = newRange;
		this.pointLight.setRange(newRange);
	}

}
