package day3;

import java.io.IOException;

import util.Util;

public class Day3 {
	
	public static int solveP1(String input) {
		int in = Integer.parseInt(input);
		int layer = 1;
		
		while (true) {	
			if (in <= layer*layer) {
				break;
			}
			layer += 2;			
		}
		
		int squaresInLayer = layer*layer - (layer-2)*(layer-2);
		int squaresInSide = squaresInLayer/4;		
		int squareNumberInLayer = layer*layer - in;
		int squareNumberInSide = squareNumberInLayer%squaresInSide;		
		int distanceFromCenterOfSide = Math.abs(squareNumberInSide-(squaresInSide/2));		
		int distanceFromCenter = (layer/2) + distanceFromCenterOfSide;
		
		return distanceFromCenter;
	}
	
	//TODO asdasd
	public static int solveP2(String input) {
		int in = Integer.parseInt(input);		
		int [][] board = new int[20][20];
		
		return 0;
	}

	public static void main(String[] args) throws IOException {
		String input = Util.readInput("day3")[0];		
		System.out.println(solveP1(input));
		System.out.println(solveP2("45"));
//		System.out.println(solveP2(input));
	}

}
