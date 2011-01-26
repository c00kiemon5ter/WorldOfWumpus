package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

/**
 * An image container-wrapper, giving images component attributes.
 *
 * @author c00kiemon5ter
 */
public class ImageComponent extends JComponent {

	private List<Image> images;
	private Dimension size;

	public ImageComponent(List<Image> images, Dimension size) {
		this.images = new ArrayList<Image>(images);
		this.size = new Dimension(size);
		init();
	}

	public ImageComponent(Image image, Dimension size) {
		this.images = new ArrayList<Image>(1);
		this.images.add(image);
		this.size = new Dimension(size);
		init();
	}

	public ImageComponent(List<Image> images, int width, int height) {
		this.images = new ArrayList<Image>(images);
		this.size = new Dimension(width, height);
		init();
	}

	public ImageComponent(Image image, int width, int height) {
		this.images = new ArrayList<Image>(1);
		this.images.add(image);
		this.size = new Dimension(width, height);
		init();
	}

	public ImageComponent(List<Image> images) {
		this.images = new ArrayList<Image>(images);
		this.size = new Dimension(images.get(0).getWidth(null),
					  images.get(0).getHeight(null));
		init();
	}

	public ImageComponent(Image image) {
		this.images = new ArrayList<Image>(1);
		this.images.add(image);
		this.size = new Dimension(image.getWidth(null),
					  image.getHeight(null));
		init();
	}

	private void init() {
		MediaTracker mt = new MediaTracker(this);
		for (int idx = 0; idx < images.size(); idx++) {
			mt.addImage(images.get(idx), idx);
		}
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
		}
		this.setSize(size);
	}

	@Override
	public void paint(Graphics g) {
		for (Image img : images) {
			g.drawImage(img, 0, 0, this);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return size;
	}
}
