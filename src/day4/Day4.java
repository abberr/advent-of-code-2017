package day4;

import java.io.IOException;

import util.Util;

public class Day4 {
	
	
	public static int solveP1(String [] input) {
		int validPassphrases = 0;

		for (int i = 0; i < input.length; i++) {
			boolean valid = true;
			String[] parts = input[i].split(" ");
			for (int j = 0; j < parts.length; j++) {
				for (int j2 = 0; j2 < parts.length; j2++) {
					if (j != j2) {
						if (parts[j].equals(parts[j2])) {
							valid = false;
						}
					}
				}
			}
			if (valid) {
				validPassphrases++;
			}
		}

		return validPassphrases;
	}
	
	public static int solveP2(String [] input) {
		int validPassphrases = 0;
		
		for (int i = 0; i < input.length; i++) {
			boolean valid = true;
			String[] parts = input[i].split(" ");
			for (int j = 0; j < parts.length; j++) {
				for (int j2 = 0; j2 < parts.length; j2++) {
					if (j != j2) {
						if (isAnagram(parts[j], parts[j2])) {
							valid = false;
						}
					}
				}
			}
			if (valid) {
				validPassphrases++;
			}
		}
		
		return validPassphrases;
	}
	
	private static boolean isAnagram(String s1, String s2) {
		int[] letters = new int[26];
		if (s1.length() != s2.length()) {
			return false;
		}
		for (int i = 0; i < s1.length(); i++) {
			letters[s1.charAt(i) - 'a']++;
			letters[s2.charAt(i) - 'a']--;
		}

		return allElementsZero(letters);
	}

	private static boolean allElementsZero(int[] in) {
		for (int i = 0; i < in.length; i++) {
			if (in[i] != 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(solveP1(Util.readInput("day4")));
		System.out.println(solveP2(Util.readInput("day4")));
	}

	

}
