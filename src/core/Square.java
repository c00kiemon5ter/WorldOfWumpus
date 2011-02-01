package core;

import configuration.ConfDefs;
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
		this.typeset.remove(SquareType.EMPTY);
		this.typeset.add(type);
	}

	public boolean isOfType(SquareType type) {
		return this.typeset.contains(type);
	}

	@Override
	public String toString() {
		StringBuilder state = new StringBuilder();
		state.append(String.format(":: [%2s]", Utils.toBoardNotation(point)));
		for (SquareType type : SquareType.values()) {
			state.append(String.format("%12s", typeset.contains(type)));
		}
		return state.toString();
	}

	public String toStringWithHeader() {
		StringBuilder header = new StringBuilder();
		StringBuilder state = new StringBuilder();
		header.append(":: Room");
		state.append(String.format(":: [%2s]", Utils.toBoardNotation(point)));
		for (SquareType type : SquareType.values()) {
			header.append(String.format("%12s", type.toString()));
			state.append(String.format("%12s", typeset.contains(type)));
		}
		return header.append(ConfDefs.NEWLINE).append(state).toString();
	}

	public String toStringAscii() {
		StringBuilder ascii = new StringBuilder();
		for (SquareType type : typeset) {
			ascii.append(type.symbol());
		}
		return ascii.toString();
	}
}
