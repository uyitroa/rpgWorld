/**
 * 
 */
package rpgWorld.app;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import rpgWorld.interfaces.Level;
import rpgWorld.level.IntroductionScene;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuitora
 *
 */
public class GameLauncher {

	public static final int WIDTH = 1440;
	public static final int HEIGHT = 900;
	private List<Level> level = new ArrayList<>();

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
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glMatrixMode(GL_MODELVIEW);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}

	public void setup() {
		level.add(new IntroductionScene());
	}

	public void gameLoop() {
		while (!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);

			Display.update();
			Display.sync(60);
		}

		Display.destroy();
		System.exit(0);
	}

	public void start() {
		initGL();
		gameLoop();
	}

	public static void main(String[] args) {
		GameLauncher game = new GameLauncher();
		game.start();

	}

}
