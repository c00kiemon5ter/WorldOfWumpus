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
		StringBuilder info = new StringBuilder();
		info.append(String.format(":: [%2s]", Utils.toBoardNotation(point)));
		for (SquareType type : SquareType.values()) {
			info.append(String.format("%12s", typeset.contains(type)));
		}
		return info.toString();
	}

	public String toStringWithHeader() {
		StringBuilder header = new StringBuilder();
		StringBuilder info = new StringBuilder();
		header.append(":: Room");
		info.append(String.format(":: [%2s]", Utils.toBoardNotation(point)));
		for (SquareType type : SquareType.values()) {
			header.append(String.format("%12s", type.toString()));
			info.append(String.format("%12s", typeset.contains(type)));
		}
		return header.append(System.getProperty("line.separator")).append(info).toString();
	}
}
