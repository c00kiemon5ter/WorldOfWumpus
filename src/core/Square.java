package core;

import java.awt.Point;
import java.util.EnumSet;
import utils.Utils;

/**
 *
 * @author c00kiemon5ter
 */
public class Square {

	private Point point;
	private EnumSet<SquareType> typeset;

	public Square(Point point) {
		this.typeset = EnumSet.of(SquareType.EMPTY);
		this.point = point;
	}

	public Point getPoint() {
		return point;
	}

	public EnumSet<SquareType> getTypes() {
		return this.typeset;
	}

	public void addType(SquareType type) {
		this.typeset.add(type);
	}

	public boolean isOfType(SquareType type) {
		return this.typeset.contains(type);
	}

	@Override
	public String toString() {
		return String.format("[%2s]%8s%8s%8s%8s%8s%8s%8s%8s",
				     Utils.toBoardNotation(point),
				     typeset.contains(SquareType.VISITED),
				     typeset.contains(SquareType.SMELL),
				     typeset.contains(SquareType.BREEZE),
				     typeset.contains(SquareType.SHINE),
				     typeset.contains(SquareType.WUMPUS),
				     typeset.contains(SquareType.PIT),
				     typeset.contains(SquareType.GOLD),
				     typeset.contains(SquareType.SAFE));
	}

	public String toStringWithHeader() {
		return String.format(":: Room%9s%9s%9s%9s%9s%9s%9s%9s\n"
				     + ":: [%2s]%9s%9s%9s%9s%9s%9s%9s%9s",
				     SquareType.VISITED.toString(),
				     SquareType.SMELL.toString(),
				     SquareType.BREEZE.toString(),
				     SquareType.SHINE.toString(),
				     SquareType.WUMPUS.toString(),
				     SquareType.PIT.toString(),
				     SquareType.GOLD.toString(),
				     SquareType.SAFE.toString(),
				     Utils.toBoardNotation(point),
				     typeset.contains(SquareType.VISITED),
				     typeset.contains(SquareType.SMELL),
				     typeset.contains(SquareType.BREEZE),
				     typeset.contains(SquareType.SHINE),
				     typeset.contains(SquareType.WUMPUS),
				     typeset.contains(SquareType.PIT),
				     typeset.contains(SquareType.GOLD),
				     typeset.contains(SquareType.SAFE));
	}
}
