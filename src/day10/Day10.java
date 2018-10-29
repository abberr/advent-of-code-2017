package day10;
import java.io.IOException;
import util.Util;

public class Day10
{

    private static final int HASH_MULTIPLIER = 64;
    private static final String EXTRA_LENGTH_SEQUENCE = "17,31,73,47,23";

    public static void solveP1(String input, int[] list, Config config)
    {
        for (int i = 0; i < input.split(",").length; i++)
        {
            int length = Integer.parseInt(input.split(",")[i]);
            reverseList(list, config.position, length);
            config.position += length + config.skiplength;
            config.skiplength++;
        }        
    }

    public static String solveP2(String input)
    {
        String newInput = "";
        for (int i = 0; i < input.length(); i++)
        {
            newInput += input.charAt(i) + 0 + ",";
        }
        newInput += EXTRA_LENGTH_SEQUENCE;
        Config config = new Config();
        int[] list = createList(256);
        for (int i = 0; i < HASH_MULTIPLIER; i++)
        {
            solveP1(newInput, list, config);
        }
        
        int [] sparseHash = sparseHash(list);
        return toHex(sparseHash);
    }
    
    private static int[] sparseHash(int[] list)
    {
        int [] sparseHash = new int[16];
        for (int i = 0; i < list.length/16; i++)
        {            
            sparseHash[i] = xor(list, i*16, 16);
        }
        return sparseHash;
    }

    private static int xor(int [] input, int start, int length) {
        int result = input[start];
        for (int i = 1; i < length; i++)
        {
            result = result^input[start + i];
        }
        return result;
    }
    
    private static String toHex(int [] input) {        
        String result = "";
        for (int i = 0; i < input.length; i++)
        {
            if (input[i] < 16) {
                result += "0";
            }
            result += Integer.toHexString(input[i]);
        }
        return result;
    }

    private static int[] createList(int size)
    {
        int[] list = new int[size];
        for (int i = 0; i < list.length; i++)
        {
            list[i] = i;
        }
        return list;
    }

    private static void reverseList(int[] list, int startIndex, int length)
    {
        for (int i = 0; i < length / 2; i++)
        {
            int firstIndex = (startIndex + i) % list.length;
            int endIndex = (((startIndex + length - 1) % list.length) - i) % list.length;
            if (endIndex < 0)
            {
                endIndex = list.length + endIndex;
            }
            int temp = list[firstIndex];
            list[firstIndex] = list[endIndex];
            list[endIndex] = temp;
        }
    }
    
    static class Config {
        int position, skiplength;
        public Config()
        {
            this.position = 0;
            this.skiplength = 0;
        }
    }

    public static void main(String[] args) throws IOException
    {
        String input = Util.readInput("input/day10")[0];
        int[] list = createList(256);
        solveP1(input, list, new Config());
        System.out.println(list[0] * list[1]);
        System.out.println(solveP2(input));
    }

}
