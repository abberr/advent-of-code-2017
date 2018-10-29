package day1;

import java.io.IOException;

import util.Util;

public class Day1 {
	
	private static final String URL = "http://adventofcode.com/2017/day/1/input";
	
	public static int solveP1(String[] input) {
		int sum = 0;
		char [] inputArray = input[0].toCharArray();		
		for (int i = 0; i < inputArray.length; i++) {
			char current = inputArray[i];
			char next = inputArray[(i+1)%inputArray.length];
			if (current == next) {
				sum += current-'0'; 
			}
		}
		
		return sum;
	}
	public static int solveP2(String[] input) {
		int sum = 0;
		char [] inputArray = input[0].toCharArray();		
		for (int i = 0; i < inputArray.length; i++) {
			char current = inputArray[i];
			char next = inputArray[(i+inputArray.length/2)%inputArray.length];
			if (current == next) {
				sum += current-'0'; 
			}
		}
		
		return sum;
	}
	
	
	public static void main(String[] args) throws IOException {
		String [] input = Util.readInput("day1");
		System.out.println(solveP1(input));
		System.out.println(solveP2(input));
	}

}
