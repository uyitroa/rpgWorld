/**
 * 
 */
package rpgWorld.abstracts;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import rpgWorld.app.GameLauncher;
import rpgWorld.interfaces.Entity;
import rpgWorld.interfaces.Level;

/**
 * @author Raishin
 *
 */
public abstract class upsideDownLevel implements Level{
	private final int RATIO_EDGE_WIDTH = 14; // to check if the player touch the edge
	private final int RATIO_EDGE_HEIGHT = 30;

	private String file_map;
	private Texture map;
	private int x_background = 0;
	private int y_background = 0;
	public List<Entity> entity = null;
	private int moveX = 0;
	private int moveY = 0;
	
	public upsideDownLevel(String file_map) {
		this.file_map = file_map;
		init();
	}
	
	public void init() {
		try {
			map = TextureLoader.getTexture("PNG", new FileInputStream(new File(file_map)));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void checkKey() {

		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			if(this.x_background >= map.getTextureWidth()/RATIO_EDGE_WIDTH) {
				this.x_background -= GameLauncher.MOVE_STEP;
				moveX -= GameLauncher.MOVE_STEP;
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			if(this.x_background <= map.getTextureWidth() - map.getTextureWidth()/RATIO_EDGE_WIDTH) {
				this.x_background += GameLauncher.MOVE_STEP;
				moveX += GameLauncher.MOVE_STEP;
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			if(this.y_background >= map.getTextureHeight()/RATIO_EDGE_HEIGHT) {
				this.y_background -= GameLauncher.MOVE_STEP;
				moveY -= GameLauncher.MOVE_STEP;
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			if(this.y_background <= map.getTextureHeight()) {
				this.y_background += GameLauncher.MOVE_STEP;
				moveY += GameLauncher.MOVE_STEP;
			}
		}
	}
	
	private void moveObjectAndDraw(int x, int y) {
		for(Entity object : entity) {
			object.draw();
			object.move(x, y);
		}
	}
	
	private void drawBG() {
		glBindTexture(GL_TEXTURE_2D, map.getTextureID());
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(this.x_background, this.y_background);
			
			glTexCoord2f(1, 0);
			glVertex2f(this.x_background + map.getTextureWidth(), this.y_background);
			
			glTexCoord2f(1, 1);
			glVertex2f(this.x_background + map.getTextureWidth(), this.y_background + map.getTextureHeight());
			
			glTexCoord2f(0, 1);
			glVertex2f(this.x_background, this.y_background + map.getTextureHeight());
		glEnd();
	}
	
	
	public void display() {
		drawBG();
		checkKey();
		moveObjectAndDraw(moveX, moveY);
	}

	
	
	public int getX_background() {
		return x_background;
	}

	public void setX_background(int x_background) {
		this.x_background = x_background;
	}

	public int getY_background() {
		return y_background;
	}

	public void setY_background(int y_background) {
		this.y_background = y_background;
	}

}
