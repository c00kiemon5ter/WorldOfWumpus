package utils;

import configuration.ConfDefs;
import core.Direction;
import core.Square;
import java.awt.Point;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Transformations and more useful functions.
 * Translating points to indexes and board notations and reverse.
 *
 * @author c00kiemon5ter
 */
public class Utils {

	public static Point indexToPoint(int index) {
		return new Point(index / ConfDefs.BOARD_LENGTH, index % ConfDefs.BOARD_LENGTH);
	}

	public static Point indexToPoint(int index, int length) {
		return new Point(index / length, index % length);
	}

	public static int pointToIndex(Point point) {
		return ConfDefs.BOARD_LENGTH * point.x + point.y;
	}

	public static int pointToIndex(Point point, int length) {
		return length * point.x + point.y;
	}

	public static String toBoardNotation(Point coordinate) {
		return String.format("%d%c", coordinate.x + 1, coordinate.y + 65);
	}

	public static Point fromBoardNotation(String coordinate) {
		return new Point(coordinate.charAt(0) - 1, coordinate.charAt(1) - 65);
	}

	public static Map<Direction, Square> getExplorerEnvironment(final List<Square> squares, final Point position) {
		Map<Direction, Square> surroundingSquares = new EnumMap<Direction, Square>(Direction.class);
		for (Direction direction : Direction.values()) {
			if (direction.next(position).x >= 0
			    && direction.next(position).x < ConfDefs.BOARD_LENGTH
			    && direction.next(position).y >= 0
			    && direction.next(position).y < ConfDefs.BOARD_WIDTH) {
				surroundingSquares.put(direction, squares.get(
					Utils.pointToIndex(
					direction.next(position))));
			}
		}
		return surroundingSquares;
	}
}
