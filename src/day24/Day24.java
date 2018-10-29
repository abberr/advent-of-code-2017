package day24;

import java.io.IOException;
import java.util.ArrayList;

import util.Util;

public class Day24
{

	private static int strongestBridge;
	private static int longestBridge;
	private static int longestBridgeStrength;

	public static int solveP1(String[] input)
	{
		strongestBridge = -1;
		ArrayList<String> bridge = new ArrayList<String>();
		dfs(input, bridge, "0");
		return strongestBridge;
	}

	private static void dfs(String[] input, ArrayList<String> bridge, String next)
	{
		int strengthOfBridge = strengthOfBridge(bridge);
		strongestBridge = Math.max(strongestBridge, strengthOfBridge);
		if (bridge.size() == longestBridge)
		{
			if (strengthOfBridge > longestBridgeStrength)
			{
				longestBridgeStrength = strengthOfBridge;
				longestBridge = bridge.size();
			}
		} else if (bridge.size() > longestBridge) 
		{
			longestBridgeStrength = strengthOfBridge;
			longestBridge = bridge.size();
		}

		for (String component : input)
		{
			if (!bridge.contains(component))
			{
				String p1 = component.split("/")[0];
				String p2 = component.split("/")[1];
				if (p1.equals(next))
				{
					bridge.add(component);
					dfs(input, bridge, p2);
					bridge.remove(component);
				} else if (p2.equals(next))
				{
					bridge.add(component);
					dfs(input, bridge, p1);
					bridge.remove(component);
				}
			}
		}
	}

	public static int solveP2(String[] input)
	{
		strongestBridge = -1;
		longestBridge = -1;
		longestBridgeStrength = -1;
		ArrayList<String> bridge = new ArrayList<String>();
		dfs(input, bridge, "0");
		return longestBridgeStrength;
	}

	private static int strengthOfBridge(ArrayList<String> bridge)
	{
		int strength = 0;
		for (String component : bridge)
		{
			strength += Integer.parseInt(component.split("/")[0]);
			strength += Integer.parseInt(component.split("/")[1]);
		}
		return strength;
	}

	public static void main(String[] args) throws IOException
	{
		String[] input = Util.readInput("day24");
		System.out.println(solveP1(input));
		System.out.println(solveP2(input));
	}

}
