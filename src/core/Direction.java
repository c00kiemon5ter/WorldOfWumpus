package core;

import java.awt.Point;

/**
 *
 * @author c00kiemon5ter
 */
public enum Direction {
	/* ↑ */ UP(-1, 0, 0),
	/* → */ RIGHT(0, +1, 1),
	/* ↓ */ DOWN(+1, 0, 2),
	/* ← */ LEFT(0, -1, 3),;
	private int rowstep;
	private int colstep;
	private int id;

	private Direction(int rowstep, int colstep, int id) {
		this.rowstep = rowstep;
		this.colstep = colstep;
		this.id = id;
	}

	public Point next(Point point) {
		return new Point(point.x + rowstep, point.y + colstep);
	}

	public int id() {
		return id;
	}

	public static Direction valueOf(int id) {
		for (Direction direction : values()) {
			if (direction.id == id) {
				return direction;
			}
		}
		/* should never happen */
		return null;
	}

	public static Direction right(Direction current) {
		switch (current) {
			case UP:
				return RIGHT;
			case RIGHT:
				return DOWN;
			case DOWN:
				return LEFT;
			case LEFT:
				return UP;
		}
		/* should never happen */
		return null;
	}

	public static Direction left(Direction current) {
		switch (current) {
			case UP:
				return LEFT;
			case RIGHT:
				return UP;
			case DOWN:
				return RIGHT;
			case LEFT:
				return DOWN;
		}
		/* should never happen */
		return null;
	}
}
