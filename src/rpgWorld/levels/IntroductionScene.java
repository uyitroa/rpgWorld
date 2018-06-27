/**
 * 
 */
package rpgWorld.levels;

import rpgWorld.abstracts.ArtScene;

/**
 * @author Raishin
 *
 */
public class IntroductionScene extends ArtScene {

	/* (non-Javadoc)
	 * @see rpgWorld.interfaces.Level#display()
	 */
	private static final String[] FILES = {"res/images/backgroundfirst.png", "res/images/backgroundDeath.png", "res/images/angry.gif-10"};
	private final String[] TEXT = {"En 6969, tout a changé. Le monde maintenant possède une seule usine principale, qui s'appelle IBM. Le soleil est devenu une légende.",
			"Ma mère est malade à cause de la pollution. Mon père et ma soeur sont morts. D'après le docteur, maman n'a que 3 jours a vivre.",
			"Si je veux la sauver, je devrais désactiver le coeur processeur de IBM.."};
	private final int TEXT_X = 10;
	private final int TEXT_Y = 5;

	private int currentIndex = 0;
	
	public IntroductionScene() {
			super(FILES);
	}
	
	public boolean show() {

		super.display();
		if(super.finishedImage) {
			currentIndex++;
		}
		
		if(this.currentIndex == TEXT.length) {
			return true;
		} else {
			write(TEXT_X, TEXT_Y, TEXT[currentIndex]);
		}

		return false;
	}

}
