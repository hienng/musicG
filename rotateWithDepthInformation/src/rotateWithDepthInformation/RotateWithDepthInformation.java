package rotateWithDepthInformation;

import ij.ImagePlus;
import ij.IJ;
import ij.io.FileSaver;
import java.awt.Color;

public class RotateWithDepthInformation {
	
	/**
	 * calculate z-value: depth information from the heatmap
	 * @param r red component of a pixel
	 * @param g green component of a pixel
	 * @param b blue component of a pixel
	 * @return z-value
	 */
	public static int zValue(int r, int g, int b) {
		int res = 0;
		if(r < 255) {
			res = r + g + 255 - b;
		} else {
			res = r + 255 + 255 - g + 255 - b;
		}
		return 1020 - res;
	}
	
	/**
	 * shift axis-point / pixel at 45 degrees and add shift of z-value
	 * @param axis
	 * @param zVal 
	 * @return rotated z-value
	 */
	public static double rotate45degrees (int axis, int zVal) {
		return (Math.sqrt(axis*axis)/2) + (Math.sqrt(zVal * zVal)/2);
	}
	
	/**
	 * rotate an image in x/y - direction around 45 degrees
	 * @param orig original image
	 * @param heat heapmap with depth-information
	 * @return rotated image 
	 */
	public static ImagePlus rotate(ImagePlus orig, ImagePlus heat) {
		ImagePlus out = IJ.createImage("rotated", orig.getWidth(), orig.getHeight(), 1, orig.getBitDepth());
		if(orig.getWidth() == heat.getWidth() && orig.getHeight() == heat.getHeight()) {
			for(int x = 0; x < orig.getWidth(); x++) {
				for(int y = 0; y < orig.getHeight(); y++) {
					int[] rgb = heat.getPixel(x, y);
					int z = (zValue(rgb[0], rgb[1], rgb[2]) * 100) / 1020;
//					System.out.println(z);
					double newX = rotate45degrees(x, z);
					double newY = rotate45degrees(y, z);
					out.getProcessor().putPixel((int) newX, (int) newY, orig.getPixel(x, y));
				}
			}
		}
		return out;
	}
		
	public static void main(String[] args) {
		ImagePlus orig = IJ.openImage("Tiefenbilder/keyOrig.png");
		ImagePlus heatmap = IJ.openImage("Tiefenbilder/keyHeatmap.png");
		ImagePlus orig2 = IJ.openImage("Tiefenbilder/rulerOrig.png");
		ImagePlus heatmap2 = IJ.openImage("Tiefenbilder/rulerHeatmap.png");
		
		Color c = new Color(255, 85, 0);
		
		ImagePlus rotated = rotate(orig, heatmap);
		rotated.getProcessor().setColor(c);
		rotated.getProcessor().drawString("Hien & Daniel", 100, 500);
		rotated.show();
		new FileSaver(rotated).saveAsPng("Tiefenbilder/out/rotatedKey.png");

		ImagePlus rotated2 = rotate(orig2, heatmap2);
		rotated2.getProcessor().setColor(c);
		rotated2.getProcessor().drawString("Hien & Daniel", 200, 50);
		rotated2.show();
		new FileSaver(rotated2).saveAsPng("Tiefenbilder/out/rotatedRuler.png");

	}

}
