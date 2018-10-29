package day6;

import java.io.IOException;
import java.util.ArrayList;

import util.Util;

public class Day6
{
	
	private static int answer_P2;
	
	public static int solveP2(String[] input) {
		solveP1(input);
		return answer_P2;
	}

	public static int solveP1(String[] input)
	{
		
		int[] in = Util.stringArrayToIntArray(input[0].split("\t"));
		ArrayList<int[]> configs = new ArrayList<int[]>();
		int cycles = 0;
		while (true)
		{
			int i = findMaxIndex(in);
			int blocks = in[i];
			in[i] = 0;
			while (blocks > 0)
			{
				i = (i + 1) % in.length;
				in[i]++;
				blocks--;
			}
			cycles++;

			if (containsArray(configs, in))
			{
				return cycles;
			}

			int[] config = new int[in.length];
			System.arraycopy(in, 0, config, 0, in.length);
			configs.add(config);
		}
	}

	private static int findMaxIndex(int[] input)
	{
		int max = -1;
		int maxIndex = 0;
		for (int i = 0; i < input.length; i++)
		{
			if (input[i] > max)
			{
				max = input[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	private static boolean containsArray(ArrayList<int[]> arrayList, int[] array)
	{
		for (int i = 0; i < arrayList.size(); i++)
		{
			int[] compareArray = arrayList.get(i);
			boolean isEqual = true;
			for (int j = 0; j < compareArray.length; j++)
			{
				if (array[j] != compareArray[j])
				{
					isEqual = false;
				}
			}
			if (isEqual)
			{
				answer_P2 = (arrayList.size() - i);
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) throws IOException
	{
		System.out.println(solveP1(Util.readInput("day6")));
		System.out.println(solveP2(Util.readInput("day6")));
//		System.out.println("P1: " + solveP1andP2(Util.readInput("day6")[0].split("\t")));
	}

}
