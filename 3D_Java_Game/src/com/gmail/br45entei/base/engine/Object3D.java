/**
 * 
 */
package com.gmail.br45entei.base.engine;

import java.util.ArrayList;

/**
 * @author Brian_Entei
 *
 */
public class Object3D {
	@Override
	public Object3D clone() throws CloneNotSupportedException {
		Object3D object = (Object3D) super.clone();
		if(object.archivable == true) {
			object.setName(object.getName() + " - Copy");
			return object;
		}
		return null;
	}

	//TODO Instead of "mesh1, shader2, texture3, etc...." make them this: "mesh[0] would be mesh1, shader[1] would be shader 2, etc.) MUCH less coding! :)

	private String name = "Part";
	public Camera camera;
	public boolean archivable = true;
	public float timeUntilRemove = -1;
	private boolean isRemoved = false;

	//Rendering Characteristics
	public Vector3f defaultPosition = new Vector3f(0, 0, 0);
	public Vector3f defaultSize = new Vector3f(1, 1, 1);
	public Vector3f defaultRotation = new Vector3f(0, 0, 0);
	
	

	////Cosmetic Characteristics
	protected double Transparency = 0;
	protected Vector3f Color = new Vector3f(1, 1, 1);
	protected double Reflectivity = 0;
	protected final String[] MaterialTypes = {"test1", "test2", "test3", "test4", "test5", "test6", "floorTest", "floorTestWithText", "Plastic", "Concrete", "Wooden",
			"Metallic", "Rubber", "Icy", "Spongy", "Glass", "Waxy", "Rocky", "Grassy",
			"Stone", "Dirt",
			"Wooden_Crate", "Wooden_Crate_Transparent", "Dark_Wooden_Crate",
			"white", "red", "green", "blue", "black"};
	



	//Physical Characteristics
	
	protected String Shape = "Square";
	protected Vector3f Size = new Vector3f(2f, 1.5f, 2f);
	//protected Vector3f Position = new Vector3f(0.0f, 0.0f, 0.0f);
	//protected Vector3f Rotation = new Vector3f(0.0f, 0.0f, 0.0f);
	protected double Friction = 1;
	protected double Elasticity = 0;
	protected double Density = 1;
	protected double Weight = 1;
	protected boolean Collidable = true;
	protected double Temperature = 72.0D;
	protected final boolean CHUCK_NORRIS_PROOF = false;
	
	
	
	///**
	//	 * 
	//	 */
	//	private static final long serialVersionUID = 2601764030704061062L;
	
	//Misc. Characteristics
	private double Conductivity = 0.2;
	
	
	
	//float fieldDepth = 10.0f;
	//float fieldWidth = 10.0f;
	//float fieldHeight = 10.0f;
	
