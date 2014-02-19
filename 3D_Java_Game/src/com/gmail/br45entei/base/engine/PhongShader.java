package com.gmail.br45entei.base.engine;

public class PhongShader extends Shader {
	private static final int MAX_POINT_LIGHTS = 4;
	private static final int MAX_SPOT_LIGHTS = 4;
	private static Game game;

	public PhongShader(Game g) {
		this();
		game = g;
	}

	private Vector3f ambientLight = new Vector3f(0.1f, 0.1f, 0.1f);
	private DirectionalLight directionalLight = new DirectionalLight(new BaseLight(new Vector3f(0, 0, 0), 0), new Vector3f(0, 0, 0));
	private PointLight[] pointLights = new PointLight[] {};
	private SpotLight[] spotLights = new SpotLight[] {};

	private PhongShader() {
		super();

		addVertexShaderFromFile("phongVertex.vs");
		addFragmentShaderFromFile("phongFragment.fs");
		compileShader();

		addUniform("transform");
		addUniform("transformProjected");
		addUniform("baseColor");
		addUniform("ambientLight");

		addUniform("specularIntensity");
		addUniform("specularPower");
		addUniform("eyePos");

		addUniform("directionalLight.base.color");
		addUniform("directionalLight.base.intensity");
		addUniform("directionalLight.direction");

		for(int i = 0; i < MAX_POINT_LIGHTS; i++) {
			addUniform("pointLights[" + i + "].base.color");
			addUniform("pointLights[" + i + "].base.intensity");
			addUniform("pointLights[" + i + "].atten.constant");
			addUniform("pointLights[" + i + "].atten.linear");
			addUniform("pointLights[" + i + "].atten.exponent");
			addUniform("pointLights[" + i + "].position");
			addUniform("pointLights[" + i + "].range");
		}

		for(int i = 0; i < MAX_SPOT_LIGHTS; i++) {
			addUniform("spotLights[" + i + "].pointLight.base.color");
			addUniform("spotLights[" + i + "].pointLight.base.intensity");
			addUniform("spotLights[" + i + "].pointLight.atten.constant");
			addUniform("spotLights[" + i + "].pointLight.atten.linear");
			addUniform("spotLights[" + i + "].pointLight.atten.exponent");
			addUniform("spotLights[" + i + "].pointLight.position");
			addUniform("spotLights[" + i + "].pointLight.range");
			addUniform("spotLights[" + i + "].direction");
			addUniform("spotLights[" + i + "].cutoff");
		}
	}

	@Override
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
		if(material.getTexture() != null) {
			material.getTexture().bind();
		} else {
			RenderUtil.unbindTextures();
		}

		setUniform("transformProjected", projectedMatrix);
		setUniform("transform", worldMatrix);
		setUniform("baseColor", material.getColor());

		setUniform("ambientLight", ambientLight);
		setUniform("directionalLight", directionalLight);

		for(int i = 0; i < pointLights.length; i++) {
			setUniform("pointLights[" + i + "]", pointLights[i]);
		}

		for(int i = 0; i < spotLights.length; i++) {
			setUniform("spotLights[" + i + "]", spotLights[i]);
		}

		setUniformf("specularIntensity", material.getSpecularIntensity());
		setUniformf("specularPower", material.getSpecularPower());

		setUniform("eyePos", game.getCamera().getPos());
	}

	public Vector3f getAmbientLight() {
		return ambientLight;
	}

	/**
	 * 
	 * @param ambientLight The Vector3f colored light to set(could be considered 'sunlight' when set to (200/255, 200/255, 200/255)
	 */
	public void setAmbientLight(Vector3f ambientLight) {
		this.ambientLight = ambientLight;
	}

	public void setDirectionalLight(DirectionalLight directionalLight) {
		this.directionalLight = directionalLight;
	}

	public void setPointLights(PointLight[] pointLights) {
		if(pointLights.length > MAX_POINT_LIGHTS) {
			System.err.println("Error: You passed in too many point lights. Max allowed is \"" + MAX_POINT_LIGHTS + "\", you passed in \"" + pointLights.length + "\".");
			new Exception().printStackTrace();
			System.exit(1);
		}

		this.pointLights = pointLights;
	}

	public void setSpotLights(SpotLight[] spotLights) {
		if(spotLights.length > MAX_SPOT_LIGHTS) {
			System.err.println("Error: You passed in too many spot lights. Max allowed is \"" + MAX_SPOT_LIGHTS + "\", you passed in \"" + spotLights.length + "\".");
			new Exception().printStackTrace();
			System.exit(1);
		}

		this.spotLights = spotLights;
	}

	public void setUniform(String uniformName, BaseLight baseLight) {
		setUniform(uniformName + ".color", baseLight.getColor());
		setUniformf(uniformName + ".intensity", baseLight.getIntensity());
	}

	public void setUniform(String uniformName, DirectionalLight directionalLight) {
		setUniform(uniformName + ".base", directionalLight.getBase());
		setUniform(uniformName + ".direction", directionalLight.getDirection());
	}

	public void setUniform(String uniformName, PointLight pointLight) {
		setUniform(uniformName + ".base", pointLight.getBaseLight());
		setUniformf(uniformName + ".atten.constant", pointLight.getAtten().getConstant());
		setUniformf(uniformName + ".atten.linear", pointLight.getAtten().getLinear());
		setUniformf(uniformName + ".atten.exponent", pointLight.getAtten().getExponent());
		setUniform(uniformName + ".position", pointLight.getPosition());
		setUniformf(uniformName + ".range", pointLight.getRange());
	}

	public void setUniform(String uniformName, SpotLight spotLight) {
		setUniform(uniformName + ".pointLight", spotLight.getPointLight());
		setUniform(uniformName + ".direction", spotLight.getDirection());
		setUniformf(uniformName + ".cutoff", spotLight.getCutoff());
	}
}