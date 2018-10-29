package Day17;

import java.util.LinkedList;

public class Day17 {
	
	public static final int INTERATIONS_P1 = 2017;
	public static final int INTERATIONS_P2 = 50000000;
	
	public static int solveP1(int stepSize) {
		LinkedList<Integer> buffer = new LinkedList<>();
		buffer.add(0);
		int position = 0;
		for (int i = 1; i < INTERATIONS_P1 + 1; i++) {
			position = ((position + stepSize + 1) % buffer.size());
			buffer.add(position, i);
		}
		return buffer.get(buffer.indexOf(INTERATIONS_P1) + 1);
	}
	
	public static int solveP2(int stepSize) {		
		int position = 0;
		int valueAfterZero = -1;
		for (int i = 1; i <= INTERATIONS_P2; i++)
		{
			position = ((position + stepSize) % i) + 1;
			if (position == 1) {
				valueAfterZero = i;
			}
		}
		
		return valueAfterZero;
	}

	public static void main(String[] args) {
		int input = 386;
		System.out.println(solveP1(input));
		System.out.println(solveP2(input));
	}

}
