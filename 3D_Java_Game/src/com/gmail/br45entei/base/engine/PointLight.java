/**
 * 
 */
package com.gmail.br45entei.base.engine;

/**
 * @author Brian_Entei
 *
 */
public class PointLight {
	private BaseLight baseLight;
	private Attenuation atten;
	private Vector3f position;
	private float range;
	private boolean isVisible;
	
	public PointLight(BaseLight baseLight, Attenuation atten, Vector3f position, float range) {
		this.baseLight = baseLight;
		this.atten = atten;
		this.position = position;
		this.range = range;
		this.setVisible(false);
	}
	
	public PointLight(BaseLight baseLight, Attenuation atten, Vector3f position, float range, boolean isVisible) {
		this.baseLight = baseLight;
		this.atten = atten;
		this.position = position;
		this.range = range;
		this.setVisible(isVisible);
	}
	
	public BaseLight getBaseLight() {
		return baseLight;
	}
	
	public void setBaseLight(BaseLight baseLight) {
		this.baseLight = baseLight;
	}
	
	public Attenuation getAtten() {
		return atten;
	}
	
	public void setAtten(Attenuation atten) {
		this.atten = atten;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

	/**
	 * @return whether or not this PointLight is visible.
	 */
	public boolean isVisible() {
		return isVisible;
	}

	/**
	 * @param isVisible sets whether or not this PointLight is visible.
	 */
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
		if(!this.isVisible) {
			this.position = new Vector3f(0, -256, 0);
		}
	}

}
