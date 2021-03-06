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

/**
 *
 * @author c00kiemon5ter
 */
public class ConfParser {

	private List<ConfLine> conflines;

	public ConfParser() {
		conflines = new LinkedList<ConfLine>();
	}

	public List<ConfLine> parse() {
		Scanner scan = null;
		try {
			scan = new Scanner(new BufferedReader(new FileReader(new File(ConfDefs.CONF))));
		} catch (FileNotFoundException fnfe) {
			throw new IllegalAccessError("==> ERROR: missing configuration file");
		}
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (line.trim().isEmpty() || line.startsWith("//")) {
				continue;
			}
			String[] words = line.split("\\s+");
			if (words.length < 3 || words.length > 4) {
				throw new IllegalArgumentException("==> ERROR: Invalid configuration line. "
								   + "Wrong argument count: " + line);
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
			ConfLine confline = new ConfLine(type, point);

			if (type == SquareType.EXPLORER) {
				Direction direction;
				try {
					direction = Direction.valueOf(words[3]);
				} catch (IllegalArgumentException iae) {
					throw new IllegalArgumentException("==> ERROR: Invalid configuration line. "
									   + "Unknown direction: " + line);
				}
				confline.setDirection(direction);
			}
			conflines.add(confline);
		}
		scan.close();
		return conflines;
	}
}
