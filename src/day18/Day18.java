package day18;
import java.io.IOException;
import java.lang.Thread.State;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.Map;

import util.Util;

public class Day18
{

    public static long solveP1(String[] input)
    {
        long lastSound = -1;
        Map<String, Long> registers = new HashMap<>();
        for (int i = 0; i < input.length; i++)
        {
            String[] instructions = input[i].split(" ");            
            switch (instructions[0])
            {
                case "set":
                    set(registers, instructions[1], getValue(registers, instructions[2]));
                    break;
                case "add":
                    add(registers, instructions[1], getValue(registers, instructions[2]));
                    break;
                case "mul":
                    mul(registers, instructions[1], getValue(registers, instructions[2]));
                    break;
                case "mod":
                    mod(registers, instructions[1], getValue(registers, instructions[2]));
                    break;
                case "snd":
                    lastSound = getValue(registers, instructions[1]);
                    break;
                case "jgz":
                    if (getValue(registers, instructions[1]) > 0) {
                        i += getValue(registers, instructions[2]) - 1;
                    }
                    break;
                case "rcv":
                    if (getValue(registers, instructions[1]) != 0) {
                        return lastSound;
                    }
                    break;
                default:
                     throw new RuntimeException("Invalid instruction: " + instructions[0]);
            }
        }
        return 0;
    }
    
    public static int solveP2(String[] input) {
    	Program p1 = new Program(input, 0);
    	Program p2 = new Program(input, 1);
    	p1.setOtherProgram(p2);
    	p2.setOtherProgram(p1);
    	p1.start();
    	p2.start();
    	while (true) {
    		if (p1.getState() == State.WAITING && p2.getState() == State.WAITING) {
    			p1.stop();
    			p2.stop();
    			return p2.sendCounter;
    		}   	
    	}
    }
    
    static Long getValue(Map<String, Long> registers, String input)
    {
        if ((input.charAt(0) >= '0' && input.charAt(0) <= '9') || input.charAt(0) == '-')
        {
        	return Long.parseLong(input);
        }
        else {
            if (registers.containsKey(input)) {
                return registers.get(input);
            } else {
                return 0l;
            }
        }
    }

    static void mod(Map<String, Long> registers, String x, long y)
    {
        registers.put(x, getValue(registers, x) % y);        
    }
    
    static void mul(Map<String, Long> registers, String x, long y)
    {
        registers.put(x, getValue(registers, x) * y);  
    }

    static void add(Map<String, Long> registers, String x, long y)
    {
        registers.put(x, getValue(registers, x) + y);  
    }

    static void set(Map<String, Long> registers, String x, long y)
    {
        registers.put(x, y);  
    }

    public static void main(String[] args) throws IOException
    {
        String[] input = Util.readInput("day18");
        System.out.println(solveP1(input));
        System.out.println(solveP2(input));
    }

}
