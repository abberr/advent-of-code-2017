package day16;

import java.io.IOException;

import util.Util;

public class Day16 {

	private static final int NUMBER_OF_DANCES = 1000000000;

	public static void solveP1(char [] programs, Move [] moves) {
		for (Move move : moves) {
			move.doMove(programs);
		}
	}
	
	public static String solveP2(char [] programs, Move [] moves) {		
		int cycleLength = findCycleLength(programs, moves);		
		for (int i = 0; i < NUMBER_OF_DANCES % cycleLength; i++) {
			solveP1(programs, moves);
		}
		
		return new String(programs);
	}
	
	private static int findCycleLength(char [] programs, Move [] moves) {
		char [] first = new char[programs.length];
		System.arraycopy(programs, 0, first, 0, programs.length);
		solveP1(programs, moves);
		int cycleLength = 1;
		while (!isEqual(first, programs)) {	
			solveP1(programs, moves);
			cycleLength++;
		}
		return cycleLength;
	}
	
	private static boolean isEqual(char [] a, char [] b) {
		for (int i = 0; i < b.length; i++) {			
			if (a[i] != b[i]) {
				return false;				
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		String[] input = Util.readInput("day16")[0].split(",");
		
		Move [] moves = new Move[input.length];
		for (int i = 0; i < moves.length; i++) {
			moves[i] = Move.creatMove(input[i]);
		}
		
		char [] programs = new char[16];
		for (int i = 0; i < programs.length; i++) {
			programs[i] =(char)('a' + i);
		}
//		solveP1(programs, moves);
		solveP2(programs, moves);
		System.out.println(new String(programs));
	}

}
