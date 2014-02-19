package com.gmail.br45entei.base.engine;

import java.util.ArrayList;

public class Game {
	/**///private Mesh mesh;
	/**///private Shader shader;
	/**///private Material floor;
	/**///public static ShapeClass floorShape;
	/**///private Transform transform;
	private Camera camera;
	private TextWindow debuggingWindow = new TextWindow();

	public Vector3f moonlight = new Vector3f(0.2f,0.2f,0.2f);
	public Vector3f sunlight = new Vector3f(0.7f,0.7f,0.7f);//Bright Sunlight
	public Vector3f nightskycolor = new Vector3f(0.0f, 28.0f/255.0f, 56.0f/255.0f);
	public Vector3f dayskycolor = new Vector3f(0.0f, 172.0f/255.0f, 255.0f/255.0f);//Bright sky blue
	public boolean day = false;

	private Thread textWindowThread = null;

	public ArrayList<Object3D> objectsToRender = new ArrayList<Object3D> ();

	public ArrayList<Object3D> getObjectsToRender() {
		return objectsToRender;
	}

	public void clearObjectsToRender(boolean removeProtectedObjects) {
		for(Object3D curObject : this.getObjectsToRender()) {
			if((curObject.isProtected() == false) || removeProtectedObjects) {
				this.getObjectsToRender().remove(curObject);
			}
		}
	}

	public void createObject(String[] args) {//createobject {Name} {ShapeName} {textureName} {posX} {posY} {posZ}
		createObject(args, new Vector3f(0, 0, 0));
	}

	public void createObject(String[] args, Vector3f rotation) {
		final Object3D newObject = new Object3D(getCamera(), args[1]);
		newObject.setName(args[0]);
		@SuppressWarnings("boxing")
		Vector3f newPosition = new Vector3f(Float.valueOf(args[3]), Float.valueOf(args[4]), Float.valueOf(args[5]));
		newObject.setPosition(newPosition);
		newObject.defaultPosition = newObject.getPosition();
		
		newObject.setRotation(rotation);
		newObject.defaultRotation = newObject.getRotation();
		
		//newObject.setAllMaterials(args[2]);
		newObject.addMaterials(newObject.getRenderedShape().getNumberOfShapeSides(args[1]), args[2]);
		
		/*newObject.setMaterial1(args[2]);
		if(newObject.getShape().equals("Cube")) {
			newObject.setMaterial2(args[2]);
			newObject.setMaterial3(args[2]);
			newObject.setMaterial4(args[2]);
			newObject.setMaterial5(args[2]);
			newObject.setMaterial6(args[2]);
		}*/
		
		newObject.camera.getGame().getObjectsToRender().add(newObject);
	}


	public static Object3D floor;
	public Object3D test;


	PointLight pLight1 = new PointLight(new BaseLight(new Vector3f(1, 0, 0), 0.8f), new Attenuation(0, 0, 1), new Vector3f(-2, 0, 5f), 5, true);
	PointLight pLight2 = new PointLight(new BaseLight(new Vector3f(0, 1, 0), 0.8f), new Attenuation(0, 0, 1), new Vector3f(2, 0, 7f), 5, true);
	PointLight pLight3 = new PointLight(new BaseLight(new Vector3f(0, 0, 1), 0.8f), new Attenuation(0, 0, 1), new Vector3f(6, 0, 9f), 5, true);

	SpotLight sLight1 = new SpotLight(new PointLight(new BaseLight(new Vector3f(1f, 1f, 1f), 0.8f), new Attenuation(0,0,0.025f), new Vector3f(-2,0,5f), 30), new Vector3f(1,1,1), 0.7f, true);

	protected static final String VERSION = "0.0.1";
	//Options
	
	public static float cameraYawLimit = 0.95f;
	
	public boolean showDebugMsgs = false;
	
	public static boolean allow_key_panning = false;
	public static boolean allow_vertical_key_travel = true;

	public boolean allowObject3DEditing = true;

	public static boolean horizonLock = false;
	public static boolean allowHorizonLockToggle = true;
	
	public static boolean allowCameraLimitPos = true;
	/**This value is to be used in the following fashion: if(camera.getPosition().getY() <= Game.limitCameraYPos) {*/
	public static float limitCameraYPos = -64.0f;
	
	//public static Vector3f meshScale = new Vector3f(1.0f, 1.0f, 1.0f);
	
	public static String CAMERA_MODE = "NORMAL";//"FREESTYLE" and "NORMAL" can go here
	public static final boolean allow_toggle_camera_mode = true;
	private static final boolean allow_Debug_Window = true;
	protected static final boolean showCurrentFrameRate = false;
	
	public static boolean allow_pos_reset = true;
	
