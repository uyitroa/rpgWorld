/**
 * 
 */
package rpgWorld.interfaces;

/**
 * @author Raishin
 *
 */
public interface Level {
	
	/*
	 * display the scene's background
	 */
	public void display();
	
	public void init();
	
	/*
	 * show on screen an return if the level is finished or not
	 */
	public boolean show();
}
