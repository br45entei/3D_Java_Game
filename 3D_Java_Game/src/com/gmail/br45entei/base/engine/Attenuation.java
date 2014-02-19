package com.gmail.br45entei.base.engine;

public class Attenuation {
	private float constant;
	private float linear;
	private float exponent;
	
	public Attenuation(float constant, float linear, float exponent) {
		this.constant = constant;
		this.linear = linear;
		this.exponent = exponent;
	}
	
	public float getConstant() {
		return this.constant;
	}
	
	public void setConstant(float constant) {
		this.constant = constant;
	}
	
	public float getLinear() {
		return this.linear;
	}
	
	public void setLinear(float linear) {
		this.linear = linear;
	}
	
	public float getExponent() {
		return this.exponent;
	}
	
	public void setExponent(float exponent) {
		this.exponent = exponent;
	}
}
