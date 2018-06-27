/**
 * 
 */
package rpgWorld.levels;

import java.util.ArrayList;

import rpgWorld.abstracts.upsideDownLevel;
import rpgWorld.entities.Player;

/**
 * @author Raishin
 *
 */
public class Level1 extends upsideDownLevel {

	private static final String FILE_MAP = "res/images/lvl1_map.png";
	
	public Level1() {
		super(FILE_MAP);
	}
	
	private void setup() {
		super.entity = new ArrayList<>();
		super.entity.add(new Player());
	}
	
	public boolean show() {
		super.display();
		
		return false;
	}

}
