package util;

import java.util.ArrayList;
import java.util.List;

public class Soundbit extends Fingerprint {
	private String title;
	private List<int[]> peakList;
	
	/**
	 * Constructor
	 * @param s title and datafile name
	 */
	public Soundbit(String s) {
		super(s);
		title = s.substring(0, s.indexOf("."));
		peakList = new ArrayList<int[]>();
		generateTuples(5);
	}
	
	/**
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 
	 * @return peakList, which is the fingerprint
	 */
	public List<int[]> getPl() {
		return peakList;
	}
	
	/**
	 * generate peak-tuples of size n 
	 * for every peak we build the difference to n successors and divide by 10 i.e 
	 * peak0:
	 * tuple[0] = (peak 0 - peak 1) / 10 
	 * tuple[1] = (peak 0 - peak 2) / 10
	 * tuple[2] = (peak 0 - peak 3) / 10
	 * tuple[3] = (peak 0 - peak 4) / 10
	 * tuple[4] = (peak 0 - peak 5) / 10 
	 * and add this tuple to our peaklist
	 * next step: peak1:
	 * tuple[0] = (peak 1 - peak 2) / 10 
	 * tuple[1] = (peak 1 - peak 3) / 10 
	 * tuple[2] = (peak 1 - peak 4) / 10 
	 * tuple[3] = (peak 1 - peak 5) / 10 
	 * tuple[4] = (peak 1 - peak 6) / 10 
	 * and add to peaklist
	 * --> we get #peak.size()-5 tuples
	 * --> with #(peak.size()-5) * 5 peaks
	 * @param n
	 */
	
	private void generateTuples(int n) {
		for(int i = 0; i < fp.length-n; i++) {
			int[] tuple = new int[n];
			for(int j = 0; j < fp.length && j < n; j++) {
				tuple[j] = Math.abs(fp[i] - fp[(i + j) + 1]) / 10;
			}
			peakList.add(tuple);
		}
	}
	
//	public int compare(Soundbit other) {
//		int hits = 0;
//		for(int i = 0; i < other.getPl().size() && i < peakList.size(); i++) {
//			if(other.has(peakList.get(i))) {
//				hits++;
//			}
//		}
//		return hits;
//	}
	
	/**
	 * compares this soundbit to another, hits are increased when both soundbit tuples matches each other
	 * @param other the other soundbit
	 * @return hit/miss ratio, number in-between 0 and 1
	 */
	public float compare(Soundbit other) {
		int hits = 0, miss = 0;
		for(int[] peakTuple: peakList) {
			if(other.match(peakTuple)) {
				hits++;
			} else {
				miss++;
			}
		}
		return miss != 0 ? (float) hits/ (float) miss : 1;
	}
	/**
	 * checks if tuples matches
	 * example2 {1,2,3,4,5} == {1,2,3,4,5} ? => match
	 * example1 {1,2,3,4,5} == {1,3,2,5,4} ? => mismatch
	 * @param peakTuple tuple of 5 contains peaks to "mask" over
	 * @return true, if peak 
	 */
	private boolean match(int[] peakTuple) {
		int subhits  = 0;
		boolean matched = false;
		for(int i = 0; i < peakList.size(); i++) {
			for(int j = 0; j < peakTuple.length; j++) {
				if(peakList.get(i)[j] == peakTuple[j]) {
					subhits++;
				}
				if(subhits == peakTuple.length) {
					matched = true;
				}
			}
			// check if all peaks (subhits) are the same and if they are, matched is true
			subhits = 0;
		} // look for all tuples from this soundbit
		return matched;
	}
	
}
