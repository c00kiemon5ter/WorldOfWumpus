package worldofwumpus;

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
