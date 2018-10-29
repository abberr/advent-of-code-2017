package day25;

import java.io.IOException;
import java.util.ArrayList;

import util.Util;

public class Day25
{
	public static int solveP1(String[] input)
	{
		ArrayList<Integer> indexOfOnes = new ArrayList<>();
		int iterations = Integer.parseInt(input[1].split(" ")[5]);
		Integer cursor = 0;
		char state = 'A';
		for (int i = 0; i < iterations ; i++)
		{
			switch (state)
			{
			case 'A':
				if (!indexOfOnes.contains(cursor))
				{
					if (!indexOfOnes.contains(cursor)) {
						indexOfOnes.add(cursor);						
					}
					cursor++;
					state = 'B';
				} else
				{
					indexOfOnes.remove(cursor);
					cursor--;
					state = 'F';
				}
				break;
			case 'B':
				if (!indexOfOnes.contains(cursor))
				{
					indexOfOnes.remove(cursor);
					cursor++;
					state = 'C';
				} else
				{
					indexOfOnes.remove(cursor);
					cursor++;
					state = 'D';
				}
				break;
			case 'C':
				if (!indexOfOnes.contains(cursor))
				{
					if (!indexOfOnes.contains(cursor)) {
						indexOfOnes.add(cursor);						
					}
					cursor--;
					state = 'D';
				} else
				{
					if (!indexOfOnes.contains(cursor)) {
						indexOfOnes.add(cursor);						
					}
					cursor++;
					state = 'E';
				}
				break;
			case 'D':
				if (!indexOfOnes.contains(cursor))
				{
					indexOfOnes.remove(cursor);
					cursor--;
					state = 'E';
				} else
				{
					indexOfOnes.remove(cursor);
					cursor--;
					state = 'D';
				}
				break;
			case 'E':
				if (!indexOfOnes.contains(cursor))
				{
					indexOfOnes.remove(cursor);
					cursor++;
					state = 'A';
				} else
				{
					if (!indexOfOnes.contains(cursor)) {
						indexOfOnes.add(cursor);						
					}
					cursor++;
					state = 'C';
				}
				break;
			case 'F':
				if (!indexOfOnes.contains(cursor))
				{
					if (!indexOfOnes.contains(cursor)) {
						indexOfOnes.add(cursor);						
					}
					cursor--;
					state = 'A';
				} else
				{
					indexOfOnes.remove(cursor);
					cursor++;
					state = 'A';
				}
				break;
			}
		}

		return indexOfOnes.size();
	}

//	private void execute(boolean a_writeOne, int a_direction, char a_state, boolean b_writeOne, int b_direction, char b_state, ArrayList<Integer> indexOfOnes)
//	{
//		if (indexOfOnes.contains(cursor))
//		{
//			indexOfOnes.add(cursor);
//			cursor++;
//			state = 'B';
//		} else
//		{
//			indexOfOnes.remove(cursor);
//			cursor++;
//			state = 'B';
//		}
//	}

	public static void main(String[] args) throws IOException
	{
		String[] input = Util.readInput("day25");
		System.out.println(solveP1(input));

	}

}