	protected boolean useTextWindow = true;
	
	//End of Options


	public ArrayList<PointLight> storedPointLights = new ArrayList<PointLight>();
	public ArrayList<SpotLight> storedSpotLights = new ArrayList<SpotLight>();
	private ArrayList<PointLight> renderablePointLights = new ArrayList<PointLight>();
	private ArrayList<SpotLight> renderableSpotLights = new ArrayList<SpotLight>();
	
	public static void print(Object obj) {
		System.out.println(obj);
		System.out.print(">");
	}
	
	public Game() {final Game game = this;
	
		//Mesh box = new Mesh("box.obj");
		
		if(Game.allow_Debug_Window) {this.initTextWindow(game);}
		camera = new Camera(this);

		game.registerLights();
		//game.updateLights();

		//Baseplate
		floor = new Object3D(camera, "Square");
		floor.setAllMaterials("floorTestWithText");//floor.setMaterial(0, "test1");
		//floor.setMaterial(1, "floorTestWithText");
		//floor.setMaterial(2, "floorTestWithText");
		//floor.setMaterial(3, "floorTestWithText");
		//floor.setMaterial(4, "floorTestWithText");
		//floor.setMaterial(5, "floorTestWithText");

		floor.setPosition(0.0f, -0.01f, 0.0f);
		floor.defaultPosition = floor.getPosition();
		//floor.setRotation(0.0f, 0.0f, 0.0f);
		floor.defaultRotation = floor.getRotation();
		floor.setSize(10.0f, 10.0f, 10.0f);
		floor.defaultSize = floor.getSize();

		floor.setName("Baseplate");
		floor.setIsProtected(true);//We don't want users to delete the baseplate, now do we??
		objectsToRender.add(floor);

		//Cube3D
		test = new Object3D(camera, "Cube");
		test.setShape("Cube");
		
		test.setMaterial(0, "test1");//Top
		test.setMaterial(1, "Dark_Wooden_Crate");//Bottom
		test.setMaterial(2, "Wooden_Crate");//Right
		test.setMaterial(3, "Wooden_Crate_Transparent");//Left
		test.setMaterial(4, "Dark_Wooden_Crate");//Front
		test.setMaterial(5, "Stone");//Back
		
		
		
		test.setPosition(0.0f, 0.0f, 0.0f);
		test.defaultPosition = test.getPosition();
		test.setRotation(0.0f, 0.0f, 0.0f);
		test.defaultRotation = test.getRotation();
		test.setSize(.01f, .01f, .01f);
		test.defaultSize = test.getSize();

		test.setName("Cube3D");
		test.setIsProtected(true);//I don't want this unique item to be able to get removed yet.
		test.timeUntilRemove = -1;//100 (unknown time increment atm)s until this object3D is removed.
		objectsToRender.add(test);
		//End

		/**///floor = new Material(new Texture(floorTexture), new Vector3f(1,1,1), 1, 8);
		/**///shader = PhongShader.getInstance(this);
		/**///floorShape = new ShapeClass();
		/**///transform = new Transform();
		/**///this.mesh = new Mesh((Vertex[]) floorShape.Floor2D[0], ((int[]) floorShape.Floor2D[1]), true);//updateFloor();

//		Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1,-1,0), new Vector2f(0,0)),
//						  new Vertex(new Vector3f(0,1,0), new Vector2f(0.5f,0)),
//						  new Vertex(new Vector3f(1,-1,0), new Vector2f(1.0f,0)),
//						  new Vertex(new Vector3f(0,-1,1), new Vector2f(0.5f,1.0f))};
//		
//		int[] indices = new int[] {3,1,0,
//								   2,1,3,
//								   0,1,2,
//								   0,2,3};

//		Vertex[] vertices = new Vertex[] { new Vertex( new Vector3f(-1.0f, -1.0f, 0.5773f),	new Vector2f(0.0f, 0.0f)),
//				new Vertex( new Vector3f(0.0f, -1.0f, -1.15475f),		new Vector2f(0.5f, 0.0f)),
//				new Vertex( new Vector3f(1.0f, -1.0f, 0.5773f),		new Vector2f(1.0f, 0.0f)),
//				new Vertex( new Vector3f(0.0f, 1.0f, 0.0f),      new Vector2f(0.5f, 1.0f)) };

//		int indices[] = { 0, 3, 1,
//				1, 3, 2,
//				2, 3, 0,
//				1, 2, 0 };

		//float fieldDepth = 10.0f;
		//float fieldWidth = 10.0f;

		//Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
		//					new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
		//					new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
		//					new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};

		//int indices[] = {0, 1, 2,
		//				 2, 1, 3,
		//				 1, 3, 2,
		//				 2, 3, 2};
		
		

		/**////*T*/transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
		/**///transform.setCamera(camera);
		/**///transform.setScale(1.0f, 1.0f, 1.0f);//transform.setScale(5.0f, 500.0f, 5.0f);//'size'
		/**///transform.setTranslation(0, 0, 0);//transform.setTranslation(0, -1, 0);//Position

	}

