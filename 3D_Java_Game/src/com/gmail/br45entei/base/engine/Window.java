package com.gmail.br45entei.base.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class Window {
	public static int width;
	public static int height;
	
	public static void createWindow(int width, int height, String title) {
		Window.width = width;
		Window.height = height;
		Display.setTitle(title);
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create(new PixelFormat().withSamples(1));//Display.create();
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setWindowTitle(String title) {
		Display.setTitle(title);
	}
	
	public static void setFullScreen(boolean fullScreen) {
		try {
			//Display.setFullscreen(fullScreen);
			if(fullScreen) {
				Display.setDisplayModeAndFullscreen(new DisplayMode(width, height));
			} else {
				Display.setFullscreen(fullScreen);
				Display.setDisplayMode(new DisplayMode(width, height));
			}
		} catch (LWJGLException e) {
			Game.print("Failed to set full screen to: " + fullScreen + "; cause:\n" + e.getCause());
		}
	}
	
	public static boolean isFullScreen() {
		return Display.isFullscreen();
	}

	public static void render() {
		Display.update();
	}
	
	public static void dispose() {
		try {
			Display.destroy();
		} catch (Exception e) {
			
		}
		try {
			Keyboard.destroy();
		} catch (Exception e) {
			
		}
		try {
			Mouse.destroy();
		} catch (Exception e) {
			
		}
	}
	
	public static boolean isCloseRequested() {
		return Display.isCloseRequested();
	}
	
	public static int getWidth() {
		return Display.getDisplayMode().getWidth();
	}
	
	public static int getHeight() {
		return Display.getDisplayMode().getHeight();
	}
	
	public static String getTitle() {
		return Display.getTitle();
	}
}
