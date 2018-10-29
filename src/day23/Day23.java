package day23;

import java.io.IOException;
import util.Util;

public class Day23
{
	public static int solveP1(String[] input)
	{
		int mulCounter = 0;
		int[] registers = new int[8];
		int index = 0;
		while (index < input.length && index >= 0)
		{
			String operation = input[index].substring(0, 3);
			int registerIndex = input[index].charAt(4) - 'a';
			int value = getValue(registers, input[index].substring(6));
			if (operation.equals("set"))
			{
				registers[registerIndex] = value;

			} else if (operation.equals("sub"))
			{
				registers[registerIndex] -= value;

			} else if (operation.equals("mul"))
			{
				mulCounter++;
				registers[registerIndex] *= value;

			} else if (operation.equals("jnz"))
			{
				if (getValue(registers, input[index].substring(4, 5)) != 0)
				{
					index += value;
					index--;
				}
			}
			index++;
		}
		return mulCounter;
	}

	public static int solveP2(String[] input)
	{
        int b = (Integer.parseInt(input[0].substring(6)) * 100) + 100000;
        int c = b + 17000; 
        int numberOfNonPrimes = 0;

        for (int i = b; i <= c; i += 17)
        {
            if (!isPrime(i))
            {
            	numberOfNonPrimes++;
            }
        }

        return numberOfNonPrimes;
	}
	
	private static boolean isPrime(int n)
    {
        if (n % 2 == 0) {
            return false; 
        }
        for (int i = 3; i * i <= n; i += 2)
        {
            if (n % i == 0) {
                return false;  
            }
        }
        return true;

    }

	private static int getValue(int[] registers, String input)
	{
		if ((input.charAt(0) >= '0' && input.charAt(0) <= '9') || input.charAt(0) == '-')
		{
			return Integer.parseInt(input);
		}
		else
		{
			return registers[input.charAt(0) - 'a'];
		}

	}

	public static void main(String[] args) throws IOException
	{
		String[] input = Util.readInput("day23");
		System.out.println(solveP1(input));
		System.out.println(solveP2(input));
	}

}