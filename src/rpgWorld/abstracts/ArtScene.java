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
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.TextureLoader;

import rpgWorld.app.GameLauncher;
import rpgWorld.interfaces.Level;

/**
 * @author Raishin
*/
public abstract class ArtScene implements Level {

	private final int FRAME_TEXT = 2;
	private final int WAIT_DOT = 20;
	private final int FRAME_IMAGE = 7;
	private final int WAIT_NEXT = 2000;
	
	String[] filename;
	List<Texture> texture = new ArrayList<>();
	
	private Texture currentImage;
	private String subText = " ";
 
	private int syncFrameText = 0;
	private int syncFrameImage = 0;

	private int indexImage = 0;
	private int indexSubImage = 0;
	private int indexText = 0;
	
	private List<Integer> image_per_scene = new ArrayList<>();
	
	int speedText = FRAME_TEXT;
	boolean finishedText = false;

	public ArtScene(String[] filename) {
		this.filename = filename;
		init();
		currentImage = texture.get(0);
	}
	
	public void init() {
		try {
			String[] splitee = new String[2];
			String namegif;
			for(String name : filename) {
				if(name.contains(".gif")) {
					splitee = name.split(".gif-"); // name file is   <namefile>.gif-<number of image> ex: background.gif-10
					this.image_per_scene.add(Integer.parseInt(splitee[1]));
					for(int x = 1; x < Integer.parseInt(splitee[1]) + 1; x++) {
						namegif = splitee[0] + x + ".png";
						texture.add(TextureLoader.getTexture("PNG", new FileInputStream(new File(namegif))));
					}
				} else {
					texture.add(TextureLoader.getTexture("PNG", new FileInputStream(new File(name))));
					this.image_per_scene.add(1);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean display() {

		glBindTexture(GL_TEXTURE_2D, currentImage.getTextureID());
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(0, 0);
	
			glTexCoord2f(1, 0);
			glVertex2f(currentImage.getTextureWidth(), 0);
	
			glTexCoord2f(1, 1);
			glVertex2f(currentImage.getTextureWidth(), currentImage.getTextureHeight());
	
			glTexCoord2f(0, 1);
			glVertex2f(0, currentImage.getTextureHeight());
		glEnd();

		
		if(indexSubImage < this.image_per_scene.get(indexImage)) {
			if(syncFrameImage == 0) {
				currentImage = texture.get(indexImage + indexSubImage);
				indexSubImage++;
				syncFrameImage = FRAME_IMAGE;
			}
		} else {
			indexSubImage = 0;
			if(finishedText) {
				indexImage++;
				finishedText = false;
				try {
					Thread.sleep(this.WAIT_NEXT);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
		}
		syncFrameImage--;

		return false;
	}
	
	public void write(int x, int y, String text) {
		TextureImpl.bindNone();
		GameLauncher.font.drawString(x, y, subText);
		
		if(indexText < text.length()) {
			if(syncFrameText == 0) {
				subText = text.substring(0, indexText + 1);

				if(text.charAt(indexText) == '.') {
					syncFrameText += WAIT_DOT; //wait twice when there is a dot.
				}
				
				indexText++;
				syncFrameText += speedText;
			}
		} else {
			finishedText = true;
			if(text != subText) {
				resetText();
			}
			return;
		}
		syncFrameText--;
		finishedText = false;
	}
	
	public void write(int x, int y, String text, int speed) {
		speedText = speed;
		write(x, y, text);
	}

	public void resetText() {
		subText = " ";
		 
		syncFrameText = 0;

		indexText = 0;
	}
}