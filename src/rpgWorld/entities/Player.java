/**
 * 
 */
package rpgWorld.entities;

import rpgWorld.abstracts.Human;
import rpgWorld.app.GameLauncher;

/**
 * @author Raishin
 *
 */
public class Player extends Human {
	
	private static final String FOLDERPATH = "res/images/sprites/";
	
	public Player() {
		super(FOLDERPATH);
		super.x = GameLauncher.WIDTH / 2 - 50;
		super.y = GameLauncher.HEIGHT / 2 - 50;
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
