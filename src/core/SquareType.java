package core;

import configuration.ConfDefs;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author c00kiemon5ter
 */
public enum SquareType {

	EXPLORER	('@',	ConfDefs.EXPLORER_IMG	),
	SMELL		('S',	ConfDefs.SMELL_IMG	),
	BREEZE		('B',	ConfDefs.BREEZE_IMG	),
	SHINE		('L',	ConfDefs.SHINE_IMG	),
	GOLD		('G',	ConfDefs.GOLD_IMG,	+1000),
	UNKNOWN		('?',	ConfDefs.UNKNOWN_IMG	),
	WUMPUS		('W',	ConfDefs.WUMPUS_IMG,	-1000),
	PIT		('P',	ConfDefs.PIT_IMG,	-1000),
	MAYBEWUMPUS	('M',	ConfDefs.UNSAFE_IMG	),
	MAYBEPIT	('M',	ConfDefs.UNSAFE_IMG	),
	VISITED		('V',	ConfDefs.VISITED_IMG	),
	EMPTY		('E',	ConfDefs.EMPTY_IMG	),
	SAFE		('O',	ConfDefs.EMPTY_IMG	),;

	/* reverse look up table mapping images to their priority */
	private static Map<Image, Integer> priorities;
	static {
		Map<Image, Integer> map = new HashMap<Image, Integer>(values().length);
		map.put(ConfDefs.EXPLORER_IMG,	16);
		map.put(ConfDefs.SMELL_IMG,	14);
		map.put(ConfDefs.BREEZE_IMG,	12);
		map.put(ConfDefs.SHINE_IMG,	10);
		map.put(ConfDefs.GOLD_IMG,	10);
		map.put(ConfDefs.UNKNOWN_IMG,	10);
		map.put(ConfDefs.WUMPUS_IMG,	8);
		map.put(ConfDefs.PIT_IMG,	6);
		map.put(ConfDefs.UNSAFE_IMG,	4);
		map.put(ConfDefs.VISITED_IMG,	2);
		map.put(ConfDefs.EMPTY_IMG,	0);
		priorities = map;
	}

	private char symbol;
	private Image image;
	private int score;

	private SquareType(char symbol, Image image) {
		this(symbol, image, 0);
	}

	private SquareType(char symbol, Image image, int score) {
		this.symbol = symbol;
		this.image = image;
		this.score = score;
	}

	public char symbol() {
		return symbol;
	}

	public Image image() {
		return image;
	}

	public int score() {
		return score;
	}

	public static int priorityFor(Image image) {
		return priorities.get(image).intValue();
	}
}
