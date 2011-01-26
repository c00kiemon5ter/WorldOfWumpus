package configuration;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * 
 * @author c00kiemon5ter
 */
public class ConfDefs {

	/* board size */
	public static final int BOARD_LENGTH = 8, BOARD_WIDTH = 8;
	/* configuration files */
	public static final String CONF = "data/world.conf";
	public static final String RULES = "clips/rules/rules";
	public static final String WORLD = "clips/rules/world";
	/* images */
	public static final Image COLS_IMG = Toolkit.getDefaultToolkit().getImage("src/ui/icons/cols.png");
	public static final Image ROWS_IMG = Toolkit.getDefaultToolkit().getImage("src/ui/icons/rows.png");
	private static final int imagewidth = 22, imageheight = 22;
	public static final Image EXPLORER_IMG = Toolkit.getDefaultToolkit().getImage("src/ui/icons/explorer.png").getScaledInstance(imagewidth, imageheight, Image.SCALE_SMOOTH);
	public static final Image BREEZE_IMG = Toolkit.getDefaultToolkit().getImage("src/ui/icons/breeze.png").getScaledInstance(imagewidth, imageheight, Image.SCALE_SMOOTH);
	public static final Image SMELL_IMG = Toolkit.getDefaultToolkit().getImage("src/ui/icons/smell.png").getScaledInstance(imagewidth, imageheight, Image.SCALE_SMOOTH);
	public static final Image SHINE_IMG = Toolkit.getDefaultToolkit().getImage("src/ui/icons/gold.png").getScaledInstance(imagewidth, imageheight, Image.SCALE_SMOOTH);
	public static final Image PIT_IMG = Toolkit.getDefaultToolkit().getImage("src/ui/icons/pit.png").getScaledInstance(imagewidth, imageheight, Image.SCALE_SMOOTH);
	public static final Image WUMPUS_IMG = Toolkit.getDefaultToolkit().getImage("src/ui/icons/wumpus.png").getScaledInstance(imagewidth, imageheight, Image.SCALE_SMOOTH);
	public static final Image GOLD_IMG = Toolkit.getDefaultToolkit().getImage("src/ui/icons/gold.png").getScaledInstance(imagewidth, imageheight, Image.SCALE_SMOOTH);
	public static final Image EMPTY_IMG = Toolkit.getDefaultToolkit().getImage("src/ui/icons/empty.png").getScaledInstance(imagewidth, imageheight, Image.SCALE_SMOOTH);
	public static final Image VISITED_IMG = Toolkit.getDefaultToolkit().getImage("src/ui/icons/visited.png").getScaledInstance(imagewidth, imageheight, Image.SCALE_SMOOTH);
	public static final Image UNKNOWN_IMG = Toolkit.getDefaultToolkit().getImage("src/ui/icons/unknown.png").getScaledInstance(imagewidth, imageheight, Image.SCALE_SMOOTH);
	public static final Image UNSAFE_IMG = Toolkit.getDefaultToolkit().getImage("src/ui/icons/unsafe.png").getScaledInstance(imagewidth, imageheight, Image.SCALE_SMOOTH);
}
