package core;

import configuration.ConfDefs;
import java.awt.Image;

/**
 *
 * @author c00kiemon5ter
 */
public enum SquareType {

	EXPLORER(0, ConfDefs.EXPLORER_IMG, 7),
	SMELL(0, ConfDefs.SMELL_IMG, 6),
	BREEZE(0, ConfDefs.BREEZE_IMG, 6),
	SHINE(0, ConfDefs.SHINE_IMG, 5),
	GOLD(+1000, ConfDefs.GOLD_IMG, 5),
	UNKNOWN(0, ConfDefs.UNKNOWN_IMG, 5),
	WUMPUS(-1000, ConfDefs.WUMPUS_IMG, 4),
	PIT(-1000, ConfDefs.PIT_IMG, 3),
	MAYBEWUMPUS(0, ConfDefs.UNSAFE_IMG, 2),
	MAYBEPIT(0, ConfDefs.UNSAFE_IMG, 2),
	VISITED(0, ConfDefs.VISITED_IMG, 1),
	EMPTY(0, ConfDefs.EMPTY_IMG, 0),
	SAFE(0, ConfDefs.EMPTY_IMG, 0),;
	private int score;
	private Image image;
	private int priority;

	private SquareType(int score, Image image, int priority) {
		this.score = score;
		this.image = image;
		this.priority = priority;
	}

	public int score() {
		return score;
	}

	public Image image() {
		return image;
	}

	public int priority() {
		return priority;
	}

	public static int priorityFor(Image image) {
		for (SquareType squareType : values()) {
			if (squareType.image() == image) {
				return squareType.priority;
			}
		}
		return 0;
	}
}
