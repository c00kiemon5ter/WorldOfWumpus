package worldofwumpus;

import configuration.ConfDefs;
import core.Direction;
import core.Square;
import core.SquareType;
import java.awt.Point;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jess.Fact;
import jess.JessException;
import jess.Rete;
import utils.Utils;

/**
 *
 * @author c00kiemon5ter
 */
public class JessHandler {

	private Rete engine;
	private Fact explorerFact;
	private Direction explorerDirection;
	private Point explorerPosition;
	private final String RoomFact = "MAIN::Room";
	private final String ExplorerFact = "MAIN::Explorer";
	private final String TurnSyntax = "(turn %d %d)";
	private final String MoveSyntax = "(move %d %d %d)";

	public JessHandler() {
		init();
	}

	public final void init() {
		engine = new Rete();
		try {
			engine.batch(ConfDefs.RULES);
			engine.batch(ConfDefs.WORLD);
			engine.reset();
			engine.run();
		} catch (JessException je) {
			Logger.getLogger(WordOfWumpus.class.getName()).log(Level.SEVERE, "==> ERROR: Fatal Jess Illegal State", je);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Square> updateSquareInfo(List<Square> squares) {
		Iterator<Fact> facts = engine.listFacts();
		while (facts.hasNext()) {
			Fact fact = facts.next();
			try {
				if (fact.getName().equals(RoomFact)) {
					Square square = squareFromFact(fact);
					squares.set(Utils.pointToIndex(square.getPoint()), square);
				} else if (fact.getName().equals(ExplorerFact)) {
					this.explorerFact = fact;
					this.explorerPosition = new Point(
						Integer.parseInt(fact.get(0).toString()),
						Integer.parseInt(fact.get(1).toString()));
					this.explorerDirection = Direction.valueOf(Integer.parseInt(fact.get(2).toString()));
					squares.get(Utils.pointToIndex(explorerPosition)).addType(SquareType.EXPLORER);
				}
			} catch (JessException je) {
				throw new IllegalArgumentException("==> ERROR: Parsing failed: " + fact.toString());
			}
		}
		return squares;
	}

	public Direction getExplorerDirection() {
		return explorerDirection;
	}

	public Point getExplorerPosition() {
		return explorerPosition;
	}

	private Square squareFromFact(Fact fact) throws JessException {
		Square square = null;
		/* parse position */
		Point point = new Point(
			Integer.parseInt(fact.get(0).toString()),
			Integer.parseInt(fact.get(1).toString()));
		square = new Square(point);
		/* parse properties */
		for (int idx = 2; idx < fact.getDeftemplate().getNSlots(); idx++) {
			String factvalue = fact.get(idx).toString();
			if (factvalue.equalsIgnoreCase("TRUE")) {
				square.addType(SquareType.valueOf(
					fact.getDeftemplate().getSlotName(idx).
					toUpperCase().replace("HAS", "")));
			} else if (factvalue.equalsIgnoreCase("MAYBE")) {
				square.addType(SquareType.UNKNOWN);
				square.addType(SquareType.valueOf(
					"MAYBE" + fact.getDeftemplate().
					getSlotName(idx).toUpperCase().
					replace("HAS", "")));
			}
		}
		return square;
	}

	public void turn(Direction toLook) {
		try {
			engine.eval(String.format(TurnSyntax,
						  explorerFact.getFactId(),
						  toLook.id()));
			engine.run();
		} catch (JessException je) {
			throw new IllegalArgumentException("==> ERROR: Invalid turn: " + toLook.toString());
		}
	}

	public void move(Direction direction, Point position) {
		try {
			engine.eval(String.format(MoveSyntax,
						  explorerFact.getFactId(),
						  position.x, position.y));
			engine.run();
		} catch (JessException je) {
			throw new IllegalArgumentException("==> ERROR: Invalid move: " + position.toString());
		}
	}
}
