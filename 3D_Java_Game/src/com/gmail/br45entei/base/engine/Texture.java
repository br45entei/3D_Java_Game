package com.gmail.br45entei.base.engine;

import java.io.File;
import java.io.FileInputStream;

import org.newdawn.slick.opengl.TextureLoader;

public class Texture {
	private int id;
	
	public Texture(String fileName) {
		this(loadTexture(fileName));
	}
	
	public Texture(int id) {
		this.id = id;
	}
	
	public void bind() {
		org.lwjgl.opengl.GL11.glBindTexture(org.lwjgl.opengl.GL11.GL_TEXTURE_2D, id);
	}
	
	public int getID() {
		return this.id;
	}
	
	@SuppressWarnings({ "resource" })
	private static int loadTexture(String fileName) {
		String[] splitArray = fileName.split("\\.");
		String ext = splitArray[splitArray.length - 1];
		
		try {
			int id = TextureLoader.getTexture(ext, new FileInputStream(new File(MainComponent.resourceFolder + "textures/" + fileName))).getTextureID();
			return id;
		} catch(Exception e) {
			e.printStackTrace();
			//System.exit(1);
		}
		
		return 0;
	}
	
}
