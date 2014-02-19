package com.gmail.br45entei.base.engine;

public class Transform {
	private Camera camera;
	
	private float zNear;
	private float zFar;
	private float width;
	private float height;
	private float fov;
	
	private Vector3f translation;
	private Vector3f rotation;
	private Vector3f scale;
	
	public Transform() {
		this.translation = new Vector3f(0,0,0);
		this.rotation = new Vector3f(0,0,0);
		this.scale = new Vector3f(1,1,1);
	}

	public Transform(Vector3f translation) {
		this.translation = translation;
		this.rotation = new Vector3f(0,0,0);
		this.scale = new Vector3f(1,1,1);
	}

	public Transform(Vector3f translation, Vector3f rotation) {
		this.translation = translation;
		this.rotation = rotation;
		this.scale = new Vector3f(1,1,1);
	}

	public Transform(Vector3f translation, Vector3f rotation, Vector3f scale) {
		this.translation = translation;
		this.rotation = rotation;
		this.scale = scale;
	}

	public Matrix4f getTransformation() {
		Matrix4f translationMatrix = new Matrix4f().initTranslation(translation.getX(), translation.getY(), translation.getZ());
		Matrix4f rotationMatrix = new Matrix4f().initRotation(rotation.getX(), rotation.getY(), rotation.getZ());
		Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());
		
		return translationMatrix.mul(rotationMatrix.mul(scaleMatrix));
	}
	
	public Matrix4f getProjectedTransformation() {
		Matrix4f transformationMatrix = getTransformation();
		Matrix4f projectionMatrix = new Matrix4f().initProjection(fov, width, height, zNear, zFar);
		Matrix4f cameraRotation = new Matrix4f().initCamera(camera.getForward(), camera.getUp());
		Matrix4f cameraTranslation = new Matrix4f().initTranslation(-camera.getPos().getX(), -camera.getPos().getY(), -camera.getPos().getZ());
		
		return projectionMatrix.mul(cameraRotation.mul(cameraTranslation.mul(transformationMatrix)));
	}
	
	public Vector3f getTranslation() {
		return translation;
	}
	
	public void setProjection(float fov, float width, float height, float zNear, float zFar) {
		this.fov = fov;
		this.width = width;
		this.height = height;
		this.zNear = zNear;
		this.zFar = zFar;
	}
	
	public void setTranslation(Vector3f translation) {
		this.translation = translation;
	}
	
	public void setTranslation(float x, float y, float z) {
		this.translation = new Vector3f(x, y, z);
	}

	public Vector3f getRotation() {
		return this.rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}
	
	public void setRotation(float x, float y, float z) {
		this.rotation = new Vector3f(x, y, z);
	}

	public Vector3f getScale() {
		return this.scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
	
	public void setScale(float x, float y, float z) {
		this.scale = new Vector3f(x, y, z);
	}

	public Camera getCamera() {
		return this.camera;
	}

	public void setCamera(Camera newCamera) {
		this.camera = newCamera;
	}
}
