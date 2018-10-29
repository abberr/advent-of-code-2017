package day13;

import java.io.IOException;

import util.Util;

public class Day13 {

	private static int solveP1(String[] input) {
		int severity = 0;
		for (int i = 0; i < input.length; i++) {
			int layerNumber = getLayerNumber(input[i]);
			int range = getRange(input[i]);
			if (isDetected(layerNumber, range , 0)) {
				severity += layerNumber * range;
			}
		}

		return severity;
	}	
	
	private static int solveP2(String[] input) {
		int delay = 0;
		while (true) {
			boolean detected = false;
			for (int i = 0; i < input.length; i++) {
				int layerNumber = getLayerNumber(input[i]);
				int range = getRange(input[i]);
				if (isDetected(layerNumber, range , delay)) {
					detected = true;
					break;
				}
			}	
			if (!detected) {
				return delay;
			}
			delay++;			
		}
	}	

	private static int getRange(String input) {
		return Integer.parseInt(input.substring(input.indexOf(":") + 2));
	}

	private static int getLayerNumber(String input) {
		return Integer.parseInt(input.substring(0, input.indexOf(":")));
	}	

	private static boolean isDetected(int layerNumber, int layerRange, int delay) {
		int roundtripSteps = (layerRange - 1) * 2;
		int scannerPos = (layerNumber + delay) % roundtripSteps;
		return scannerPos == 0;
		
	}

	public static void main(String[] args) throws IOException {
		System.out.println(solveP1(Util.readInput("day13")));
		System.out.println(solveP2(Util.readInput("day13")));
	}

}
