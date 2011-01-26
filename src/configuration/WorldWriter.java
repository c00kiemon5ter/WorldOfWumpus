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

	public void write(List<ConfLine> objs) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(ConfDefs.WORLD)));
		StringBuilder str = new StringBuilder();
		for (int cnt = 0; cnt < objs.size(); cnt++) {
			ConfLine obj = objs.get(cnt);
			if (obj.getType() == SquareType.EXPLORER) {
				str.append(String.format("(deffacts %s (%s (xpos %d) (ypos %d) (dir %d)))\n",
							 obj.getType() + String.valueOf(cnt),
							 obj.getObjClassName(),
							 obj.getPoint().x, obj.getPoint().y,
							 obj.getDirection().id()));
				continue;
			}
			str.append(String.format("(deffacts %s (%s (xpos %d) (ypos %d)))\n",
						 obj.getType() + String.valueOf(cnt),
						 obj.getObjClassName(),
						 obj.getPoint().x, obj.getPoint().y));
		}
		writer.write(str.toString());
		writer.flush();
		writer.close();
	}
}
