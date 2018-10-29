package day21;

import java.io.IOException;

import util.Util;

public class Day21
{

	private static final int ITERATIONS_P1 = 5;
	private static final int ITERATIONS_P2 = 18;
	public static boolean[][] STARTING_PATTERN = { { false, true, false }, { false, false, true }, { true, true, true } };

	public static void main(String[] args) throws IOException
	{
		String[] input = Util.readInput("day21");
		System.out.println(solveP1(input));
		System.out.println(solveP2(input));
	}

	private static int solveP1(String[] input)
	{
		boolean[][] pattern = STARTING_PATTERN;
		for (int i = 0; i < ITERATIONS_P1; i++)
		{
			pattern = enhance(pattern, input);
		}
		return countTrue(pattern);
	}
	
	private static int solveP2(String[] input)
	{
		boolean[][] pattern = STARTING_PATTERN;
		for (int i = 0; i < ITERATIONS_P2; i++)
		{
			pattern = enhance(pattern, input);
		}
		return countTrue(pattern);
	}

	private static int countTrue(boolean[][] pattern)
	{
		int counter = 0;
		for (int i = 0; i < pattern.length; i++)
		{
			for (int j = 0; j < pattern.length; j++)
			{
				if (pattern[i][j]) {
					counter++;
				}
			}
		}
		return counter;
	}

	private static boolean[][] enhance(boolean input[][], String[] rules)
	{
		int size = input.length;
		int dividor;
		if (size % 2 == 0)
		{
			dividor = 2;
		} else
		{
			dividor = 3;
		}
		int newSize = size + size / dividor;
		boolean[][] enhancedImage = new boolean[newSize][newSize];

		// For each sub-image
		for (int i = 0; i < size / dividor; i++)
		{
			for (int j = 0; j < size / dividor; j++)
			{
				boolean[][] old = slice(input, i * dividor, j * dividor, dividor);
				boolean[][] enhancement = findEnhancement(old, rules);
				arrayCopy(enhancement, enhancedImage, j * (dividor + 1), i * (dividor + 1));
			}
		}

		return enhancedImage;
	}

	private static boolean[][] findEnhancement(boolean[][] old, String[] rules)
	{
		for (int i = 0; i < rules.length; i++)
		{
			boolean isMatch = false;
			boolean[][] rule = stringToArray(rules[i].split(" => ")[0]);
			if (rule.length == old.length)
			{
				// Rotate and flip 4 times
				for (int j = 0; j < 4; j++)
				{
					rotateMatrix(rule);
					if (isEqual(old, rule))
					{
						isMatch = true;
					}
					flipMatrix(rule);
					if (isEqual(old, rule))
					{
						isMatch = true;
					}
					flipMatrix(rule);
				}
			}
			if (isMatch)
			{
				return stringToArray(rules[i].split(" => ")[1]);
			}
		}
		return null;
	}

	private static boolean isEqual(boolean[][] a, boolean[][] b)
	{
		for (int i = 0; i < b.length; i++)
		{
			for (int j = 0; j < b.length; j++)
			{
				if (a[i][j] != b[i][j])
				{
					return false;
				}
			}
		}
		return true;
	}

	private static boolean[][] stringToArray(String input)
	{
		String[] rows = input.split("/");
		boolean[][] array = new boolean[rows.length][rows.length];
		for (int i = 0; i < array.length; i++)
		{
			for (int j = 0; j < array.length; j++)
			{
				if (rows[i].charAt(j) == '#')
				{
					array[i][j] = true;
				} else
				{
					array[i][j] = false;
				}
			}
		}
		return array;
	}

	private static boolean[][] slice(boolean[][] input, int y, int x, int size)
	{
		boolean[][] old = new boolean[size][size];
		for (int i = 0; i < old.length; i++)
		{
			for (int j = 0; j < old.length; j++)
			{
				old[i][j] = input[i + y][j + x];
			}
		}
		return old;
	}

	private static void arrayCopy(boolean[][] aSource, boolean[][] aDestination, int xDest, int yDest)
	{
		for (int i = 0; i < aSource.length; i++)
		{
			System.arraycopy(aSource[i], 0, aDestination[i + yDest], xDest, aSource[i].length);
		}
	}

	private static void rotateMatrix(boolean input[][])
	{
		int N = input.length;
		for (int x = 0; x < N / 2; x++)
		{
			for (int y = x; y < N - x - 1; y++)
			{
				boolean temp = input[x][y];
				input[x][y] = input[y][N - 1 - x];
				input[y][N - 1 - x] = input[N - 1 - x][N - 1 - y];
				input[N - 1 - x][N - 1 - y] = input[N - 1 - y][x];
				input[N - 1 - y][x] = temp;
			}
		}
	}

	private static void flipMatrix(boolean input[][])
	{
		int n = input.length;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n / 2; j++)
			{
				boolean temp = input[i][j];
				input[i][j] = input[i][n - 1 - j];
				input[i][n - 1 - j] = temp;
			}
		}
	}
}