/**
 * 
 */
package com.gmail.br45entei.base.engine;

/**
 * @author Brian_Entei
 *
 */
public class ShapeClass {

	private float fieldDepth = 1.0f;
	private float fieldWidth = 1.0f;
	private float fieldHeight = 1.0f;


	private float widthOffset = 2.35f + (0.14120181f / 10.19f);
	private float heightOffset = 2.35f;// - 0.12910156f;
	private float depthOffset = 2.35f;// - 0.010599989f;

	public float getDepthOffset() {
		return depthOffset;
	}

	public void setDepthOffset(float depthOffset) {
		this.depthOffset = depthOffset;
	}

	private Vector3f fieldOffset = new Vector3f(widthOffset, heightOffset, depthOffset);


	private float fieldMultiplier = 5.7142857142857142857142857142857f;

	//										args[0],  args[1],		args[2],	args[3]		args[4]
	protected final int[] ShapeSideAmounts = {1, 6, 1, 1, 2};
	protected final String[] ShapeTypes = {"Square", "Cube", "TriangularPrism", "Sphere", "Square_2"};

	public int getNumberOfShapeSides(String shape) {
		int rtrn = -1;
		int i = 0;
		for(String curShapeType : ShapeTypes) {
			if(curShapeType.equalsIgnoreCase(shape)) {
				rtrn = ShapeSideAmounts[i];
			}
			i++;
		}
		return rtrn;
	}

	private static final ShapeClass instance = new ShapeClass();

	public static ShapeClass getInstance() {
		return instance;
	}

	public boolean checkIfStringIsValidShape(String shape) {
		boolean rtrn = false;
		for(String curShape : this.ShapeTypes) {
			if(curShape.equalsIgnoreCase(shape)) {//.contains(
				rtrn = true;
				break;
			}
		}
		return rtrn;
	}

	public ShapeClass() {
		//
	}

	/** A blank mesh. */
	public Object[] Blank = new Object[] {
		new Vertex[] {
			new Vertex(new Vector3f(0.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f)),
			new Vertex(new Vector3f(0.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f)),
			new Vertex(new Vector3f(0.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f)),
			new Vertex(new Vector3f(0.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f))
		},
		new int[] {
			0, 0, 0,
			0, 0, 0
		}
	};

	/**
	 * A flat 2-dimensional surface with a top only.
	 */
	public Object[] Floor2D = new Object[] {
		new Vertex[] {
			new Vertex( new Vector3f(-this.fieldWidth, 0.0f, -this.fieldDepth).sub(new Vector3f(fieldOffset.getX(), 0.0f, fieldOffset.getZ())), new Vector2f(0.0f, 0.0f)),
			new Vertex( new Vector3f(-this.fieldWidth, 0.0f, this.fieldDepth * this.fieldMultiplier).sub(new Vector3f(fieldOffset.getX(), 0.0f, fieldOffset.getZ())), new Vector2f(0.0f, 1.0f)),
			new Vertex( new Vector3f(this.fieldWidth * this.fieldMultiplier, 0.0f, -this.fieldDepth).sub(new Vector3f(fieldOffset.getX(), 0.0f, fieldOffset.getZ())), new Vector2f(1.0f, 0.0f)),
			new Vertex( new Vector3f(this.fieldWidth * this.fieldMultiplier, 0.0f, this.fieldDepth * this.fieldMultiplier).sub(new Vector3f(fieldOffset.getX(), 0.0f, fieldOffset.getZ())), new Vector2f(1.0f, 1.0f))
		},
		new int[] {
			0, 1, 2,
			2, 1, 3
		}
	};

	/**
	 * A flat 2-dimensional surface with a top and a bottom.
	 */
	public Object[] Surface2D = new Object[] {
		new Vertex[] {
			new Vertex( new Vector3f(-this.fieldWidth, 0.0f, -this.fieldDepth).sub(fieldOffset), new Vector2f(0.0f, 0.0f)),
			new Vertex( new Vector3f(-this.fieldWidth, 0.0f, this.fieldDepth * this.fieldMultiplier).sub(fieldOffset), new Vector2f(0.0f, 1.0f)),
			new Vertex( new Vector3f(this.fieldWidth * this.fieldMultiplier, 0.0f, -this.fieldDepth).sub(fieldOffset), new Vector2f(1.0f, 0.0f)),
			new Vertex( new Vector3f(this.fieldWidth * this.fieldMultiplier, 0.0f, this.fieldDepth * this.fieldMultiplier).sub(fieldOffset), new Vector2f(1.0f, 1.0f))
		},
		new int[] {
			0, 1, 2,
			2, 1, 3,
			1, 0, 2,
			2, 3, 1
		}
	};
	
	/** * The top side of a 3-dimensional object with six faces, each of which have congruent surface areas. Note that the surface area can be changed later by changing the size of this object.*/

