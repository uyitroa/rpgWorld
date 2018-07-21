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
		super.x = GameLauncher.WIDTH / 2;
		super.y = GameLauncher.HEIGHT / 2;
	}

	@Override
	public void move(int moveX, int moveY) {
		//player need to be at the middle, so if other thing move x, the player need to move -x
		super.x = moveX * -1;
		super.y = moveY * -1;
		
		if(super.x == GameLauncher.MOVE_STEP) {
			super.updateDir(super.RIGHT);
			
		} else if(super.x == -GameLauncher.MOVE_STEP) {
			super.updateDir(super.LEFT);
			
		} else if(super.y == GameLauncher.MOVE_STEP) {
			super.updateDir(super.FRONT);
			
		} else if(super.y == -GameLauncher.MOVE_STEP) {
			super.updateDir(super.BACK);
		}
	}

}