	/**
	 * Registers the three debugging light and the camera's spotlight(flashlight)
	 */
	private void registerLights() {
		addStoredPointLight(pLight1);
		addStoredPointLight(pLight2);
		addStoredPointLight(pLight3);
		
		addStoredSpotLight(sLight1);//TODO Make the camera class have it's own flashlight!
	}

	/**
	 * @param newSLight the newPLight to add
	 */
	public void addStoredSpotLight(SpotLight newSLight) {
		this.storedSpotLights.add(newSLight);
	}
	
	/**
	 * @param storedSpotLights the storedSpotLights to get
	 */
	private ArrayList<SpotLight> getStoredSpotLights() {
		return this.storedSpotLights;
	}
	
	public ArrayList<SpotLight> getRenderableSpotLightArrayList() {
		renderableSpotLights = new ArrayList<SpotLight>();
		
		for(SpotLight curSpotLight : getStoredSpotLights()) {
			if(curSpotLight.isVisible()) {
				renderableSpotLights.add(curSpotLight);
			} else {
				renderableSpotLights.remove(curSpotLight);
				
			}
		}
		
		return renderableSpotLights;
	}
	
	public SpotLight[] getRenderableSpotLightArray() {
		ArrayList<SpotLight> curSpots = getRenderableSpotLightArrayList();
		
		SpotLight[] spotLightsToRender =  new SpotLight[curSpots.size()];
		spotLightsToRender = curSpots.toArray(spotLightsToRender);
		
		return spotLightsToRender;
	}
	/**
	 * @param storedSpotLights the storedSpotLights to set
	 */
	public void setStoredSpotLights(ArrayList<SpotLight> storedSpotLights) {
		this.storedSpotLights = storedSpotLights;
	}

	/**
	 * @param newPLight the newPLight to add
	 */
	public void addStoredPointLight(PointLight newPLight) {
		this.storedPointLights.add(newPLight);
	}
	
	/**
	 * @param storedPointLights the storedPointLights to get
	 */
	private ArrayList<PointLight> getStoredPointLights() {
		return this.storedPointLights;
	}
	
	public ArrayList<PointLight> getRenderablePointLightArrayList() {
		this.renderablePointLights = new ArrayList<PointLight>();
		
		for(PointLight curPointLight : getStoredPointLights()) {
			if(curPointLight.isVisible()) {
				this.renderablePointLights.add(curPointLight);
			} else {
				this.renderablePointLights.remove(curPointLight);
			}
		}
		
		return this.renderablePointLights;
	}
	
	public PointLight[] getRenderablePointLightArray() {
		ArrayList<PointLight> curPoints = getRenderablePointLightArrayList();
		
		PointLight[] pointLightsToRender = new PointLight[curPoints.size()];
		pointLightsToRender = curPoints.toArray(pointLightsToRender);
		
		return pointLightsToRender;
	}
	/**
	 * @param storedPointLights the storedPointLights to set
	 */
	public void setStoredPointLights(ArrayList<PointLight> storedPointLights) {
		this.storedPointLights = storedPointLights;
	}

	private void initTextWindow(final Game game) {
		//new Thread(new Runnable() {
		//	@Override
		//	public void run() {
				game.getTextWindow().setSize(640, 520);
				game.getTextWindow().setVisible(true);
				game.getTextWindow().setTitle("(Debugging Window)");
				game.getTextWindow().setResizable(true);
				//game.getTextWindow().setDefaultCloseOperation(TextWindow.EXIT_ON_CLOSE);
				//game.getTextWindow().setLocationRelativeTo(null);

		//	}
		//}).start();
	}
	
	public void input() {
		getCamera().input();
//		if(Input.getKeyDown(Input.KEY_UP)) {
//			System.out.println("We've just pressed up!");
//		}
//		if(Input.getKeyUp(Input.KEY_UP)) {
//			System.out.println("We've just released up!");
//		}
//		if(Input.getMouseDown(1)) {
//			System.out.println("We've just right clicked at " + Input.getMousePosition().toString());
//		}
//		if(Input.getMouseUp(1)) {
//			System.out.println("We've just released right mouse button!");
//		}
	}
	
	float temp = 0.0f;
	public double fps = 0;
	public double curFPS = 0;
	protected boolean running = true;
	