	private Object[] CubeTop2D = new Object[] {
			/*new Vertex[] {
				new Vertex(new Vector3f(-this.fieldWidth, -this.fieldHeight, -this.fieldDepth), new Vector2f(0.0f, 0.0f)),
				
				new Vertex( new Vector3f(-this.fieldWidth, this.fieldHeight * this.fieldMultiplier, this.fieldDepth * this.fieldMultiplier), new Vector2f(0.0f, 1.0f)),
				
				//new Vertex( new Vector3f(-this.fieldWidth, -this.fieldHeight, this.fieldDepth * this.fieldMultiplier), new Vector2f(0.0f, 1.0f)),
				
				//new Vertex( new Vector3f(this.fieldWidth * this.fieldMultiplier, -this.fieldHeight, -this.fieldDepth), new Vector2f(1.0f, 0.0f)),
				
				new Vertex( new Vector3f(this.fieldWidth * this.fieldMultiplier, this.fieldHeight * this.fieldMultiplier, -this.fieldDepth), new Vector2f(1.0f, 0.0f)),
				
				new Vertex( new Vector3f(this.fieldWidth * this.fieldMultiplier, this.fieldHeight * this.fieldMultiplier, this.fieldDepth * this.fieldMultiplier), new Vector2f(1.0f, 1.0f))
			}*/
			new Vertex[] {
				new Vertex(new Vector3f(-this.fieldWidth, this.fieldHeight * this.fieldMultiplier, -this.fieldDepth).sub(fieldOffset), new Vector2f(0.0f, 0.0f)),
				///*3*/new Vertex(new Vector3f(this.fieldWidth * this.fieldMultiplier, -this.fieldHeight, -this.fieldDepth), new Vector2f(1.0f, 0.0f)),
				
				///*2*/new Vertex(new Vector3f(-this.fieldWidth, -this.fieldHeight, this.fieldDepth * this.fieldMultiplier), new Vector2f(0.0f, 1.0f)),
				
				
				
				/*1*/new Vertex(new Vector3f(-this.fieldWidth, this.fieldHeight * this.fieldMultiplier, this.fieldDepth * this.fieldMultiplier).sub(fieldOffset), new Vector2f(0.0f, 1.0f)),
				/*4*/new Vertex(new Vector3f(this.fieldWidth * this.fieldMultiplier, this.fieldHeight * this.fieldMultiplier, -this.fieldDepth).sub(fieldOffset), new Vector2f(1.0f, 0.0f)),
				
				new Vertex(new Vector3f(this.fieldWidth * this.fieldMultiplier, this.fieldHeight * this.fieldMultiplier, this.fieldDepth * this.fieldMultiplier).sub(fieldOffset), new Vector2f(1.0f, 1.0f))
			},
			new int[] {
				0, 1, 2,
				2, 1, 3
			}
		};

	/** * The top side of a 3-dimensional object with six faces, each of which have congruent surface areas. Note that the surface area can be changed later by changing the size of this object.*/
	private Object[] CubeBottom2D = new Object[] {
		new Vertex[] {
			new Vertex( new Vector3f(-this.fieldWidth, -this.fieldHeight, -this.fieldDepth).sub(fieldOffset), new Vector2f(0.0f, 0.0f)),
			//new Vertex( new Vector3f(-this.fieldWidth, 0.0f, this.fieldDepth * this.fieldMultiplier), new Vector2f(0.0f, 1.0f)),
			//new Vertex( new Vector3f(this.fieldWidth * this.fieldMultiplier, 0.0f, -this.fieldDepth), new Vector2f(1.0f, 0.0f)),
			
			//4
			new Vertex(new Vector3f(this.fieldWidth * this.fieldMultiplier, -this.fieldHeight, -this.fieldDepth).sub(fieldOffset), new Vector2f(1.0f, 0.0f)),
			
			//1
			new Vertex(new Vector3f(-this.fieldWidth, -this.fieldHeight, this.fieldDepth * this.fieldMultiplier).sub(fieldOffset), new Vector2f(0.0f, 1.0f)),
			
			new Vertex( new Vector3f(this.fieldWidth * this.fieldMultiplier, -this.fieldHeight, this.fieldDepth * this.fieldMultiplier).sub(fieldOffset), new Vector2f(1.0f, 1.0f))
		},
		new int[] {
			0, 1, 2,
			2, 1, 3
		}
	};

