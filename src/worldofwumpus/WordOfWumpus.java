package worldofwumpus;

import configuration.ConfDefs;
import configuration.ConfParser;
import configuration.WorldWriter;
import java.io.IOException;
import ui.GameUI;

/**
 *
 * @author c00kiemon5ter
 */
public class WordOfWumpus {

	public static void main(String[] args) throws IOException {
		ConfDefs.DEBUG = args.length != 0 && args[0].equals(ConfDefs.DEBUG_OPTION);
		new WorldWriter().write(new ConfParser().parse());
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				GameUI wow = new GameUI();
				wow.setVisible(true);
			}
		});
	}
}
