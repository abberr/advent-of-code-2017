package day9;

import java.io.IOException;

import util.Util;

public class Day9 {
	
	private static int solveP1(String input) {
		char [] in = input.toCharArray();
		
		for (int i = 0; i < in.length; i++) {
			
		}
		
		return 0;
	}
	
	private static int scoreInGroup(char [] in, int depth, int index) {
		
		for (int i = index; i < in.length; i++) {
			
		}
		
		return 0;
	}
	
	private static int indexOfClosingTag(char [] in, int startIndex) {
		
		for (int i = startIndex; i < in.length; i++) {
			if (in[i] == '}') {
				return i;
			}
			else if (in[i] == '<') {
				
			}
		}
		
		return 0;		
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(solveP1(Util.readInput("day9")[0]));
	}

	
}
