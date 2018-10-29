package day18;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Program extends Thread
{

	private Program otherProgram;
	private Map<String, Long> registers;
	private String[] input;
	LinkedList<Long> queue;

	int sendCounter = 0;
	
	public Program(String[] input, long id)
	{
		this.input = input;
		this.registers = new HashMap<>();
		registers.put("p", id);
		this.queue = new LinkedList<>();
	}

	public void setOtherProgram(Program otherProgram)
	{
		this.otherProgram = otherProgram;
	}

	private void send(long value)
	{
		queue.add(value);
	}

	@Override
	public void run()
	{
		for (int i = 0; i < input.length; i++)
		{
			String[] instructions = input[i].split(" ");
			switch (instructions[0])
			{
			case "set":
				Day18.set(registers, instructions[1], Day18.getValue(registers, instructions[2]));
				break;
			case "add":
				Day18.add(registers, instructions[1], Day18.getValue(registers, instructions[2]));
				break;
			case "mul":
				Day18.mul(registers, instructions[1], Day18.getValue(registers, instructions[2]));
				break;
			case "mod":
				Day18.mod(registers, instructions[1], Day18.getValue(registers, instructions[2]));
				break;
			case "jgz":
				if (Day18.getValue(registers, instructions[1]) > 0)
				{
					i += Day18.getValue(registers, instructions[2]) - 1;
				}
				break;
			case "snd":
				synchronized (otherProgram) {
					otherProgram.send(Day18.getValue(registers, instructions[1]));
					otherProgram.notify();
					sendCounter++;
				}
				break;
			case "rcv":
				synchronized(this) {
					if (queue.isEmpty())
					{
						try
						{
							this.wait();
						} catch (InterruptedException e)
						{
							e.printStackTrace();
						}
					}
				
					Day18.set(registers, instructions[1], queue.pollFirst());					
				}
				
				break;
			default:
				throw new RuntimeException("Invalid instruction: " + instructions[0]);
			}
		}
	}

}
