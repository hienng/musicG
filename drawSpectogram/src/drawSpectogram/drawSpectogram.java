package drawSpectogram;

import com.musicg.wave.Wave;
import com.musicg.wave.extension.Spectrogram;

import ij.IJ;
import ij.ImagePlus;

/**
 * Sheet 8, ex 2
 * @author Daniel Hudlet, Hien Nguyen
 * 
 */
public class drawSpectogram {

	public static void main(String[] args) {
		ImagePlus imp1 = new ImagePlus();
		ImagePlus imp2 = new ImagePlus();
		ImagePlus imp3 = new ImagePlus();
		ImagePlus imp4 = new ImagePlus();
		imp1 = IJ.createImage("Spektogram_coldplay","8G Black", 308, 256, 16);
		imp2 = IJ.createImage("Spektogram_gotorio","8G Black", 308, 256, 16);
		imp3 = IJ.createImage("Spektogram_ritmo","8G Black", 308, 256, 16);
		imp4 = IJ.createImage("Spektogram_top_otw","8G Black", 308, 256, 16);
		
		Wave wave_coldplay = new Wave ("waves/coldplay.wav");
		Wave wave_gotorio = new Wave ("waves/gotorio.wav");
		Wave wave_ritmo = new Wave ("waves/ritmo.wav");
		Wave wave_top_otw = new Wave ("waves/top_of_the_world_7.wav");
		Spectrogram sp1 = wave_coldplay.getSpectrogram();
		Spectrogram sp2 = wave_gotorio.getSpectrogram();
		Spectrogram sp3 = wave_ritmo.getSpectrogram();
		Spectrogram sp4 = wave_top_otw.getSpectrogram();
		
		double[][] d1 = sp1.getNormalizedSpectrogramData();
		double[][] d2 = sp2.getNormalizedSpectrogramData();
		double[][] d3 = sp3.getNormalizedSpectrogramData();
		double[][] d4 = sp4.getNormalizedSpectrogramData();
		
		for(int x = 0; x < 308; x++){
			for(int y = 0; y < 256; y++){
				int[] tmp = new int[1];
				tmp[0] = (int) (d1[x][y]*255);
				imp1.getProcessor().putPixel(x,y,tmp);
				tmp[0] = (int) (d2[x][y]*255);
				imp2.getProcessor().putPixel(x,y,tmp);
				tmp[0] = (int) (d3[x][y]*255);
				imp3.getProcessor().putPixel(x,y,tmp);
				tmp[0] = (int) (d4[x][y]*255);
				imp4.getProcessor().putPixel(x,y,tmp);
			}
		}
		
		IJ.save(imp1, "waves/out/spectogram_coldplay.jpg");
		IJ.save(imp2, "waves/out/spectogram_gotorio.jpg");
		IJ.save(imp3, "waves/out/spectogram_ritmo.jpg");
		IJ.save(imp4, "waves/out/spectogram_topofhteworld.jpg");
		
		System.out.println("Creation of Images completed.");
	}

}
