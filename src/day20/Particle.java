package day20;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Particle
{

	private static final String PATTERN = "<(\\S*)>.*<(\\S*)>.*<(\\S*)>";

	int[] pos, acc, vel;

	public Particle(String input)
	{
		pos = new int[3];
		vel = new int[3];
		acc = new int[3];

		Pattern pattern = Pattern.compile(PATTERN);
		Matcher matcher = pattern.matcher(input);
		matcher.find();

		String[] posString = matcher.group(1).split(",");
		String[] velString = matcher.group(2).split(",");
		String[] accString = matcher.group(3).split(",");
		for (int i = 0; i < 3; i++)
		{
			pos[i] = Integer.parseInt(posString[i]);
			vel[i] = Integer.parseInt(velString[i]);
			acc[i] = Integer.parseInt(accString[i]);
		}
	}

	public int getTotalAcc()
	{
		return Math.abs(acc[0]) + Math.abs(acc[1]) + Math.abs(acc[2]);
	}

	public int getTotalVel()
	{
		return Math.abs(vel[0]) + Math.abs(vel[1]) + Math.abs(vel[2]);
	}

	public void update()
	{
		for (int i = 0; i < acc.length; i++)
		{
			vel[i] += acc[i];
		}

		for (int i = 0; i < vel.length; i++)
		{
			pos[i] += vel[i];
		}
	}

	public boolean isColliding(Particle particle)
	{
		for (int i = 0; i < acc.length; i++)
		{
			if (this.pos[i] != particle.pos[i])
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString()
	{
		return pos[0] + "," + pos[1] + "," + pos[2];
	}

}
