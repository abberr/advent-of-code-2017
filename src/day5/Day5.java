package day5;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.Util;


public class Day5 {

	public static int solveP1(String [] input) {
		int [] in = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			in[i] = Integer.parseInt(input[i]);
		}
		int steps = 0;
		int i = 0;
		while (i >= 0 && i < in.length) {
			in[i]++;
			i += in[i] - 1;
			steps++;
		}
		
		return steps;
	}
	
	public static int solveP2(String [] input) {
		int [] in = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			in[i] = Integer.parseInt(input[i]);
		}
		int steps = 0;
		int i = 0;
		while (i >= 0 && i < in.length) {
			if (in[i] >= 3) {
				in[i]--;
				i += in[i] + 1;
			}
			else {
				in[i]++;
				i += in[i] - 1;				
			}
			steps++;
		}
				
		return steps;
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(solveP1(Util.readInput("day5")));
		System.out.println(solveP2(Util.readInput("day5")));
	}
}
