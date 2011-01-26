package configuration;

import core.Direction;
import core.SquareType;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import worldofwumpus.WordOfWumpus;

/**
 *
 * @author c00kiemon5ter
 */
public class ConfParser {

	private List<ConfLine> confObjs;
	private ConfLine explorer;

	public ConfParser() {
		confObjs = new LinkedList<ConfLine>();
	}

	public List<ConfLine> parse() {
		Scanner scan = null;
		try {
			scan = new Scanner(new BufferedReader(new FileReader(new File(ConfDefs.CONF))));
		} catch (FileNotFoundException fnfe) {
			Logger.getLogger(String.format("%s::%s", WordOfWumpus.class.getName(),
						       ConfParser.class.getName())).log(Level.SEVERE, "==> ERROR: missing configuration file", fnfe);
		}
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (line.trim().isEmpty() || line.startsWith("//")) {
				continue;
			}

			String[] words = line.split("\\s+");
			if (words.length < 3) {
				throw new IllegalArgumentException("==> ERROR: Invalid configuration line. "
								   + "Missing arguments: " + line);
			}

			SquareType type = null;
			try {
				type = SquareType.valueOf(words[0].toUpperCase());
			} catch (IllegalArgumentException iae) {
				throw new IllegalArgumentException("==> ERROR: Invalid configuration line. "
								   + "Unknown object type: " + line);
			}

			int x, y;
			try {
				x = Integer.parseInt(words[1]) - 1;
				y = Integer.parseInt(words[2]) - 1;
				if (x < 0 || y < 0
				    || x >= ConfDefs.BOARD_LENGTH
				    || y >= ConfDefs.BOARD_WIDTH) {
					throw new IllegalArgumentException("==> ERROR: Invalid configuration line. "
									   + "Out of range coordinates: " + line);
				}
			} catch (NumberFormatException nfe) {
				throw new IllegalArgumentException("==> ERROR: Invalid configuration line. "
								   + "Cooardinates must be numbers: " + line);
			}

			Point point = new Point(x, y);
			ConfLine confObj = new ConfLine(type, point);

			if (type == SquareType.EXPLORER) {
				Direction direction;
				try {
					direction = Direction.valueOf(words[3]);
				} catch (IllegalArgumentException iae) {
					throw new IllegalArgumentException("==> ERROR: Invalid configuration line. "
									   + "Unknown direction type: " + line);
				}
				confObj.setDirection(direction);
				this.explorer = confObj;
			}

			confObjs.add(confObj);
		}
		scan.close();
		return confObjs;
	}

	public ConfLine getExplorer() {
		return explorer;
	}
}
