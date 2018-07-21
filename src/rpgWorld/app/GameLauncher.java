/**
 * 
 */
package rpgWorld.app;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.TrueTypeFont;

import rpgWorld.interfaces.Level;
import rpgWorld.levels.EndingScene;
import rpgWorld.levels.IntroductionScene;
import rpgWorld.levels.Level1;

/**
 * @author yuitora
 *
 */
public class GameLauncher {

	public static final int WIDTH = 1440;
	public static final int HEIGHT = 900;
	public static final int MOVE_STEP = 5;
	private List<Level> level = new ArrayList<>();
	public static TrueTypeFont font = null;
	
	private int currentLevel = 0;

	private void initGL() {
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("Abyss World");
			Display.create();
		} catch (Exception e) {
			Display.destroy();
			System.exit(1);
		}

		glEnable(GL_TEXTURE_2D);
		glShadeModel(GL_SMOOTH);
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_LIGHTING);
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		glClearDepth(1);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glMatrixMode(GL_MODELVIEW);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}

	public void setup() {
		//level.add(new IntroductionScene());
		level.add(new Level1());
		//level.add(new EndingScene());
	}
	
	public void initFont() {
		Font awtFont = new Font("Times New Roman", Font.PLAIN, 24);
		font = new TrueTypeFont(awtFont, true);
	}

	public void gameLoop() {
		while (currentLevel < level.size() && !Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);

			if(level.get(currentLevel).show()) {
				currentLevel++;
			}
			
			Display.update();
			Display.sync(60);
		}

		Display.destroy();
		System.exit(0);
	}

	public void start() {
		initGL();
		initFont();
		setup();
		gameLoop();
	}

	public static void main(String[] args) {
		GameLauncher game = new GameLauncher();
		game.start();

	}

}
