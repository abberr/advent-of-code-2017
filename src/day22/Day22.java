package day22;

import java.io.IOException;
import java.util.ArrayList;

import util.Util;

public class Day22
{
	private static final int ITERATIONS_P1 = 10000;
	private static final int ITERATIONS_P2 = 10000000;

	public static int solveP1(String[] input)
	{
		int infectedCounter = 0;
		ArrayList<Node> nodes = getInfectedNodes(input);
		Square position = new Square(input.length / 2, input[0].length() / 2);
		int dir = 0; // 0=up, 1=right, 2=down, 3=left
		for (int i = 0; i < ITERATIONS_P1; i++)
		{
			Node node = getNodeAtPosition(position, nodes);
			if (node != null && node.status == NodeStatus.INFECTED)
			{
				nodes.remove(position);
				dir = (dir + 1) % 4;
			} else
			{
				dir = (dir + 3) % 4;
				nodes.add(new Node(position.x, position.y, NodeStatus.INFECTED));
				infectedCounter++;
			}
			position.move(dir);
		}

		return infectedCounter;
	}

	public static int solveP2(String[] input)
	{
		int infectedCounter = 0;
		ArrayList<Node> nodes = getInfectedNodes(input);
		Square position = new Square(input.length / 2, input[0].length() / 2);
		int dir = 0; // 0=up, 1=right, 2=down, 3=left
		for (int i = 0; i < ITERATIONS_P2; i++)
		{
			if (i % 1000000 == 0) {
				System.out.println(i);
			}
			Node node = getNodeAtPosition(position, nodes);
			if (node == null) {
				nodes.add(new Node(position.x, position.y, NodeStatus.WEAKENED));
				dir = (dir + 3) % 4;
			}
			else if (node.status == NodeStatus.WEAKENED)
			{
				node.status = NodeStatus.INFECTED;
				infectedCounter++;
			} else if (node.status == NodeStatus.INFECTED)
			{
				node.status = NodeStatus.FLAGGED;
				dir = (dir + 1) % 4;
				
			} else {
				nodes.remove(node);
				dir = (dir + 2) % 4;
			}
			position.move(dir);
		}

		return infectedCounter;
	}
	
	private static Node getNodeAtPosition(Square position, ArrayList<Node> nodes) {
		for (int i = 0; i < nodes.size(); i++)
		{
			if (nodes.get(i).equals(position))
			{
				return nodes.get(i);
			}
		}
		return null;
	}

	private static ArrayList<Node> getInfectedNodes(String[] input)
	{
		ArrayList<Node> infectedSquares = new ArrayList<>();
		for (int i = 0; i < input.length; i++)
		{
			for (int j = 0; j < input[i].length(); j++)
			{
				if (input[i].charAt(j) == '#')
				{
					infectedSquares.add(new Node(j, i, NodeStatus.INFECTED));
				}
			}
		}
		return infectedSquares;
	}

	public static void main(String[] args) throws IOException
	{
		String[] input = Util.readInput("day22");
		System.out.println(solveP1(input));
		System.out.println(solveP2(input));
	}
}
