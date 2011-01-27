package configuration;

import core.SquareType;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author c00kiemon5ter
 */
public class WorldWriter {

	public void write(List<ConfLine> conflines) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(ConfDefs.WORLD)));
		StringBuilder worldline = new StringBuilder();
		for (int cnt = 0; cnt < conflines.size(); cnt++) {
			ConfLine confline = conflines.get(cnt);
			StringBuilder type = new StringBuilder(confline.getType().toString().toLowerCase());
			type.setCharAt(0, Character.toUpperCase(type.charAt(0)));
			worldline.append(String.format("(deffacts %s%s (%s (xpos %d) (ypos %d) ",
						       confline.getType().toString(), String.valueOf(cnt),
						       type, confline.getPoint().x, confline.getPoint().y));
			if (confline.getType() == SquareType.EXPLORER) {
				worldline.append(String.format("(dir %d)", confline.getDirection().id()));
			}
			worldline.append("))\n");
		}
		writer.write(worldline.toString());
		writer.flush();
		writer.close();
	}
}