	/** * The front side of a 3-dimensional object with six faces, each of which have congruent surface areas. Note that the surface area can be changed later by changing the size of this object.*/
	private Object[] CubeFront2D = new Object[] {
		new Vertex[] {
			new Vertex( new Vector3f(this.fieldWidth * this.fieldMultiplier, this.fieldHeight * this.fieldMultiplier, -this.fieldDepth).sub(fieldOffset), new Vector2f(1.0f, 0.0f)),//Top Right
			//new Vertex( new Vector3f(-this.fieldWidth, 0.0f, this.fieldDepth * this.fieldMultiplier), new Vector2f(0.0f, 1.0f)),
			//new Vertex( new Vector3f(this.fieldWidth * this.fieldMultiplier, 0.0f, -this.fieldDepth), new Vector2f(1.0f, 0.0f)),

			//1
			new Vertex(new Vector3f(this.fieldWidth * this.fieldMultiplier, -this.fieldHeight, -this.fieldDepth).sub(fieldOffset), new Vector2f(0.0f, 1.0f)),//Bottom Right

			//4
			new Vertex(new Vector3f(-this.fieldWidth, (this.fieldHeight * this.fieldMultiplier), -this.fieldDepth).sub(fieldOffset), new Vector2f(1.0f, 0.0f)),//Top Left
			
			new Vertex( new Vector3f(-this.fieldWidth, -this.fieldHeight, -this.fieldDepth).sub(fieldOffset), new Vector2f(0.0f, 1.0f))//Bottom left
		},
		new int[] {
			0, 1, 2,
			2, 1, 3
		}
	};

	/** * The back side of a 3-dimensional object with six faces, each of which have congruent surface areas. Note that the surface area can be changed later by changing the size of this object.*/
	private Object[] CubeBack2D = new Object[] {
		new Vertex[] {
			new Vertex( new Vector3f(this.fieldWidth * this.fieldMultiplier, this.fieldHeight * this.fieldMultiplier, this.fieldDepth * this.fieldMultiplier).sub(fieldOffset), new Vector2f(1.0f, 0.0f)),//Top Right
			//new Vertex( new Vector3f(-this.fieldWidth, 0.0f, this.fieldDepth * this.fieldMultiplier), new Vector2f(0.0f, 1.0f)),
			//new Vertex( new Vector3f(this.fieldWidth * this.fieldMultiplier, 0.0f, -this.fieldDepth), new Vector2f(1.0f, 0.0f)),

			//4
			new Vertex(new Vector3f(-this.fieldWidth, (this.fieldHeight * this.fieldMultiplier), this.fieldDepth * this.fieldMultiplier).sub(fieldOffset), new Vector2f(1.0f, 0.0f)),//Top Left

			//1
			new Vertex(new Vector3f(this.fieldWidth * this.fieldMultiplier, -this.fieldHeight, this.fieldDepth * this.fieldMultiplier).sub(fieldOffset), new Vector2f(0.0f, 1.0f)),//Bottom Right
			
			new Vertex( new Vector3f(-this.fieldWidth, -this.fieldHeight, this.fieldDepth * this.fieldMultiplier).sub(fieldOffset), new Vector2f(0.0f, 1.0f))//Bottom left
		},
		new int[] {
			0, 1, 2,
			2, 1, 3
		}
	};

	/** * The left side of a 3-dimensional object with six faces, each of which have congruent surface areas. Note that the surface area can be changed later by changing the size of this object.*/
	private Object[] CubeLeft2D = new Object[] {
		new Vertex[] {
			new Vertex( new Vector3f(-this.fieldWidth, this.fieldHeight * this.fieldMultiplier, -this.fieldDepth).sub(fieldOffset), new Vector2f(1.0f, 0.0f)),//Top Right

			//1
			new Vertex(new Vector3f(-this.fieldWidth, -this.fieldHeight, -this.fieldDepth).sub(fieldOffset), new Vector2f(0.0f, 1.0f)),//Bottom Right

			//4
			new Vertex(new Vector3f(-this.fieldWidth, this.fieldHeight * this.fieldMultiplier, this.fieldDepth * this.fieldMultiplier).sub(fieldOffset), new Vector2f(1.0f, 0.0f)),//Top Left
			new Vertex( new Vector3f(-this.fieldWidth, -this.fieldHeight, this.fieldDepth * this.fieldMultiplier).sub(fieldOffset), new Vector2f(0.0f, 1.0f))//Bottom left
		},
		new int[] {
			0, 1, 2,
			2, 1, 3,
		}
	};

