package day14;
import day10.Day10;

public class Day14
{
    private static final int HEIGHT = 128;

    public static boolean[][] createGrid(String input)
    {
        boolean[][] grid = new boolean[HEIGHT][128];
        for (int i = 0; i < grid.length; i++)
        {
            String knotHash = Day10.solveP2(input + "-" + i);
            grid[i] = hexToBinary(knotHash);
        }

        return grid;
    }

    public static int countGroupsInGrid(boolean[][] grid)
    {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int groupCounter = 0;
        for (int i = 0; i < visited.length; i++)
        {
            for (int j = 0; j < visited[i].length; j++)
            {
                if (grid[i][j] == true && !visited[i][j]) {
                    visitNearbySquares(grid, visited, j, i);
                    groupCounter++;
                }
            }
        }
        return groupCounter;
    }

    private static void visitNearbySquares(boolean[][] grid, boolean[][] visited, int x, int y)
    {
        if (grid[y][x] == false) {
            return;
        }
        visited[y][x] = true;
        
        if (y > 0 && !visited[y-1][x]) {
            visitNearbySquares(grid, visited, x, y-1);
        }
        if (y < grid.length - 1 && !visited[y+1][x]) {
            visitNearbySquares(grid, visited, x, y+1);
        }
        if (x > 0 && !visited[y][x-1]) {
            visitNearbySquares(grid, visited, x-1, y);
        }
        if (x < grid[0].length-1 && !visited[y][x+1]) {
            visitNearbySquares(grid, visited, x+1, y);
        }        
    }

    private static int countTrues(boolean[][] grid)
    {
        int counter = 0;
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                if (grid[i][j] == true)
                {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static boolean[] hexToBinary(String input)
    {
        boolean[] binaryArray = new boolean[input.length() * 4];
        String binaryString = "";
        for (int i = 0; i < input.length(); i++)
        {
            int num = (Integer.parseInt(input.charAt(i) + "", 16));
            String hexNumberInBinary = Integer.toBinaryString(num);            
            if (hexNumberInBinary.length() < 4) {
                for (int j = 0; j < 4-hexNumberInBinary.length(); j++)
                {
                    hexNumberInBinary = "0" + hexNumberInBinary;
                    j--;
                }
            }
            binaryString += hexNumberInBinary;
        }
        for (int i = 0; i < binaryString.length(); i++)
        {
            if (binaryString.charAt(i) == '1')
            {
                binaryArray[i] = true;
            }
        }
        return binaryArray;
    }
    
    private static void printGrid(boolean [][] grid) {
        for (int i = 0; i < grid.length; i++)
        {
            System.out.println();
            for (int j = 0; j < grid.length; j++)
            {
                if (grid [i][j]) {
                    System.out.print('#');                    
                }
                else {
                    System.out.print('.');
                }
            }
            
        }
    }

    public static void main(String[] args)
    {
        String input = "hfdlxzhv";
        boolean [][] grid = createGrid(input);
        System.out.println(countTrues(grid));
        System.out.println(countGroupsInGrid(grid));
    }
    
    

}