	public void update() {
		temp += Time.getDelta();
		
		//float sinTemp = (float) Math.sin(temp);
		float cosTemp = (float) Math.cos(temp);
		
		
		
		
		/**///transform.setTranslation(0, -1, 0);//Position
		
		
		
		//transform2.setRotation(0, sinTemp * 180, 0);//Rotation
		//transform.setScale(0.7f * sinTemp, 0.7f * sinTemp, 0.7f * sinTemp);//Size
		
		
		
		//pLight2.setPosition(new Vector3f(0 + (8.0f * (float) (cosTemp + 1.0 / 2.0)), -0.49f, 0 + (8.0f * (float) (cosTemp + 1.0 / 2.0))));
		
		float yPos = floor.getPosition().getY() + -10;//0.403f;
		
		pLight2.setPosition(new Vector3f(0, yPos, 0));
		pLight1.setPosition(new Vector3f((8.0f * cosTemp) - pLight2.getPosition().getX(), yPos, 0 - pLight2.getPosition().getZ()).mult(new Vector3f(-1, 1, -1)));
		pLight3.setPosition(new Vector3f(0 - pLight2.getPosition().getX(), yPos, (8.0f * cosTemp) - pLight2.getPosition().getZ()).mult(new Vector3f(-1, 1, -1)));
		
		
		sLight1.getPointLight().setPosition(camera.getPos());
		sLight1.setDirection(camera.getForward());
		sLight1.setVisible(camera.isFlashLightOn());
		sLight1.getPointLight().setRange(sLight1.isVisible() ? sLight1.getRange() : 0.0f);
		
		this.updateTextWindow();
	}

	public void render() {
		RenderUtil.setClearColor(this.day ? dayskycolor : nightskycolor);
		//RenderUtil.setClearColor(this.getCamera().getPos().div(2048f));
		//RenderUtil.setClearColor(Transform.getCamera().getPos().div(2048f).abs());// TODO Void color gets set here!//RenderUtil.setClearColor(new Vector3f(0, 0, 0));
		/**///shader.bind();
		/**///shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), floor);
		for(Object3D curObject : objectsToRender) {
			if(curObject.timeUntilRemove == 0) {
				objectsToRender.remove(curObject);
				break;
			}
		}
		for(Object3D curObject : objectsToRender) {
			curObject.render();//Renders the current object3D!
		}
		//this.updateFloor();
		/**///mesh.draw();
	}

	/**
	 * @return the window
	 */
	public TextWindow getTextWindow() {
		return debuggingWindow;
	}

	/**
	 * @param debuggingWindow the window to set
	 */
	public void setTextWindow(TextWindow debuggingWindow) {
		this.debuggingWindow = debuggingWindow;
	}

	public void updateTextWindow() {
		final Game game = this;
		if(this.textWindowThread == null && this.useTextWindow) {
			this.textWindowThread = new Thread(new Runnable() {
				@Override
				public void run() {
					while(game.running && game.getTextWindow().isShowing()) {
						game.getTextWindow().setText("Version: " + Game.VERSION + ";\n\n" + (Game.showCurrentFrameRate ? "Current Frame count: " + game.curFPS + ";\n" : "") + "FPS: " + game.fps + "\n\nCamera mode: \"" + Game.CAMERA_MODE + "\";\nKey-based camera rotation: " + Game.allow_key_panning + ";\nHorizon lock: " + Game.horizonLock + ";\nCamera position: " + game.getCamera().getPos().toString() + ";\nCamera rotation(pitch, yaw, roll): " + camera.getLookVector().toString() + ";\nVertical camera key movement: " + Game.allow_vertical_key_travel + ";\nCamera flashlight active: " + camera.isFlashLightOn() + ";\n");
						try {
							Thread.sleep(10);/*If this thread doesn't wait at least 5 milliseconds in between updates,
							then the computer will update this text frame faster than the current monitor's
							refresh rate, causing computer lag, especially if there are constantly changing
							values(like the position of the camera)!*/
						} catch (InterruptedException e) {
							
						}
					}
				}
			});
			textWindowThread.start();
		}
	}

	/**
	 * Waits the specified amount of milliseconds, then runs the given task.
	 * @param task
	 * @param milliseconds
	 */
	public void runTaskLater(final Runnable task, final long milliseconds) {
		try{
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(milliseconds);
						task.run();
					} catch (InterruptedException e) {
						
					}
				}
				
			}).start();
		} catch(Exception e) {
			e.printStackTrace();
			print("An error occurred while trying to perform a task! Please see the above stack trace for more information.");
		}
	}

	/**
	 * @return the camera
	 */
	public Camera getCamera() {
		return this.camera;
	}

	/**
	 * @param camera the camera to set
	 */
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
}