	/** * The right side of a 3-dimensional object with six faces, each of which have congruent surface areas. Note that the surface area can be changed later by changing the size of this object.*/
	private Object[] CubeRight2D = new Object[] {
		new Vertex[] {
			new Vertex( new Vector3f(this.fieldWidth * this.fieldMultiplier, this.fieldHeight * this.fieldMultiplier, -this.fieldDepth).sub(fieldOffset), new Vector2f(1.0f, 0.0f)),//Top Right

			//4
			new Vertex(new Vector3f(this.fieldWidth * this.fieldMultiplier, this.fieldHeight * this.fieldMultiplier, this.fieldDepth * this.fieldMultiplier).sub(fieldOffset), new Vector2f(1.0f, 0.0f)),//Top Left

			//1
			new Vertex(new Vector3f(this.fieldWidth * this.fieldMultiplier, -this.fieldHeight, -this.fieldDepth).sub(fieldOffset), new Vector2f(0.0f, 1.0f)),//Bottom Right
			new Vertex( new Vector3f(this.fieldWidth * this.fieldMultiplier, -this.fieldHeight, this.fieldDepth * this.fieldMultiplier).sub(fieldOffset), new Vector2f(0.0f, 1.0f))//Bottom left
		},
		new int[] {
			0, 1, 2,
			2, 1, 3,
		}
	};

	public Object[] Cube3D = new Object[] {
		this.CubeTop2D,
		this.CubeBottom2D,
		this.CubeFront2D,
		this.CubeBack2D,
		this.CubeLeft2D,
		this.CubeRight2D
	};


	public Object[] Triangular_Prism = new Object[] {
		new Vertex[] {
			new Vertex( new Vector3f(-1.0f, -1.0f, 0.5773f),  new Vector2f(0.0f, 0.0f)),
			new Vertex( new Vector3f(0.0f, -1.0f, -1.15475f), new Vector2f(0.5f, 0.0f)),
			new Vertex( new Vector3f(1.0f, -1.0f, 0.5773f),   new Vector2f(1.0f, 0.0f)),
			new Vertex( new Vector3f(0.0f, 1.0f, 0.0f), 	  new Vector2f(0.5f, 1.0f))
		},
		new int[] {
			0, 3, 1,
			1, 3, 2,
			2, 3, 0,
			1, 2, 0
		}
	};

	private void updateMeshes() {
		
		
		CubeTop2D = new Object[] {
			new Vertex[] {
				new Vertex(new Vector3f(-this.fieldWidth, this.fieldHeight * this.fieldMultiplier, -this.fieldDepth).sub(fieldOffset), new Vector2f(0.0f, 0.0f)),
	
				/*1*/new Vertex(new Vector3f(-this.fieldWidth, this.fieldHeight * this.fieldMultiplier, this.fieldDepth * this.fieldMultiplier).sub(fieldOffset), new Vector2f(0.0f, 1.0f)),
				/*4*/new Vertex(new Vector3f(this.fieldWidth * this.fieldMultiplier, this.fieldHeight * this.fieldMultiplier, -this.fieldDepth).sub(fieldOffset), new Vector2f(1.0f, 0.0f)),
	
				new Vertex(new Vector3f(this.fieldWidth * this.fieldMultiplier, this.fieldHeight * this.fieldMultiplier, this.fieldDepth * this.fieldMultiplier).sub(fieldOffset), new Vector2f(1.0f, 1.0f))
			},
			new int[] {
				0, 1, 2,
				2, 1, 3
			}
		};
		
		
		
		CubeBottom2D = CubeTop2D;
		CubeFront2D = CubeTop2D;
		CubeBack2D = CubeTop2D;
		CubeLeft2D = CubeTop2D;
		CubeRight2D = CubeTop2D;
		
		
		
		
		Floor2D = new Object[] {
			new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
				new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * this.fieldMultiplier), new Vector2f(0.0f, 1.0f)),
				new Vertex( new Vector3f(fieldWidth * this.fieldMultiplier, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
				new Vertex( new Vector3f(fieldWidth * this.fieldMultiplier, 0.0f, fieldDepth * this.fieldMultiplier), new Vector2f(1.0f, 1.0f))
			},
			new int[] {
				0, 1, 2,
				2, 1, 3
			}
		};
		Surface2D = new Object[] {
			new Vertex[] {
				new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
				new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * this.fieldMultiplier), new Vector2f(0.0f, 1.0f)),
				new Vertex( new Vector3f(fieldWidth * this.fieldMultiplier, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
				new Vertex( new Vector3f(fieldWidth * this.fieldMultiplier, 0.0f, fieldDepth * this.fieldMultiplier), new Vector2f(1.0f, 1.0f))
			},
			new int[] {
				0, 1, 2,
				2, 1, 3,
				1, 0, 2,
				2, 3, 1
			}
		};
	}

	public float getFieldDepth() {
		return this.fieldDepth;
	}

	public void setFieldDepth(float newFieldDepth) {
		this.fieldDepth = newFieldDepth;
		updateMeshes();
	}

	public float getFieldHeight() {
		return this.fieldHeight;
	}

	public void setFieldHeight(float newFieldHeight) {
		this.fieldHeight = newFieldHeight;
		updateMeshes();
	}

	public float getFieldWidth() {
		return this.fieldWidth;
	}

	public void setFieldWidth(float newFieldWidth) {
		this.fieldWidth = newFieldWidth;
		updateMeshes();
	}
}
