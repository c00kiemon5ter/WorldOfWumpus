package ui;

import configuration.ConfDefs;
import core.Direction;
import core.Square;
import core.SquareType;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utils.Utils;
import worldofwumpus.JessHandler;

/**
 *
 * @author c00kiemon5ter
 */
public class GameUI extends JFrame {

	private JPanel board;
	private JLabel scoreStat;
	private JButton next;
	private JessHandler jessHandle;
	private List<Square> squares;
	private Direction explorerDirection;
	private Point explorerPosition;
	private int score = 0;

	public GameUI() {
		super("World of Wumpus");
		this.jessHandle = new JessHandler();
		this.squares = new ArrayList<Square>(ConfDefs.BOARD_LENGTH * ConfDefs.BOARD_WIDTH);
		initComponents();
		updateSquares();
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initComponents() {
		Container pane = this.getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints constrains = new GridBagConstraints();

		/* the menus */
		JMenuBar menubar = new JMenuBar();
		JMenu menu;
		JMenuItem menuitem;

		menu = new JMenu("File");
		menuitem = new JMenuItem("New Game");
		menuitem.addActionListener(newGameListener());
		menu.add(menuitem);
		menuitem = new JMenuItem("Exit");
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		menu.add(menuitem);
		menubar.add(menu);

		menu = new JMenu("Help");
		menuitem = new JMenuItem("About");
		menuitem.addActionListener(aboutListener());
		menu.add(menuitem);
		menubar.add(menu);

		/* place the menubar */
		constrains.anchor = GridBagConstraints.FIRST_LINE_START;
		constrains.fill = GridBagConstraints.HORIZONTAL;
		constrains.gridwidth = 3;
		constrains.gridx = 0;
		constrains.gridy = 0;
		pane.add(menubar, constrains);
		constrains.gridwidth = 0;

		/* paint the cols */
		ImageComponent imgcomp;
		imgcomp = new ImageComponent(ConfDefs.COLS_IMG);
		constrains.anchor = GridBagConstraints.PAGE_START;
		constrains.fill = GridBagConstraints.HORIZONTAL;
		constrains.gridwidth = 3;
		constrains.gridx = 0;
		constrains.gridy = 1;
		pane.add(imgcomp, constrains);
		constrains.gridwidth = 0;
		imgcomp = new ImageComponent(ConfDefs.COLS_IMG);
		constrains.anchor = GridBagConstraints.PAGE_START;
		constrains.fill = GridBagConstraints.HORIZONTAL;
		constrains.gridwidth = 3;
		constrains.gridx = 0;
		constrains.gridy = 3;
		pane.add(imgcomp, constrains);
		constrains.gridwidth = 0;

		/* paint the rows */
		imgcomp = new ImageComponent(ConfDefs.ROWS_IMG);
		constrains.anchor = GridBagConstraints.LINE_START;
		constrains.fill = GridBagConstraints.VERTICAL;
		constrains.gridwidth = 0;
		constrains.gridx = 0;
		constrains.gridy = 2;
		pane.add(imgcomp, constrains);
		constrains.gridwidth = 0;
		imgcomp = new ImageComponent(ConfDefs.ROWS_IMG);
		constrains.anchor = GridBagConstraints.LINE_END;
		constrains.fill = GridBagConstraints.VERTICAL;
		constrains.gridwidth = 0;
		constrains.gridx = 2;
		constrains.gridy = 2;
		pane.add(imgcomp, constrains);
		constrains.gridwidth = 0;

		/* paint the squares */
		board = new JPanel(new GridLayout(ConfDefs.BOARD_LENGTH, ConfDefs.BOARD_WIDTH));
		for (int row = 0; row < ConfDefs.BOARD_LENGTH; row++) {
			for (int col = 0; col < ConfDefs.BOARD_WIDTH; col++) {
				Point point = new Point(row, col);
				imgcomp = SquareImgFactory.buildSquare(SquareType.EMPTY);
				squares.add(new Square(point));
				board.add(imgcomp);
			}
		}

		/* place the board */
		constrains.anchor = GridBagConstraints.CENTER;
		constrains.fill = GridBagConstraints.NONE;
		constrains.gridwidth = 0;
		constrains.gridx = 1;
		constrains.gridy = 2;
		pane.add(board, constrains);
		constrains.gridwidth = 0;

		/* paint next move button */
		next = new JButton("Next Move");
		next.setBorder(BorderFactory.createEtchedBorder());
		next.addActionListener(nextMoveListener());

		/* paint the score status */
		scoreStat = new JLabel("Score: " + score);
		scoreStat.setBorder(BorderFactory.createEtchedBorder());
		scoreStat.setBackground(Color.WHITE);
		scoreStat.setFont(scoreStat.getFont().deriveFont(Font.PLAIN));
		scoreStat.setHorizontalAlignment(JLabel.RIGHT);

		/* paint the statusbar */
		JPanel statusbar = new JPanel(new GridLayout());
		statusbar.add(next);
		statusbar.add(scoreStat);

		/* place the statusbar */
		constrains.anchor = GridBagConstraints.PAGE_END;
		constrains.fill = GridBagConstraints.HORIZONTAL;
		constrains.gridx = 1;
		constrains.gridy = 4;
		pane.add(statusbar, constrains);
	}

	private ActionListener nextMoveListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				play();
			}
		};
	}

	private ActionListener newGameListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				reset();
			}
		};
	}

	private ActionListener aboutListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(getParent(),
							      "Created by:"
							      + "\nIvan c00kiemon5ter Kanakarakis"
							      + "\nPeriklis MasterEx Ntanasis",
							      "About", JOptionPane.PLAIN_MESSAGE);
			}
		};
	}

	private void reset() {
		/* reset button */
		for (ActionListener listener : next.getActionListeners()) {
			next.removeActionListener(listener);
		}
		next.setText("Next Move");
		next.addActionListener(nextMoveListener());
		/* reset squares */
		jessHandle.init();
		updateSquares();
		/* reset score */
		score = 0;
		updateScore();
	}

	private void play() {
//		System.out.println("==========================================");
//		System.out.printf("%s\n:: Explorer is at: %s looking: %s\n",
//				  squares.get(Utils.pointToIndex(explorerPosition)).toStringWithHeader(),
//				  Utils.toBoardNotation(explorerPosition),
//				  explorerDirection.toString());
		move(evaluate());
		updateSquares();
		updateScore();
//		System.out.printf("%s\n:: Explorer is at: %s looking: %s\n",
//				  squares.get(Utils.pointToIndex(explorerPosition)).toStringWithHeader(),
//				  Utils.toBoardNotation(explorerPosition),
//				  explorerDirection.toString());
//		System.out.println("==========================================");
		checkState();
	}

	private void updateScore() {
		EnumSet<SquareType> typeset = squares.get(Utils.pointToIndex(explorerPosition)).getTypes();
		for (SquareType type : typeset) {
			score += type.getScore();
		}
		scoreStat.setText("Score: " + score);
	}

	private void updateSquares() {
		jessHandle.updateSquareInfo(squares);
		explorerDirection = jessHandle.getExplorerDirection();
		explorerPosition = jessHandle.getExplorerPosition();
		for (Square square : squares) {
			setSquare(square.getPoint(), square.getTypes());
		}
		board.revalidate();
	}

	private void setSquare(Point point, EnumSet<SquareType> types) {
		int index = Utils.pointToIndex(point);
		ImageComponent imgcomp = SquareImgFactory.buildSquare(types);
		board.remove(index);
		board.add(imgcomp, index);
	}

	private Entry<Direction, Square> evaluate() {
		Set<Entry<Direction, Square>> surroundings = Utils.getExplorerEnvironment(squares, explorerPosition).entrySet();
		List<Entry<Direction, Square>> exploredSafe = new ArrayList<Entry<Direction, Square>>(surroundings.size());
		List<Entry<Direction, Square>> unExploredSafe = new ArrayList<Entry<Direction, Square>>(surroundings.size());
		List<Entry<Direction, Square>> unExploredMaybeSafe = new ArrayList<Entry<Direction, Square>>(surroundings.size());
		List<Entry<Direction, Square>> unSafe = new ArrayList<Entry<Direction, Square>>(surroundings.size());

		for (Entry<Direction, Square> surrounding : surroundings) {
			Entry<Direction, Square> entry = new SimpleEntry<Direction, Square>(
				surrounding.getKey(), surrounding.getValue());
			Square square = entry.getValue();
			if (square.isOfType(SquareType.VISITED)) {
				exploredSafe.add(entry);
			} else if (square.isOfType(SquareType.SAFE)) {
				unExploredSafe.add(entry);
			} else if (square.isOfType(SquareType.MAYBEPIT)
					   || square.isOfType(SquareType.MAYBEWUMPUS)) {
				unExploredMaybeSafe.add(entry);
			} else {
				unSafe.add(entry);
			}
		}

		Random randomizer = new Random();
		if (!unExploredSafe.isEmpty()) {
			return unExploredSafe.get(randomizer.nextInt(unExploredSafe.size()));
		}
		if (!unExploredMaybeSafe.isEmpty()) {
			return unExploredMaybeSafe.get(randomizer.nextInt(unExploredMaybeSafe.size()));
		}
		if (!exploredSafe.isEmpty()) {
			return exploredSafe.get(randomizer.nextInt(exploredSafe.size()));
		}
		return unSafe.get(randomizer.nextInt(unSafe.size()));
	}

	private void move(Entry<Direction, Square> nextMove) {
		Direction direction = nextMove.getKey();
		explorerPosition = nextMove.getValue().getPoint();

		if (direction != explorerDirection) {
			shiftDirection(direction);
		}
		jessHandle.move(explorerDirection, explorerPosition);
		--score;
	}

	/**
	 * <p>turn player to the right direction<br/>
	 * distance is a metric that holds info for:<ul>
	 * <li>the turn direction to take - left or right
	 * <li>the number of times to repeat the turn action - one or two</ul>
	 * The distance is implemented as
	 * <pre>    distance = currentPositionId - newPositionId;</pre>
	 * This is stored in a signed number.<br/>
	 * The sign reveals whether the turn should be<ul>
	 * <li>to the right - negative sign or
	 * <li>to the left - positive sign</ul>
	 * The number reveals how many time it is needed to repeat the turning</p>
	 * <p><pre>
	 *            UP(0)
	 * LEFT(3)  explorer  RIGHT(1)
	 *           DOWN(2)
	 * </pre></p>
	 *
	 * @param direction - the new direction
	 * @see Direction
	 */
	private void shiftDirection(Direction direction) {
		int distance = explorerDirection.id() - direction.id();
		int absdistance = Math.abs(distance);
		if (absdistance > 2) {
			distance -= Integer.signum(distance) * 4;
			absdistance = Math.abs(distance);
		}
		for (int turns = 0; turns < absdistance; turns++) {
			explorerDirection = Integer.signum(distance) == -1
					    ? Direction.right(explorerDirection)
					    : Direction.left(explorerDirection);
			jessHandle.turn(explorerDirection);
		}
		score -= absdistance;
	}

	private void checkState() {
		Square current = squares.get(Utils.pointToIndex(explorerPosition));
		boolean endOfGame = false;

		if ((endOfGame = current.isOfType(SquareType.PIT))) {
			JOptionPane.showMessageDialog(this, "Fell into the pit",
						      "Pit!", JOptionPane.PLAIN_MESSAGE);
		}
		if ((endOfGame = current.isOfType(SquareType.WUMPUS))) {
			JOptionPane.showMessageDialog(this, "Eaten by the wumpus",
						      "Wumpus!", JOptionPane.PLAIN_MESSAGE);
		}
		if ((endOfGame = current.isOfType(SquareType.GOLD))) {
			JOptionPane.showMessageDialog(this, "Gold Found!",
						      "Gold!", JOptionPane.PLAIN_MESSAGE);
		}

		if (endOfGame) {
			for (ActionListener listener : this.next.getActionListeners()) {
				this.next.removeActionListener(listener);
			}
			this.next.setText("Play again");
			next.addActionListener(newGameListener());
		}
	}
}
