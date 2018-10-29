package day2;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import util.Util;

public class Day2 {

	public static int solveP1(String[] input) {
		int sum = 0;
		for (int i = 0; i < input.length; i++) {
			String numbersString[] = input[i].split("\t");
			Integer[] numbers = new Integer[numbersString.length];
			for (int j = 0; j < numbersString.length; j++) {
				numbers[j] = Integer.parseInt(numbersString[j]);
			}
			
			int min = Collections.min(Arrays.asList(numbers));
			int max = Collections.max(Arrays.asList(numbers));
			sum += max-min;
		}
		
		return sum;		
	}
	public static int solveP2(String[] input) {
		int sum = 0;
		for (String line : input) {
			String numbersString[] = line.split("\t");
			Integer[] numbers = new Integer[numbersString.length];
			for (int i = 0; i < numbersString.length; i++) {
				numbers[i] = Integer.parseInt(numbersString[i]);
			}
			
			//Check if divisble with any other
			sum += quotientOfDivisibleNumbers(numbers);
			
			
		}
		
		return sum;	
	}
	
	private static int quotientOfDivisibleNumbers(Integer [] numbers) {
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers.length; j++) {
				if (i != j) {
					if (numbers[i] % numbers[j] == 0) {
						return numbers[i]/numbers[j];
					}
				}
			}
		}
		return 0;
	}
	
	
	public static void main(String[] args) throws IOException {
		String[] input = Util.readInput("day2");
		System.out.println(solveP1(input));
		System.out.println(solveP2(input));
	}

}
