package day12;

import java.io.IOException;
import java.util.ArrayList;

import util.Util;

public class Day12 {

	public static int solveP1(String[] input) {
		ArrayList<Integer> programs = new ArrayList<>();
		findPrograms(input, programs, 0);
		return programs.size();
	}
	
	public static int solveP2(String[] input) {
		ArrayList<Integer> programs = new ArrayList<>();
		int groupCounter = 0;
		for (int i = 0; i < input.length; i++) {
			if (!programs.contains(i)) {
				findPrograms(input, programs, i);
				groupCounter++;
			}
		}
		
		return groupCounter;
	}

	public static void findPrograms(String[] input, ArrayList<Integer> programs, int currentProgram) {
		int[] connectedPrograms = findConnectedPrograms(input[currentProgram]);
		for (int program : connectedPrograms) {
			if (!programs.contains(program)) {
				programs.add(program);
				findPrograms(input, programs, program);
			}
		}
	}

	private static int[] findConnectedPrograms(String row) {
		String [] connectedProgramsString = row.substring(row.indexOf('>') + 2).split(", ");
		int [] connectedPrograms = new int[connectedProgramsString.length];
		for (int i = 0; i < connectedPrograms.length; i++) {
			connectedPrograms[i] = Integer.parseInt(connectedProgramsString[i]);
		}
		return connectedPrograms;
	}

	public static void main(String[] args) throws IOException {
		String[] input = Util.readInput("day12");
		System.out.println(solveP1(input));
		System.out.println(solveP2(input));
	}
}
