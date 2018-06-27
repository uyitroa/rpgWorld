/**
 * 
 */
package rpgWorld.abstracts;

import java.io.File;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import static org.lwjgl.opengl.GL11.*;

import rpgWorld.interfaces.Entity;

/**
 * @author Raishin
 *
 */
public abstract class Human implements Entity {
	
	private final int NUMBER_DIR = 4;
	private final int NUMBER_EACH_DIR = 4;
	private final String[] ID_DIR = {"back", "front", "left", "right"};
	private final int FRAME_DIRECTION = 5;
	
	//id
	private final int BACK = 0;
	private final int FRONT = 1;
	private final int LEFT = 2;
	private final int RIGHT = 3;
	
	private int syncFrameDir = 1;
	private String folder_sprites;
	
	private List<Texture> sprites = new ArrayList<>();
	private Texture currentSprite;
	
	protected int x;
	protected int y;
	
	public Human(String folder_sprites) {
		this.folder_sprites = folder_sprites;
		init();
		currentSprite = sprites.get(0);
	}


	public void draw() {
		glBindTexture(GL_TEXTURE_2D, currentSprite.getTextureID());
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(x, y);
			
			glTexCoord2f(1, 0);
			glVertex2f(x + currentSprite.getTextureWidth(), y);
			
			glTexCoord2f(1, 1);
			glVertex2f(x + currentSprite.getTextureWidth(), y + currentSprite.getTextureHeight());
			
			glTexCoord2f(0, 1);
			glVertex2f(x, y + currentSprite.getTextureHeight());
		glEnd();

	}
	
	public void init() {
		try {
			for(int y = 0; y < NUMBER_DIR; y++) {
				for(int x = 1; x <= this.NUMBER_EACH_DIR; x++) {
					sprites.add(TextureLoader.getTexture("PNG", new FileInputStream(new File(folder_sprites + ID_DIR[y] + x + ".png"))));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * change sprite to animate player when move
	 */
	public void updateDir(int dir, int pose) {
		if(syncFrameDir <= 0) {
			syncFrameDir = FRAME_DIRECTION;
			int i = dir * NUMBER_DIR + pose;
			currentSprite = sprites.get(i);
		} else {
			syncFrameDir--;
		}
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}

}