	private ArrayList<Vertex[]> vertices = new ArrayList<Vertex[]>();
	private Object[] indices = new Object[] {//Since you can't add new int[]s in later, I'ma just set it to be a bazillion right here........ Silly java, isn't there a better way?!
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0},
		new int[] {0, 0, 0, 0, 0, 0}
	};
	
	private ArrayList<PhongShader> shaders = new ArrayList<PhongShader>();
	private ArrayList<Mesh> meshes = new ArrayList<Mesh>();
	private ArrayList<Transform> transforms = new ArrayList<Transform>();
	//protected ArrayList<Texture> Textures = new ArrayList<Texture>();
	public ArrayList<Material> materials = new ArrayList<Material>();
	protected ArrayList<String> Materials = new ArrayList<String>();
	
	
	
	/*private Vertex[] vertices1;
	private int indices1[];
	private Vertex[] vertices2;
	private int indices2[];
	private Vertex[] vertices3;
	private int indices3[];
	private Vertex[] vertices4;
	private int indices4[];
	private Vertex[] vertices5;
	private int indices5[];
	private Vertex[] vertices6;
	private int indices6[];
	
	
	private PhongShader shader1;
	private Mesh mesh1;
	private Transform transform1 = new Transform(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(1.0f, 1.0f, 1.0f));
	protected Texture Texture1 = new Texture("Stone.png");
	public Material material1 = new Material(Texture1, Color, 1f, (float) Reflectivity);
	protected String Material1 = "Stone";


	private PhongShader shader2;
	private Mesh mesh2;
	private Transform transform2 = new Transform(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(1.0f, 1.0f, 1.0f));
	protected Texture Texture2 = new Texture("Stone.png");
	public Material material2 = new Material(Texture2, Color, 1f, (float) Reflectivity);
	protected String Material2 = "Stone";
	
	
	private PhongShader shader3;
	private Mesh mesh3;
	private Transform transform3 = new Transform(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(1.0f, 1.0f, 1.0f));
	protected Texture Texture3 = new Texture("Stone.png");
	public Material material3 = new Material(Texture3, Color, 1f, (float) Reflectivity);
	protected String Material3 = "Stone";
	
	
	private PhongShader shader4;
	private Mesh mesh4;
	private Transform transform4 = new Transform(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(1.0f, 1.0f, 1.0f));
	protected Texture Texture4 = new Texture("Stone.png");
	public Material material4 = new Material(Texture4, Color, 1f, (float) Reflectivity);
	protected String Material4 = "Stone";
	
	
	private PhongShader shader5;
	private Mesh mesh5;
	private Transform transform5 = new Transform(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(1.0f, 1.0f, 1.0f));
	protected Texture Texture5 = new Texture("Stone.png");
	public Material material5 = new Material(Texture5, Color, 1f, (float) Reflectivity);
	protected String Material5 = "Stone";
	
	
	private PhongShader shader6;
	private Mesh mesh6;
	private Transform transform6 = new Transform(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(1.0f, 1.0f, 1.0f));
	protected Texture Texture6 = new Texture("Stone.png");
	public Material material6 = new Material(Texture6, Color, 1f, (float) Reflectivity);
	protected String Material6 = "Stone";*/
	//End of Rendering Characteristics

	
	

	public String getName() {
		return this.name;
	}

	public void setName(String newName) {
		this.name = newName;
	}


	private boolean isProtected = false;

	public boolean isProtected() {
		return this.isProtected;
	}

	public void setIsProtected(boolean isProtected) {
		this.isProtected = isProtected;
	}


	private ShapeClass shapeclass = new ShapeClass();

	public ShapeClass getRenderedShape() {
		return this.shapeclass;
	}

	public void setRenderedShape(ShapeClass newShapeClass) {
		this.shapeclass = newShapeClass;
	}

	private Transform getTransform(int index) {
		if(index < this.transforms.size()) {
			return this.transforms.get(index);
		}
		return null;
	}


	private void addTransform(Transform transform) {
		this.transforms.add(transform);
	}

	public Matrix4f getProjectedTransformation(int index) {
		if(index < this.transforms.size()) {
			if(this.getTransform(index) != null) {
				return this.getTransform(index).getProjectedTransformation();
			}
		}
		return null;
	}

	public Matrix4f getTransformation(int index) {
		if(index < this.transforms.size()) {
			if(this.getTransform(index) != null) {
				return this.getTransform(index).getTransformation();
			}
		}
		return null;
	}

	/*public void setTransform(int index, Transform newTransform) {
		newTransform.setCamera(camera);
		this.transforms.set(index, newTransform);
	}*/

	public PhongShader getShader(int index) {
		if(index < this.shaders.size()) {
			return shaders.get(index);
		}
		return null;
	}

	public void setShader(int index, PhongShader shader) {
		if(index < this.shaders.size()) {
			this.shaders.set(index, shader);
		}
	}

	public void addShader(PhongShader shader) {
		this.shaders.add(shader);
	}

	public Mesh getMesh(int index) {
		if(index < this.meshes.size()) {
			return this.meshes.get(index);
		}
		return null;
	}

	public void setMesh(int index, Mesh newMesh) {
		if(index < this.meshes.size()) {
			this.meshes.set(index, newMesh);
		}
	}

	public void addMesh(Mesh newMesh) {
		this.meshes.add(newMesh);
	}

	public Material getRenderedMaterial(int index) {
		if(index < this.materials.size()) {
			return this.materials.get(index);
		}
		return null;
	}

	public void setRenderedMaterial(int index, Material material) {
		if(index < this.materials.size()) {
			this.materials.set(index, material);
		}
	}

	public void addRenderedMaterial(Material material) {
		this.materials.add(material);
	}

	@SuppressWarnings("unused")
	private void setAllTextures(String fileName) {
		for(int i = 0; i < this.materials.size();) {
			setTexture(i, fileName);
			i++;
		}
	}

	private void setTexture(int index, String fileName) {
		if(index < this.materials.size()) {
			this.materials.get(index).setTexture(new Texture(fileName));
		}
	}

	public Texture getTexture(int index) {
		if(index < this.materials.size()) {
			return this.materials.get(index).getTexture();
		}
		return null;
	}

	/**
	 * @return the top material
	 */
	public String getMaterial(int index) {
		if(index < this.Materials.size()) {
			return this.Materials.get(index);
		}
		return null;
	}

	/**
	 * @param material the top material to set
	 */
	public void setMaterial(int index, String material) {
		for(String curMaterial : this.MaterialTypes) {
			if(curMaterial.equals(material)) {//.contains(
				if(index < this.Materials.size()) {
					this.Materials.set(index, curMaterial);
					this.setTexture(index, material + ".png");
				}
				break;
			}
		}
	}

	public void setAllMaterials(String material) {
		for(int i = 0; i < this.Materials.size();) {
			
			setMaterial(i, material);
			
			i++;
		}
	}

	public void addMaterial(String material) {
		this.Materials.add(material);
	}

	public void addMaterials(int numberOfMaterialsToAdd, String material) {
		for(int i = 0; i < numberOfMaterialsToAdd;) {
			this.addMaterial(material);
			i++;
		}
	}

	private void updateRemoveTime() {
		if(this.timeUntilRemove != -1) {
			if(this.timeUntilRemove <= 0) {
				this.timeUntilRemove = 0;
				//camera.getGame().getObjectsToRender().remove(this); Let's let the Game.render() function do that.
				this.isRemoved = true;
				return;
			}
			this.timeUntilRemove -= 0.001f;
		}
	}

	private void updateTransforms() {
		int i = 0;
		for(Transform curTransform : this.transforms) {
			if(curTransform != null) {
				curTransform.setCamera(camera);
				curTransform.setProjection(camera.getFOV(), Window.getWidth(), Window.getHeight(), 0.1f, 1000);
				this.transforms.set(i, curTransform);//This line may be unnecessary.
			}
			i++;
		}
		/*transform1.setCamera(camera);
		transform1.setProjection(camera.getFOV(), Window.getWidth(), Window.getHeight(), 0.1f, 1000);
		if(this.getShape().equals("Cube")) {
			transform2.setCamera(camera);
			transform2.setProjection(camera.getFOV(), Window.getWidth(), Window.getHeight(), 0.1f, 1000);
			transform3.setCamera(camera);
			transform3.setProjection(camera.getFOV(), Window.getWidth(), Window.getHeight(), 0.1f, 1000);
			transform4.setCamera(camera);
			transform4.setProjection(camera.getFOV(), Window.getWidth(), Window.getHeight(), 0.1f, 1000);
			transform5.setCamera(camera);
			transform5.setProjection(camera.getFOV(), Window.getWidth(), Window.getHeight(), 0.1f, 1000);
			transform6.setCamera(camera);
			transform6.setProjection(camera.getFOV(), Window.getWidth(), Window.getHeight(), 0.1f, 1000);
		}*/
	}

	public void render() {
		updateRemoveTime();
		if(this.isRemoved == true) {Game.print("The object \"" + this.getName() + "\" has been removed. Not rendering it anymore...");return;}
		
		//1. Bind all the shaders(reset them)
		int i1 = 0;
		for(PhongShader curShader : this.shaders) {
			if(curShader != null) {
				curShader.bind();
				this.shaders.set(i1, curShader);//This line may be unnecessary.
			}
			i1++;
		}
		/*this.getShader1().bind();
		if(this.getShape().equals("Cube")) {
			this.getShader2().bind();
			this.getShader3().bind();
			this.getShader4().bind();
			this.getShader5().bind();
			this.getShader6().bind();
		}*/

		//2. Update all of the transforms(Position, rotation, size)
		this.updateTransforms();

		//3. Update the first shader's uniforms, then draw the first mesh, THEN continue on to the next shader + mesh, until they're all done!
		//This effectively draws the textures onto each mesh.
		
		int i2 = 0;
		for(PhongShader curShader : this.shaders) {
			Mesh curMesh = this.meshes.get(i2);
			Transform curTransform = this.transforms.get(i2);
			if((curShader != null) && (curMesh != null) && (curTransform != null)) {
				
				
				curShader.updateUniforms(curTransform.getTransformation(), curTransform.getProjectedTransformation(), this.getRenderedMaterial(i2));
				curMesh.draw();//Renders this object3D!
			}
			i2++;
		}
		/*this.getShader1().updateUniforms(this.getTransformation1(), this.getProjectedTransformation1(), this.getRenderedMaterial1());
		this.getMesh1().draw();//Renders this object3D!
		if(this.getShape().equals("Cube")) {
			this.getShader2().updateUniforms(this.getTransformation2(), this.getProjectedTransformation2(), this.getRenderedMaterial2());
			this.getMesh2().draw();
			this.getShader3().updateUniforms(this.getTransformation3(), this.getProjectedTransformation3(), this.getRenderedMaterial3());
			this.getMesh3().draw();
			this.getShader4().updateUniforms(this.getTransformation4(), this.getProjectedTransformation4(), this.getRenderedMaterial4());
			this.getMesh4().draw();
			this.getShader5().updateUniforms(this.getTransformation5(), this.getProjectedTransformation5(), this.getRenderedMaterial5());
			this.getMesh5().draw();
			this.getShader6().updateUniforms(this.getTransformation6(), this.getProjectedTransformation6(), this.getRenderedMaterial6());
			this.getMesh6().draw();
		}*/

		//4. Update the shader with any different kinds of lights!
		this.updateLights();
	}

	private void updateLights() {
		for(PhongShader curShader : this.shaders) {
			if(curShader != null) {
				curShader.setAmbientLight(this.camera.getGame().day ? this.camera.getGame().sunlight : this.camera.getGame().moonlight);
				//curShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.1f), new Vector3f(1,1,1)));//PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1f, 1f, 1f), 1f), new Vector3f(0, 0, 0).normalize()));			
				curShader.setPointLights(new PointLight[] {});
				curShader.setSpotLights(new SpotLight[] {});
				curShader.setPointLights(this.camera.getGame().getRenderablePointLightArray());
				curShader.setSpotLights(this.camera.getGame().getRenderableSpotLightArray());
			}
		}
		
		/*PhongShader shader1 = this.getShader1();
		shader1.setAmbientLight(this.camera.getGame().day ? this.camera.getGame().sunlight : this.camera.getGame().moonlight);
		//shader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.1f), new Vector3f(1,1,1)));//PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1f, 1f, 1f), 1f), new Vector3f(0, 0, 0).normalize()));			
		shader1.setPointLights(new PointLight[] {});
		shader1.setSpotLights(new SpotLight[] {});
		shader1.setPointLights(this.camera.getGame().getRenderablePointLightArray());
		shader1.setSpotLights(this.camera.getGame().getRenderableSpotLightArray());

		if(this.getShape().equals("Cube")) {
			PhongShader shader2 = this.getShader2();
			shader2.setAmbientLight(this.camera.getGame().day ? this.camera.getGame().sunlight : this.camera.getGame().moonlight);
			shader2.setPointLights(new PointLight[] {});
			shader2.setSpotLights(new SpotLight[] {});
			shader2.setPointLights(this.camera.getGame().getRenderablePointLightArray());
			shader2.setSpotLights(this.camera.getGame().getRenderableSpotLightArray());
			
			PhongShader shader3 = this.getShader3();
			shader3.setAmbientLight(this.camera.getGame().day ? this.camera.getGame().sunlight : this.camera.getGame().moonlight);
			shader3.setPointLights(new PointLight[] {});
			shader3.setSpotLights(new SpotLight[] {});
			shader3.setPointLights(this.camera.getGame().getRenderablePointLightArray());
			shader3.setSpotLights(this.camera.getGame().getRenderableSpotLightArray());
			
			PhongShader shader4 = this.getShader4();
			shader4.setAmbientLight(this.camera.getGame().day ? this.camera.getGame().sunlight : this.camera.getGame().moonlight);
			shader4.setPointLights(new PointLight[] {});
			shader4.setSpotLights(new SpotLight[] {});
			shader4.setPointLights(this.camera.getGame().getRenderablePointLightArray());
			shader4.setSpotLights(this.camera.getGame().getRenderableSpotLightArray());
			
			PhongShader shader5 = this.getShader5();
			shader5.setAmbientLight(this.camera.getGame().day ? this.camera.getGame().sunlight : this.camera.getGame().moonlight);
			shader5.setPointLights(new PointLight[] {});
			shader5.setSpotLights(new SpotLight[] {});
			shader5.setPointLights(this.camera.getGame().getRenderablePointLightArray());
			shader5.setSpotLights(this.camera.getGame().getRenderableSpotLightArray());
			
			PhongShader shader6 = this.getShader6();
			shader6.setAmbientLight(this.camera.getGame().day ? this.camera.getGame().sunlight : this.camera.getGame().moonlight);
			shader6.setPointLights(new PointLight[] {});
			shader6.setSpotLights(new SpotLight[] {});
			shader6.setPointLights(this.camera.getGame().getRenderablePointLightArray());
			shader6.setSpotLights(this.camera.getGame().getRenderableSpotLightArray());
		}*/
	}

	public Object3D(Camera camera, String shape) {
		this.camera = camera;
		this.setShape(shape);
		int numberOfSidesToRender = this.getRenderedShape().getNumberOfShapeSides(shape);
		
		//for int i = 0; i is not greater than the number of renderable sides; do:
		for(int i = 0; i < numberOfSidesToRender;) {
			this.addTransform(new Transform());
			this.addShader(new PhongShader(camera.getGame()));
			
			this.addRenderedMaterial(new Material(new Texture("Stone.png"), new Vector3f(1,1,1), 1, 8));
			this.addMaterial("Stone");
			
			
			
			
			i++;
		}
		
		/*setShader1(new PhongShader(camera.getGame()));
		if(this.getShape().equals("Cube")) {
			setShader2(new PhongShader(camera.getGame()));
			setShader3(new PhongShader(camera.getGame()));
			setShader4(new PhongShader(camera.getGame()));
			setShader5(new PhongShader(camera.getGame()));
			setShader6(new PhongShader(camera.getGame()));
		}
		
		this.setRenderedMaterial1(new Material(new Texture("Stone.png"), new Vector3f(1,1,1), 1, 8));
		this.setMaterial1("Stone");
		
		if(this.getShape().equals("Cube")) {
			this.setRenderedMaterial2(new Material(new Texture("Stone.png"), new Vector3f(1,1,1), 1, 8));
			this.setMaterial2("Stone");
			this.setRenderedMaterial3(new Material(new Texture("Stone.png"), new Vector3f(1,1,1), 1, 8));
			this.setMaterial3("Stone");
			this.setRenderedMaterial4(new Material(new Texture("Stone.png"), new Vector3f(1,1,1), 1, 8));
			this.setMaterial4("Stone");
			this.setRenderedMaterial5(new Material(new Texture("Stone.png"), new Vector3f(1,1,1), 1, 8));
			this.setMaterial5("Stone");
			this.setRenderedMaterial6(new Material(new Texture("Stone.png"), new Vector3f(1,1,1), 1, 8));
			this.setMaterial6("Stone");
			
		}*/
		
		this.updateTransforms();
		this.setSize(1.0f, 1.0f, 1.0f);//transform.setScale(5.0f, 500.0f, 5.0f);//'size'
		this.setPosition(0, 0, 0);//transform.setTranslation(0, -1, 0);//Position
		this.setRotation(0, 0, 0);
		updateMeshes(true);//Initializes the meshes.
	}

	public Vector3f getPosition() {
		//return this.getTransform1().getTranslation();
		
		Vector3f rtrn = new Vector3f(0, 0, 0);
		for(Transform curTransform : this.transforms) {
			if(curTransform != null) {
				rtrn = curTransform.getTranslation();
				break;
			}
		}
		return rtrn;
	}

	public float offset = 0.0f;//-0.14200073f;//0.01f;
	public void setPosition(Vector3f newPosition) {
		for(Transform curTransform : this.transforms) {
			if(curTransform != null) {
				curTransform.setTranslation(newPosition);
			}
		}
		
		
		
		/*Game.print("New position for \"" + this.getName() + "\" is: " + newPosition.toString());
		this.getTransform1().setTranslation(newPosition);
		this.getTransform2().setTranslation(newPosition);
		this.getTransform3().setTranslation(newPosition);
		this.getTransform4().setTranslation(newPosition);
		this.getTransform5().setTranslation(newPosition);
		this.getTransform6().setTranslation(newPosition);*/
		
		
		
		/*
		this.getTransform2().setTranslation(new Vector3f(newPosition.getX(), newPosition.getY() + 
				
				(0.014609775f * this.getSize().getY()), newPosition.getZ()));//Bottom
		
		this.getTransform4().setTranslation(new Vector3f(newPosition.getX() + 
				
				(0.0161699382f * this.getSize().getX()), newPosition.getY() + 
				(0.0012899943f * this.getSize().getY()), newPosition.getZ()));//Right
		
		this.getTransform3().setTranslation(new Vector3f(newPosition.getX() - 
				
				(0.0161699382f * this.getSize().getX()), newPosition.getY() + 
				(0.014429783f * this.getSize().getY()), newPosition.getZ()));//Left
		
		this.getTransform5().setTranslation(new Vector3f(newPosition.getX(), newPosition.getY() + 
				
				(2.6999682E-4f * this.getSize().getY()), newPosition.getZ() + 
				(0.015569734f * this.getSize().getZ())));//Front//0.015409743f
		
		this.getTransform6().setTranslation(new Vector3f(newPosition.getX(), newPosition.getY() + 
				
				(0.015569734f * this.getSize().getY()), newPosition.getZ() + 0.0f));//Back
		*/
		// ^ Done
		// \/ NOT done
	}

	public void setPosition(float x, float y, float z) {
		this.setPosition(new Vector3f(x, y, z));
	}

	public Vector3f getRotation() {
		//return this.getTransform1().getRotation();
		
		Vector3f rtrn = new Vector3f(0, 0, 0);
		for(Transform curTransform : this.transforms) {
			if(curTransform != null) {
				rtrn = curTransform.getRotation();
				break;
			}
		}
		return rtrn;
	}

	public void setRotation(Vector3f newPosition) {
		for(Transform curTransform : this.transforms) {
			if(curTransform != null) {
				curTransform.setRotation(newPosition);
			}
		}
		
		
		/*this.getTransform1().setRotation(newPosition);//Top
		Game.print(newPosition.toString());
		//-Y X -Z for adding 0 0 -90 To the left side, and
		//Y X -Z for adding 0 0 +90 To the right side.
		//X Y Z for adding 0 0 0 to the top
		//X Y Z for adding 0 0 +180 to the bottom
		this.getTransform2().setRotation(new Vector3f(-newPosition.getX(), -newPosition.getY(), newPosition.getZ() + 180.0f));//Bottom
		this.getTransform3().setRotation(new Vector3f(newPosition.getY(), -newPosition.getX(), newPosition.getZ() - 90.0f));//Left
		this.getTransform4().setRotation(new Vector3f(-newPosition.getY(), newPosition.getX(), newPosition.getZ() + 90.0f));//Right
		this.getTransform5().setRotation(new Vector3f(newPosition.getX() - 90.0f, newPosition.getY(), newPosition.getZ()));//Front
		this.getTransform6().setRotation(new Vector3f(newPosition.getX() + 90.0f, newPosition.getY(), newPosition.getZ()));//Back*/
	}

	public void setRotation(float x, float y, float z) {
		this.setRotation(new Vector3f(x, y, z));
	}

	public Vector3f getSize() {
		//return this.getTransform1().getScale();
		
		Vector3f rtrn = new Vector3f(0, 0, 0);
		for(Transform curTransform : this.transforms) {
			if(curTransform != null) {
				rtrn = curTransform.getScale();
				break;
			}
		}
		return rtrn;
	}

	public void setSize(float x, float y, float z) {
		this.setSize(new Vector3f(x, y, z));
	}

	public void setSize(Vector3f newSize) {
		for(Transform curTransform : this.transforms) {
			if(curTransform != null) {
				curTransform.setScale(newSize);
			}
		}
		/*this.getTransform1().setScale(newSize);
		this.getTransform2().setScale(newSize);
		this.getTransform3().setScale(newSize);
		this.getTransform4().setScale(newSize);
		this.getTransform5().setScale(newSize);
		this.getTransform6().setScale(newSize);*/
	}

	private void updateMeshes(boolean firstTime) {
		Game.print("updateMeshes() is being run for the part: " + this.getName());
		//final String ShapeTypes[] = this.shapeclass.ShapeTypes;
		//Square is default
		
		int numberOfShapeSidesToRender = this.getRenderedShape().getNumberOfShapeSides(this.getShape());
		
		
		
		for(int i = 0; i < numberOfShapeSidesToRender;) {
			if(i >= vertices.size()) {//if(firstTime) {
				vertices.add((Vertex[]) this.getRenderedShape().Floor2D[0]);
			} else {
				vertices.set(i, (Vertex[]) this.getRenderedShape().Floor2D[0]);
			}
			indices[i] = (this.getRenderedShape().Floor2D[1]);
			
			if(this.getShape().equals("Cube")) {
				vertices.set(i, (Vertex[]) ((Object[]) this.getRenderedShape().Cube3D[i])[0]);
				indices[i] = ((Object[]) this.getRenderedShape().Cube3D[i])[1];
				
				// ^ ... Migraine time! AHHHHHH
			}
			
			if(firstTime) {
				
				this.addMesh(new Mesh(vertices.get(i), (int[]) indices[i], true));
			} else {
				this.setMesh(i, new Mesh(vertices.get(i), (int[]) indices[i], true));
			}
			i++;
		}
		/*vertices1 = (Vertex[]) this.shapeclass.Floor2D[0];
		indices1 = ((int[]) this.shapeclass.Floor2D[1]);
		if(this.getShape().equals("Cube")) {
			vertices1 = (Vertex[]) this.shapeclass.CubeTop2D[0];
			indices1 = ((int[]) this.shapeclass.CubeTop2D[1]);
			
			vertices2 = (Vertex[]) this.shapeclass.CubeBottom2D[0];
			indices2 = ((int[]) this.shapeclass.CubeBottom2D[1]);
			
			vertices3 = (Vertex[]) this.shapeclass.CubeFront2D[0];
			indices3 = ((int[]) this.shapeclass.CubeFront2D[1]);
			
			vertices4 = (Vertex[]) this.shapeclass.CubeBack2D[0];
			indices4 = ((int[]) this.shapeclass.CubeBack2D[1]);
			
			vertices5 = (Vertex[]) this.shapeclass.CubeLeft2D[0];
			indices5 = ((int[]) this.shapeclass.CubeLeft2D[1]);
			
			vertices6 = (Vertex[]) this.shapeclass.CubeRight2D[0];
			indices6 = ((int[]) this.shapeclass.CubeRight2D[1]);
			
			this.setMesh2(new Mesh(vertices2, indices2, true));
			this.setMesh3(new Mesh(vertices3, indices3, true));
			this.setMesh4(new Mesh(vertices4, indices4, true));
			this.setMesh5(new Mesh(vertices5, indices5, true));
			this.setMesh6(new Mesh(vertices6, indices6, true));
			
		} else if(this.getShape().equals(ShapeTypes[2])) {//TriangularPrism
			vertices1 = (Vertex[]) this.shapeclass.Triangular_Prism[0];
			indices1 = ((int[]) this.shapeclass.Triangular_Prism[1]);
		} else if(this.getShape().equals(ShapeTypes[4])) {//Double-sided square
			vertices1 = (Vertex[]) this.shapeclass.Surface2D[0];
			indices1 = ((int[]) this.shapeclass.Surface2D[1]);
		}
		this.setMesh1(new Mesh(vertices1, indices1, true));*/
		
	}

	public String getShape() {
		return this.Shape;
	}

	public boolean setShape(String newShape) {
		if(this.getRenderedShape().checkIfStringIsValidShape(newShape)) {
			this.Shape = newShape;
			updateMeshes(false);
		}
		return false;
	}

	/**
	 * @return the transparency
	 */
	public double getTransparency() {
		return this.Transparency;
	}

	/**
	 * @param transparency the transparency to set
	 */
	public void setTransparency(double transparency) {
		this.Transparency = transparency;
	}

	/**
	 * @return the conductivity
	 */
	public double getConductivity() {
		return this.Conductivity;
	}

	/**
	 * @param conductivity the conductivity to set
	 */
	public void setConductivity(double conductivity) {
		this.Conductivity = conductivity;
	}

	/**
	 * @return the reflectivity
	 */
	public double getReflectivity() {
		return this.Reflectivity;
	}

	/**
	 * @param reflectivity the reflectivity to set
	 */
	public void setReflectivity(double reflectivity) {
		this.Reflectivity = reflectivity;
	}
}
