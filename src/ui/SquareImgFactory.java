package ui;

import core.SquareType;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

/**
 * Square representation. 
 * This class provides a square factory for easy building of any type of square.
 * 
 * @author c00kiemon5ter
 */
public class SquareImgFactory {

	public static ImageComponent buildSquare(EnumSet<SquareType> typeset) {
		List<Image> images = new ArrayList<Image>(typeset.size());
		for (SquareType type : typeset) {
			images.add(type.getImage());
		}
		Collections.sort(images, new Comparator<Image>() {

			@Override
			public int compare(Image a, Image b) {
				return Integer.valueOf(SquareType.getPriority(a)).
					compareTo(
					Integer.valueOf(SquareType.getPriority(b)));
			}
		});
		return new ImageComponent(images);
	}

	public static ImageComponent buildSquare(SquareType type) {
		return new ImageComponent(type.getImage());
	}
}
