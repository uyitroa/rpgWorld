/**
 * 
 */
package rpgWorld.levels;

import rpgWorld.abstracts.ArtScene;

/**
 * @author Raishin
 *
 */
public class EndingScene extends ArtScene {
	private static final String[] FILENAME = {"res/images/endscene/Propre.gif-70", "res/images/black.png", 
			"res/images/phone.png", "res/images/black.png", "res/images/ending.gif-17", "res/images/ending17.png"};
	private final String[] TEXT = {"", "Mais. . .   ", "                                             ", "Maman est   ","", ""};
	private final int[] TEXT_X = {10, 10, 10, 600, 10, 10};
	private final int[] TEXT_Y = {5, 5, 5, 450, 5, 5};

	private int currentIndex = 0;
	
	public EndingScene() {
		super(FILENAME);
	}

	public boolean show() {

		if(super.display()) {
			currentIndex++;
		}


		write(TEXT_X[currentIndex], TEXT_Y[currentIndex], TEXT[currentIndex]);
		
		if(this.currentIndex == TEXT.length) {
			return true;
		}

		return false;
	}
}
