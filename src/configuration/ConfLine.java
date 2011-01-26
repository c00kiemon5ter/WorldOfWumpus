package configuration;

import core.Direction;
import core.SquareType;
import java.awt.Point;

/**
 *
 * @author c00kiemon5ter
 */
public class ConfLine {

	private SquareType type;
	private Point point;
	private Direction direction;

	public ConfLine(SquareType type, Point coordinate) {
		this.type = type;
		this.point = coordinate;
	}

	public SquareType getType() {
		return type;
	}

	public Point getPoint() {
		return point;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

	public String getObjClassName() {
		switch (type) {
			case EXPLORER:
				return "Explorer";
			case GOLD:
				return "Gold";
			case PIT:
				return "Pit";
			case WUMPUS:
				return "Wumpus";
			default:
				return "UnknownObject";
		}
	}

	@Override
	public String toString() {
		return String.format("%s %d %d", type.toString(), point.x, point.y);
	}
}
