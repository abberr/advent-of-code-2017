package day11;
import java.io.IOException;

import util.Util;

public class Day11
{

    public static void main(String[] args) throws IOException
    {
        System.out.println(solveP1(Util.readInput("input/day11")[0].split(",")));
    }

    private static int solveP1(String[] input)
    {
        Position pos = new Position();
        int maxDistanceFromCenter = -1;
        for (String direction : input)
        {
            pos.move(direction);
            int distanceFromCenter = Math.max(pos.x, pos.y);
            if (distanceFromCenter > maxDistanceFromCenter) {
                maxDistanceFromCenter = distanceFromCenter;
            }
        }

        System.out.println("Max distance from center: " + maxDistanceFromCenter);
        return Math.max(pos.x, pos.y);
    }

    
    
    //Even-q vertical layout https://www.redblobgames.com/grids/hexagons/
    static class Position {
        int x, y;
        
        public Position()
        {
            x = 0;
            y = 0;
        }
        
        public void move(String direction) {
            switch (direction)
            {
                case "n":
                    y--;
                    break;
                case "ne":
                    x++;                    
                    if (x % 2 != 0) {
                        y--;
                    }
                    break;
                case "se":
                    x++;
                    if (x % 2 == 0) {
                        y++;
                    }
                    break;
                case "s":
                    y++;
                    break;
                case "sw":
                    x--;
                    if (x % 2 == 0) {
                        y++;
                    }
                    break;
                case "nw":
                    if (x % 2 != 0) {
                        y--;
                    }
                    x--;
                    break;
                default:
                    throw new RuntimeException("Unsupported format: " + direction);
            }
        }
    }

}
