package day8;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import util.Util;

public class Day8 {

	public static int solveP1(String[] lines) {
		HashMap<String, Integer> registers = new HashMap<String, Integer>();
		int highestValue = Integer.MIN_VALUE;

		for (String line : lines) {
			String r1 = line.split(" ")[0];
			String r2 = line.split(" ")[4];
			addRegisters(registers, r1, r2);
			int changeValue =  Integer.parseInt(line.split(" ")[2]);
			if (line.split(" ")[1].equals("dec")) {
				changeValue = -changeValue;
			}
			String condition = line.split(" ")[5];
			int compareValue = Integer.parseInt(line.split(" ")[6]);			
			if (evaluate(registers.get(r2), condition, compareValue)) {
				int newValue = registers.get(r1) + changeValue;
				registers.put(r1, newValue);
				if (newValue > highestValue) {
					highestValue = newValue;
				}
			}
		}

		System.out.println("Highest value: " + highestValue);
		return findMax(registers);
	}
	
	private static boolean evaluate(int value, String condition, int compareValue) {
		switch (condition) {
		case ">":
			return value>compareValue;
		case ">=":
			return value>=compareValue;
		case "==":
			return value==compareValue;
		case "<":
			return value<compareValue;
		case "<=":
			return value<=compareValue;
		case "!=":
			return value!=compareValue;
		default:
			throw new IllegalArgumentException("Invalid condition: " + condition);
		}
	}
	
	private static void addRegisters(Map<String, Integer> registers, String... reg) {
		for (String register : reg) {
			if (!registers.containsKey(register)) {
				registers.put(register, 0);
			}
		}		
	}

	private static int findMax(Map<String, Integer> registers) {
		int max = Integer.MIN_VALUE;
		for (Integer value : registers.values()) {
		    if (value > max) {
		    	max = value;
		    }
		}
		return max;
	}
	

	public static void main(String[] args) throws IOException {
		System.out.println(solveP1(Util.readInput("day8")));
	}
}
