package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Util {

	public static String[] readInput(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("input/" + filename));
		ArrayList<String> list = new ArrayList<>();
		try {		    
		    String line = br.readLine();

		    while (line != null) {
		    	list.add(line);
		        line = br.readLine();
		    }
		} finally {
		    br.close();
		}
		
		return list.toArray(new String[list.size()]);
	}
	
	public static int[] stringArrayToIntArray(String [] array) {
		int [] result = new int[array.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Integer.parseInt(array[i]);
		}
		return result;
	}
	
	public static void printArray(int [] array) {
		String result = "{";
		for (int i = 0; i < array.length; i++)
		{
			result += array[i] + ", ";
		}
		result = result.substring(0,result.length() - 2);
		result += "}";
		System.out.println(result);
	}
}
