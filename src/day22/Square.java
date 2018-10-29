package day22;

public class Square
{
	public int x, y;

	public Square(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void move(int dir)
	{
		if (dir == 0)
		{
			y--;
		} else if (dir == 1)
		{
			x++;
		} else if (dir == 2)
		{
			y++;
		} else if (dir == 3)
		{
			x--;
		}
	}

	@Override
	public boolean equals(Object obj)
	{
		Square p = (Square) obj;
		return (x == p.x && y == p.y);
	}

	@Override
	public String toString()
	{
		return x + "," + y;
	}
}

class Node extends Square
{	
	public NodeStatus status;
	
	public Node(int x, int y, NodeStatus status)
	{
		super(x, y);
		this.status = status;
	}
}

