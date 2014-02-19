package com.gmail.br45entei.base.engine;

public class Camera {
	public static final Vector3f yAxis = new Vector3f(0, 1, 0);

	private boolean flashLight_power = false;
	
	private Game game;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	private Vector3f pos;
	private Vector3f forward;
	private Vector3f up;
	private float FOV = 70.0f;
	private final float defaultFOV = 70.0f;
	private final float zoomInFOV = 20.0f;

	public float getFOV() {
		return this.FOV;
	}

	public void setFOV(float FOV) {
		this.FOV = FOV;
	}

	public Camera(Game game) {
		this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 1), new Vector3f(0, 1, 0));
		this.game = game;
	}
	
	public Camera(Vector3f pos, Vector3f forward, Vector3f up) {
		this.pos = pos;
		this.forward = forward.normalize();
		this.up = up.normalize();
	}

	private float previous_Mouse_sensitivity = 0.5f;
	public float mouse_sensitivity = 0.5f;
	protected boolean mouseLocked = false;
	Vector2f centerPosition = new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2);


	@SuppressWarnings("boxing")
	private Object[] nearestObject3D = new Object[] {(double) 0, null};
	private boolean[] debounce = new boolean[] {false, false, false, false};

	@SuppressWarnings("boxing")
	public Object[] getNearestObject3D() {
		Object3D objectToEdit = null;
		double dist = Double.MAX_VALUE;
		for(Object3D curObject : this.getGame().getObjectsToRender()) {
			double distance = curObject.getPosition().getDistanceBetweenVector3f(curObject.camera.getPos());
			if(distance < dist) {
				dist = distance;
				objectToEdit = curObject;
			}
		}
		return new Object[] {
			dist,
			objectToEdit
		};
	}

	private void editSelectedPart() {
		//Fun with the Transform
		if(Input.getKeyDown(Input.KEY_DELETE)) {
			if(debounce[2] == false) {
				debounce[2] = true;
				if(nearestObject3D[1] != null) {
					if(((Object3D) nearestObject3D[1]).isProtected() == false) {
						((Object3D) nearestObject3D[1]).timeUntilRemove = 0;//this.getObjectsToRender().remove(nearestObject3D[1]);//Remove the recently edited object3D
						nearestObject3D = new Object[] {nearestObject3D[0], null};
						//nearestObject3D = this.getNearestObject3D();
						//if(nearestObject3D[1] != null) {
						//	Game.print("The distance between the camera and the nearest object(named \"" + ((Object3D) nearestObject3D[1]).getName() + "\") is: " + nearestObject3D[0]);
						//}
					} else {
						//Game.print("You cannot delete the protected object \"" + ((Object3D) nearestObject3D[1]).getName() + "\"!");
					}
				}
				debounce[2] = false;
			}
		}
		if(Input.getMouseUp(0)) {//If the user clicked the left mouse button, then:
			if(debounce[0] == false) {
				debounce[0] = true;
				nearestObject3D = this.getNearestObject3D();
				if(nearestObject3D[1] != null) {
					//Game.print("The distance between the camera and the selected object(named \"" + ((Object3D) nearestObject3D[1]).getName() + "\") is: " + nearestObject3D[0]);
				}
				debounce[0] = false;
			}
			
		} else if(Input.getMouseUp(1)) {//If the user clicked the right mouse button, then:
			if(debounce[1] == false) {
				debounce[1] = true;
				this.getGame().createObject(new String[] {
						"RightClickPart No. " + (this.getGame().getObjectsToRender().size() - 2),
						"Cube",
						"Wooden_Crate_Transparent",//"009066822120lg",//"Stone",
						this.getPos().getX() + "",
						this.getPos().getY() + "",
						this.getPos().getZ() + ""
				}, this.getLookVector());
				
				
				//EDIT: To get a ray from point A in the direction of point B you can use point A as origin and pointB.sub(pointA).normalize() as the direction.
				
				
				nearestObject3D = this.getNearestObject3D();
				if(nearestObject3D[1] != null) {
					//Game.print("The distance between the camera and the selected object named \"" + ((Object3D) nearestObject3D[1]).getName() + "\" is: " + nearestObject3D[0]);
				}
				debounce[1] = false;
			}
		}
		Object3D objectToEdit = (Object3D) nearestObject3D[1];
		float editIncrement = 0.0001f;
		if(Input.getMouse(3)) {//left back button
			objectToEdit.offset += 0.00001f;
			objectToEdit.setPosition(objectToEdit.getPosition());
			Game.print("New object3D.offset: " + objectToEdit.offset);
		}
		if(Input.getMouse(4)) {//right forward button
			objectToEdit.offset -= 0.00001f;
			objectToEdit.setPosition(objectToEdit.getPosition());
			Game.print("New object3D.offset: " + objectToEdit.offset);
		}
		
		if(objectToEdit != null) {
			Vector3f oldRotation = objectToEdit.getRotation();
			if(Input.getKey(Input.KEY_U)) {
				objectToEdit.setRotation(oldRotation.getX(), oldRotation.getY() + editIncrement, oldRotation.getZ());
			}
			if(Input.getKey(Input.KEY_I)) {
				objectToEdit.setRotation(oldRotation.getX(), oldRotation.getY() - editIncrement, oldRotation.getZ());
			}
			if(Input.getKey(Input.KEY_J)) {
				objectToEdit.setRotation(oldRotation.getX() + editIncrement, oldRotation.getY(), oldRotation.getZ());
			}
			if(Input.getKey(Input.KEY_K)) {
				objectToEdit.setRotation(oldRotation.getX() - editIncrement, oldRotation.getY(), oldRotation.getZ());
			}
			if(Input.getKey(Input.KEY_N)) {
				objectToEdit.setRotation(oldRotation.getX(), oldRotation.getY(), oldRotation.getZ() + editIncrement);
			}
			if(Input.getKey(Input.KEY_M)) {
				objectToEdit.setRotation(oldRotation.getX(), oldRotation.getY(), oldRotation.getZ() - editIncrement);
			}
			if(Input.getKey(Input.KEY_O)) {
				objectToEdit.setRotation(0, 0, 0);
			}
	
			//Size Manipulation
			if(Input.getKey(Input.KEY_V)) {
				objectToEdit.setSize(objectToEdit.getSize().sub(editIncrement));
			}
			if(Input.getKey(Input.KEY_B)) {
				objectToEdit.setSize(objectToEdit.getSize().add(editIncrement));
			}
			if(Input.getKey(Input.KEY_C)) {
				objectToEdit.setSize(objectToEdit.defaultSize);
			}

			//long waitTime = 40; //A stupid idea...
			//Position Manipulation
			if(debounce[3] == false) {
				debounce[3] = true;
				if(Input.getKey(Input.KEY_NUMPAD6)) {
					objectToEdit.setPosition(objectToEdit.getPosition().add(new Vector3f(editIncrement, 0, 0)));
					//try {Thread.sleep(waitTime);} catch (InterruptedException e) {}
				}
				if(Input.getKey(Input.KEY_NUMPAD4)) {
					objectToEdit.setPosition(objectToEdit.getPosition().add(new Vector3f(-editIncrement, 0, 0)));
					//try {Thread.sleep(waitTime);} catch (InterruptedException e) {}
				}
				if(Input.getKey(Input.KEY_NUMPAD8)) {
					objectToEdit.setPosition(objectToEdit.getPosition().add(new Vector3f(0, 0, editIncrement)));
					//try {Thread.sleep(waitTime);} catch (InterruptedException e) {}
				}
				if(Input.getKey(Input.KEY_NUMPAD2)) {
					objectToEdit.setPosition(objectToEdit.getPosition().add(new Vector3f(0, 0, -editIncrement)));
					//try {Thread.sleep(waitTime);} catch (InterruptedException e) {}
				}
				if(Input.getKey(Input.KEY_ADD)) {
					objectToEdit.setPosition(objectToEdit.getPosition().add(new Vector3f(0, editIncrement, 0)));
					//try {Thread.sleep(waitTime);} catch (InterruptedException e) {}
				}
				if(Input.getKey(Input.KEY_MINUS)) {
					objectToEdit.setPosition(objectToEdit.getPosition().add(new Vector3f(0, -editIncrement, 0)));
					//try {Thread.sleep(waitTime);} catch (InterruptedException e) {}
				}
				if(Input.getKey(Input.KEY_NUMPAD5)) {
					//print("Numpad5");
					objectToEdit.setPosition(objectToEdit.defaultPosition);
				}
				
				debounce[3] = false;
			}
		}
		//End of Fun with the Transform
	}

	public void input() {
		if(Input.getKey(Input.KEY_ESCAPE)) {
			Input.setCursor(true);
			mouseLocked = false;
		}
		if(Input.getMouseDown(0) || Input.getKeyDown(Input.KEY_ESCAPE)) {
			Input.setMousePosition(centerPosition);
			Input.setCursor(false);
			mouseLocked = true;
		}
		
		if(mouseLocked) {
			if(Input.getKeyDown(Input.KEY_E)) {
				Window.setFullScreen(!Window.isFullScreen());
			}
			float movAmt = (float)(10 * Time.getDelta());
			float rotAmt = (float)(100 * Time.getDelta());
			
			if(Input.getKeyDown(Input.KEY_Q)) {
				this.setFlashLightOn(!this.flashLight_power);
			}
			if(Input.getKeyDown(Input.KEY_P)) {
				Game.allow_key_panning = !Game.allow_key_panning;
			}
			
			if(Input.getKeyDown(Input.KEY_X)) {
				this.getGame().day = !this.getGame().day;
			}
			if(Input.getKeyDown(Input.KEY_Z)) {
				this.setFOV(this.zoomInFOV);
				this.previous_Mouse_sensitivity = this.mouse_sensitivity;
				this.mouse_sensitivity = 0.1f;
			}
			if(Input.getKeyUp(Input.KEY_Z)) {
				this.setFOV(this.defaultFOV);
				this.mouse_sensitivity = this.previous_Mouse_sensitivity;
			}
			
			if(Game.allow_vertical_key_travel) {
				if(Input.getKey(Input.KEY_SPACE)) {
					this.setPos(new Vector3f(this.pos.getX(), this.pos.getY() + 0.001f, this.pos.getZ()));
				}
				if(Input.getKey(Input.KEY_LSHIFT)) {
					this.setPos(new Vector3f(this.pos.getX(), this.pos.getY() - 0.001f, this.pos.getZ()));
				}
			}
			
			if(Game.allowHorizonLockToggle) {
				if(Input.getKeyDown(Input.KEY_H)) {
					Game.horizonLock = !Game.horizonLock;//lol, so simple...
					System.out.println("Toggled \"Game.horizonLock\" to: " + Game.horizonLock);
				}
			}
			
			if(Game.allow_toggle_camera_mode) {
				if(Input.getKeyDown(Input.KEY_T)) {
					Game.CAMERA_MODE = (Game.CAMERA_MODE == "NORMAL" ? "FREESTYLE" : "NORMAL");
					if(Game.CAMERA_MODE.equalsIgnoreCase("normal")) {
						Game.allow_vertical_key_travel = true;
					} else if(Game.CAMERA_MODE.equalsIgnoreCase("freestyle")) {
						Game.allow_vertical_key_travel = false;
					}
				}
			}
			
			if(Game.allow_pos_reset) {
				if(Input.getKeyDown(Input.KEY_R)) {
					this.setPos(new Vector3f(0, 0, 0));
					this.forward = new Vector3f(0, 0, 1);
					this.up = new Vector3f(0, 1, 0);
				}
			}
			
			if(Input.getKey(Input.KEY_W)) {
				move(getForward(), movAmt, "Forward");
			}
			if(Input.getKey(Input.KEY_S)) {
				move(getForward(), -movAmt, "Backward");
			}
			if(Input.getKey(Input.KEY_A)) {
				move(getLeft(), movAmt, "Left");
			}
			if(Input.getKey(Input.KEY_D)) {
				move(getRight(), movAmt, "Right");
			}
			
			//if(mouseLocked) {
				Vector2f deltaPos = Input.getMousePosition().sub(centerPosition);
				
				boolean rotY = deltaPos.getX() != 0;
				boolean rotX = deltaPos.getY() != 0;
				
				if(rotY) {
					rotateY(deltaPos.getX() * mouse_sensitivity);
				}
				if(rotX) {
					rotateX(-deltaPos.getY() * mouse_sensitivity);
				}
				if(rotY || rotX) {
					Input.setMousePosition(new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2));
				}
			//}
			
			if(Game.allow_key_panning) {
				if(Input.getKey(Input.KEY_UP)) {
					rotateX(-rotAmt);
				}
				if(Input.getKey(Input.KEY_DOWN)) {
					rotateX(rotAmt);
				}
				if(Input.getKey(Input.KEY_LEFT)) {
					rotateY(-rotAmt);
				}
				if(Input.getKey(Input.KEY_RIGHT)) {
					rotateY(rotAmt);
				}
			}
			if(this.getGame().allowObject3DEditing) {
				this.editSelectedPart();
			}
		}
	}
	
	private float getNewAmount(String direction, float amt) {
		if(Input.getKey(Input.KEY_LSHIFT) && Input.getKey(Input.KEY_SPACE)) {
			amt = amt / 10;
		}
		if(direction.equalsIgnoreCase("forward")) {
			if(Input.getKey(Input.KEY_LCONTROL)) {
				//Game.print("Left control is down.");
				
				if(Game.CAMERA_MODE.equals("FREESTYLE")) {
					if(Input.getKey(Input.KEY_S) == false) {
						//Game.print("\"S\" is not down.");
						amt = amt * 10;
					} else {
						//Game.print("\"S\" is down while left control and \"W\" is down.");
					}
				} else {
					if(Input.getKey(Input.KEY_LSHIFT) == false && Input.getKey(Input.KEY_S) == false) {
						//Game.print("Left shift is not down.\n\"S\" is not down.");
						amt = amt * 10;
					} else {
						//Game.print("Left shift is down while left control is down.");
					}
				}
			}
		}
		return amt;
	}
	
	public Vector3f getLookVector() {
		////Vector3f pointB = this.pos.add(getForward().toDegrees()); //.mult(100));
		////Vector3f lookVector = pointB.sub(this.pos).normalize();
		//float oldX = lookVector.getX();
		//float oldY = lookVector.getY();
		
		//lookVector.setX(oldY);
		//lookVector.setY(oldX);
		//lookVector.setZ(0);//Tilt
		
		
		
		Vector3f lookVector = this.getForward().normalize().toDegrees();
		
		//Game.print(lookVector.toDegrees().toString());
		return lookVector.toDegrees();
	}
	
	public void move(Vector3f dir, float amt, String direction) {
		amt = getNewAmount(direction, amt);
		Vector3f moveBy = this.pos.add(dir.mult(amt));
		
		if(direction.equalsIgnoreCase("forward") || direction.equalsIgnoreCase("backward") || direction.equalsIgnoreCase("left") || direction.equalsIgnoreCase("right")) {
			this.setPos(Game.CAMERA_MODE.equalsIgnoreCase("normal") ? new Vector3f(moveBy.getX(), this.pos.getY(), moveBy.getZ()) : (Game.CAMERA_MODE.equalsIgnoreCase("freestyle") ? moveBy : this.pos));
		} else {
			this.setPos(moveBy);
		}
	}
	
	public void rotateY(float angle) {
		Vector3f Haxis = yAxis.cross(forward).normalize();

		Vector3f newForward = this.forward.rotate(angle, yAxis).normalize();//this.setForward(this.forward.rotate(angle, yAxis).normalize());

		Vector3f newUp = this.forward.cross(Haxis).normalize();//this.up = this.forward.cross(Haxis).normalize();

		handleXAndY(newUp, newForward);
	}
	
	public void rotateX(float angle) {
		Vector3f oldForward = this.forward;
		
		Vector3f Haxis = yAxis.cross(oldForward).normalize();//Vector3f Haxis = yAxis.cross(this.forward).normalize();

		Vector3f newForward = oldForward.rotate(angle, Haxis).normalize();//this.setForward(this.forward.rotate(angle, Haxis).normalize());

		Vector3f newUp = newForward.cross(Haxis).normalize();//this.up = this.forward.cross(Haxis).normalize();

		if(checkGameOptions(newUp, newForward)) {
			return;
		}

		handleXAndY(newUp, newForward);
	}
	
	private boolean checkGameOptions(Vector3f newUp, Vector3f newForward) {
		if(Game.horizonLock == true) {
			if(newForward.getY() >= 0.90 && newForward.getY() <= -0.90) {
				this.setForward(newForward);
				this.up = newUp;
			} else {
				//System.out.println("Horizon lock is turned on; cannot change the yaw.");
			}
			return true;
		}
		return false;
	}
	
	//@SuppressWarnings("boxing")
	private void handleXAndY(Vector3f newUp, Vector3f newForward) {
		//Game.print(Game.cameraYawLimit);
		if((newForward.getY() >= -Game.cameraYawLimit) && (newForward.getY() <= Game.cameraYawLimit)) {
			this.setForward(newForward);
			this.up = newUp;
		} else {
			//System.out.println("Not setting the camera's yaw(forward) because the new yaw's Y value was either too large or too small(valid values between -0.99 and 0.99 only.");
		}
	}
	
	public Vector3f getLeft() {
		return this.forward.cross(up).normalize();
	}
	
	public Vector3f getRight() {
		return this.up.cross(forward).normalize();
	}
	
	public Vector3f getPos() {
		return this.pos;
	}

	public void setPos(Vector3f pos) {
		if(pos.getY() <= Game.limitCameraYPos) {
			if(Game.allowCameraLimitPos) {
				this.pos = new Vector3f(pos.getX(), Game.limitCameraYPos, pos.getZ());
			} else {
				this.pos = pos;
			}
		} else {
			this.pos = pos;
		}
	}

	public Vector3f getForward() {
		return this.forward;
	}

	public void setForward(Vector3f forward) {
		this.forward = forward;
	}

	public Vector3f getUp() {
		return this.up;
	}

	public void setUp(Vector3f up) {
		this.up = up;
	}

	/**
	 * @return the flashLight_power
	 */
	public boolean isFlashLightOn() {
		return this.flashLight_power;
	}

	/**
	 * @param flashLight_power the flashLight_power to set
	 */
	public void setFlashLightOn(boolean flashLight_power) {
		this.flashLight_power = flashLight_power;
	}
}
