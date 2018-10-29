package day20;

import java.io.IOException;
import java.util.ArrayList;

import util.Util;

public class Day20
{

	private static final int NUMBER_OF_TICKS = 100;

	public static int solveP1(String[] input)
	{
		Particle[] particles = new Particle[input.length];
		int minAcc = Integer.MAX_VALUE;
		int minAccIndex = -1;
		for (int i = 0; i < input.length; i++)
		{
			particles[i] = new Particle(input[i]);
			if (particles[i].getTotalAcc() < minAcc)
			{
				minAcc = particles[i].getTotalAcc();
				minAccIndex = i;
			}
			if (particles[i].getTotalAcc() == minAcc && particles[i].getTotalVel() < particles[minAccIndex].getTotalVel())
			{
				minAcc = particles[i].getTotalAcc();
				minAccIndex = i;
			}
		}
		return minAccIndex;
	}

	public static int solveP2(String[] input)
	{
		ArrayList<Particle> particles = new ArrayList<>();
		for (int i = 0; i < input.length; i++)
		{
			particles.add(new Particle(input[i]));
		}

		for (int i = 0; i < NUMBER_OF_TICKS; i++)
		{
			checkCollisions(particles);
			for (int j = 0; j < particles.size(); j++)
			{
				particles.get(j).update();
			}
		}

		return particles.size();
	}

	private static void checkCollisions(ArrayList<Particle> particles)
	{
		for (int i = 0; i < particles.size(); i++)
		{
			Particle p = particles.get(i);
			boolean collided = false;
			for (int j = 0; j < particles.size(); j++)
			{
				if (i != j && p.isColliding(particles.get(j)))
				{
					particles.remove(j);
					j--;
					collided = true;
				}
			}
			if (collided)
			{
				particles.remove(p);
			}
		}
	}

	public static void main(String[] args) throws IOException
	{
		String[] input = Util.readInput("day20");
		System.out.println(solveP1(input));
		System.out.println(solveP2(input));
	}

}
