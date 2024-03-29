package com.gmail.br45entei.base.engine;

public class DirectionalLight {
	private BaseLight base;
	private Vector3f direction;
	
	public DirectionalLight(BaseLight base, Vector3f direction) {
		this.base = base;
		this.direction = direction.normalize();
	}

	public BaseLight getBase() {
		return this.base;
	}

	public void setBase(BaseLight base) {
		this.base = base;
	}

	public Vector3f getDirection() {
		return this.direction;
	}

	public void setDirection(Vector3f direction) {
		this.direction = direction.normalize();
	}
}
