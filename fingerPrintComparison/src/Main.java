import java.util.ArrayList;

import util.Soundbit;


public class Main {
	
	/* author: Hien Nguyen, Daniel Hudlet
	 * Sheet 7, ex. 3
	 * 
	 * Antwort: Nach Hoergefuehl passen Coldplay und Ritmo. 
	 * Zu beiden passt wiederrum kleines bisschen Gotorio.
	 * 
	 * Die Ergebnisse decken sich nur teilweise mit dem pers. Hoereindruck:
	 * So ist z.B. 
	 * Coldplay und Ritmo 					( ~5.5% - 5.8%) <- Hier passten Ergebnis und Hoereindruck
	 * Coldplay und Gotorio 				( ~2.0% - 2.2%)
	 * Coldplay und Top_of_the_world_7 		( ~1.6% - 1.8%)
	 * Gotorio  und Ritmo 					( ~2.3% - 2.2%)
	 * Gotorio  und Top_of_the_world_7		( ~2.6% - 2.8%)
	 * Ritmo 	und Top_of_the_world_7		( ~2.7%) 
	 * 
	 */
	
	public static void main(String[] args) {
					
		Soundbit coldplay = new Soundbit("coldplay.wav");
		Soundbit gotorio = new Soundbit("gotorio.wav");
		Soundbit ritmo = new Soundbit("ritmo.wav");
		Soundbit world = new Soundbit("top_of_the_world_7.wav");
		
		ArrayList<Soundbit> sb = new ArrayList<>();
		sb.add(coldplay);
		sb.add(ritmo);
		sb.add(gotorio);
		sb.add(world);
		
		for(Soundbit soundbits : sb) {
			for(Soundbit otherSoundbits: sb) {
				if(soundbits != otherSoundbits) {
					System.out.print(soundbits.getTitle() + " vs " + otherSoundbits.getTitle() + "\t\t\t");
					System.out.println(soundbits.compare(otherSoundbits) * 100 + "%");
				}
			}
		}
	}
}
