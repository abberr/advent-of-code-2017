package day7;
import java.io.IOException;
import java.util.ArrayList;

import util.Util;
 
public class Day7
{
 
    public static String solveP1(String[] array)
    {
        String leaf = findFirstLeaf(array);
        return findTopNode(array, leaf.substring(0, leaf.indexOf(" ")));
    }
 
    private static String findFirstLeaf(String[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            if (!array[i].contains("->"))
            {
                return array[i];
            }
        }
        return null;
    }
 
    private static String findTopNode(String[] array, String child)
    {
        for (String line : array)
        {
            if (line.contains("->"))
            {
                String parent = line.substring(0, line.indexOf(" "));
                String[] childs = line.substring(line.indexOf("->") + 3).split(
                    ", ");
                for (String childNode : childs)
                {
                    if (childNode.equals(child))
                    {
                        return findTopNode(array, parent);
                    }
                }
            }
        }
        return child;
    }
 
    public static int solveP2(String[] array)
    {
        Program topNode = new Program(solveP1(array));
        populateTree(array, topNode);
        totalWeight(topNode);        
        
 
        return 0;
    }
 
    private static void populateTree(String[] array, Program parent)
    {
        String rowWithProgram = findRow(array, parent);
        int weightOfParent = Integer.parseInt(rowWithProgram.substring(rowWithProgram.indexOf("(") + 1, rowWithProgram
            .indexOf(")")));
        
        parent.setWeight(weightOfParent);
        
        String[] childs = getChilds(rowWithProgram);       
        if (childs != null)
        {  
            for (String childName : childs)
            {
            	Program child = new Program(childName);
            	parent.addChild(child);
                populateTree(array, child);
            }           
        }
    }
    
    private static int totalWeight(Program parent) {
    	int totalWeight = parent.getWeight();
    	ArrayList<Program> children = parent.getChildren();
    	
    	int [] weightOfChildren = new int [children.size()];
    	for (int i = 0; i < children.size(); i++) {
    		int weight = totalWeight(children.get(i));
    		weightOfChildren[i] += weight;
    		totalWeight += weight;
		}
    	
    	if (children.size() != 0 && !isBalanced(weightOfChildren)) {
    		int stableWeight = stableWeight(weightOfChildren);
    		for (int i = 0; i < weightOfChildren.length; i++) {
				if (weightOfChildren[i] != stableWeight) {
					int difference = stableWeight - weightOfChildren[i];
					System.out.println(children.get(i).getWeight() + difference);
				}
			}
    	}
    	
    	return totalWeight;
    }
   
    private static boolean isBalanced(int [] array) {
    	int element = array[0];
    	for (int i = 1; i < array.length; i++) {
			if (array[i] != element) {
				return false;
			}
			element = array[i];
		}
    	return true;
    }
    
    private static int stableWeight(int [] array) {
        int weight1 = array[0];
        int weight1Count = 1;
        int weight2 = -1;
        int weight2Count = 0;
        for (int i = 1; i < array.length; i++)
        {
            if (array[i] == weight1)
            {
                weight1Count++;
            }
            else {
                if (weight2Count == 0) {
                    weight2 = array[i];
                }
                weight2Count++;
            }
        }
       
        if (weight1Count < weight2Count) {
            return weight2;
        }
        else {
            return weight1;
        }
    }
 
    private static String findRow(String[] array, Program parent)
    {
        for (String line : array)
        {
            if (line.contains(parent.getName() + " ("))
            {
                return line;
            }
        }
        return null;
    }
 
    private static String[] getChilds(String line)
    {
        if (line.contains("->"))
        {
            return line.substring(line.indexOf("->") + 3).split(", ");
        }
 
        return null;
    }
    
    public static void main(String[] args) throws IOException
    {
        System.out.println(solveP1(Util.readInput("day7")));
        System.out.println(solveP2(Util.readInput("day7")));
 
    }
 
}
 