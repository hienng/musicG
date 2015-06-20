package matrixDistance;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Hien Nguyen, Daniel Hudlet
 * Sheet 8, Ex 1b
 */


public class matrixDistance {

	public static void main(String[] args) {
		int[] x = {0, 0, 1, 4, 7, 14, 26, 23, 8, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] y = {0, 0, 0, 0, 0, 0, 1, 5, 6, 13, 25, 24, 9, 4, 2, 1, 0, 0, 0, 0};
//		int[] x = new int[] { 1, 2, 2, 3, 4, 4, 1, 0 };
//		int[] y = new int[] { 0, 1, 2, 2, 3, 4, 3, 2, 1 };
		int[][] distanceMatrix = getDistanceMatrix(x, y);
		
//		print2Tex(distanceMatrix);
		print2dMatrix(distanceMatrix);
		System.out.println("Shortest Path: " + getShortestPath(distanceMatrix));
	}
	
	/**
	 * calculate distance matrix 
	 * @param x array
	 * @param y array
	 * @return d(x,y) = |x-y|
	 */	
	public static int[][] getDistanceMatrix(int[] x, int[] y) {
		int[][] distanceMatrix = new int[x.length][y.length];
		
		for(int i = 0; i < x.length; i++) {
			for(int j = 0; j < y.length; j++) {
				distanceMatrix[i][j] = Math.abs(x[i] - y[j]);
			}
		}
		return distanceMatrix;
	}
	
	/**
	 * prints a matrix
	 * @param m 2d array
	 */	
	public static void print2dMatrix(int[][] m) {
		for(int i = 0; i < m.length; i++) {
			for(int j = 0; j < m[i].length; j++) {
				System.out.printf("%4d ", m[i][j]);
			}
			System.out.println("");
		}
	}
	
	/**
	 * prints matrix in latex friendly format
	 * @param m matrix
	 */	
	public static void print2Tex(int[][] m) {
		for(int i = 0; i < m.length; i++) {
			System.out.print(" & ");
			for(int j = 0; j < m[i].length; j++) {
				if(j == m[i].length-1) {
					System.out.print(m[i][j]);
				} else {
					System.out.print(m[i][j] + " & ");
				}
			}
			System.out.println("\\" + "\\");
		}
	}
	
	/**
	 * get the value from matrix m at x and y
	 * @param m matrix
	 * @param x x position
	 * @param y y position
	 * @return value m[x][y]
	 */
	private static int getValue(int[][] m, int x, int y) {
		return (x < m.length && y < m[x].length) ? m[x][y] : Integer.MAX_VALUE;
	}
	
	/**
	 * determine the minimum of 3 values
	 * @param v1 value 1
	 * @param v2 value 2
	 * @param v3 value 3
	 * @return min(v1,v2,v3)
	 */
	private static int min(int v1, int v2, int v3) {
		return (v1 < v2) ? ((v1 < v3) ? v1 : v3) : ((v2 < v3) ? v2 : v3);
	}
	
	/**
	 * find the shortest path for matrix m with n x n - dimensions, so, (0,0) -- (n,n)
	 * preferably we move downright 
	 * @param m
	 * @return list of pairs of coordinates of shortest path from top-left (0,0) to bottom-right (n,n)
	 * i.e read (2,3) as go 2 down and 3 right
	 */
	public static List<List<Integer>> getShortestPath(int[][] m) {
		List<List<Integer>> path = new LinkedList<List<Integer>>();
		int x = 0, y = 0;
		while(x < m.length && y < m[x].length) {
			path.add(Arrays.asList(x, y));
			int down = getValue(m, x, y+1);
			int right = getValue(m, x+1, y);
			int downright = getValue(m, x+1, y+1); 
			int min = min(right, down, downright);
			
			if(downright == min) {
				x++; 
				y++;
			} else if(down == min) {
				y++;
			} else {
				x++;
			}
		}
		return path;
	}

}
