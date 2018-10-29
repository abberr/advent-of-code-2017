package day19;

import java.io.IOException;

import util.Util;

public class Day19 {

	public static String solveP1(String[] input) {
		String result = "";
		Position currentPos = new Position(findStartIndex(input[1]), -1);
		int direction = 2; // 0=up, 1=right, 2=down, 3=left
		currentPos.move(direction);
		currentPos.move(direction);
		
		while (true) {
			char currentTile = input[currentPos.y].charAt(currentPos.x);
			if (currentTile >= 'A' && currentTile <= 'Z') {
				result += currentTile;
			}
			direction = nextPos(currentPos, direction, input);
			if (direction == -1) {
				System.out.println("Steps: " + currentPos.steps);
				return result;
			}
		}

	}

	// Returns -1 if no more moves
	private static int nextPos(Position currentPos, int direction, String[] input) {
		char[] tilesNearby = new char[4];
		tilesNearby[0] = input[currentPos.y - 1].charAt(currentPos.x);
		tilesNearby[1] = input[currentPos.y].charAt(currentPos.x + 1);
		tilesNearby[2] = input[currentPos.y + 1].charAt(currentPos.x);
		tilesNearby[3] = input[currentPos.y].charAt(currentPos.x - 1);
		char nextTile = tilesNearby[direction];

		if (nextTile == ' ') {
			if (direction == 0 || direction == 2) {
				if (tilesNearby[1] == '-') {
					direction = 1;
				} else if (tilesNearby[3] == '-') {
					direction = 3;
				} else {
					return -1;
				}
			} else if (direction == 1 || direction == 3) {
				if (tilesNearby[0] == '|') {
					direction = 0;
				} else if (tilesNearby[2] == '|') {
					direction = 2;
				} else {
					return -1;
				}
			} 
		}
		currentPos.move(direction);
		return direction;
	}

	private static int findStartIndex(String firstRow) {
		for (int i = 0; i < firstRow.length(); i++) {
			if (firstRow.charAt(i) == '|') {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		String[] input = Util.readInput("day19");
		System.out.println(solveP1(input));
	}

	private static class Position {
		public int x, y;
		private int steps;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void move(int direction) {
			if (direction == 0) {
				y--;
			} else if (direction == 1) {
				x++;
			} else if (direction == 2) {
				y++;
			} else if (direction == 3) {
				x--;
			}
			steps++;
		}

		@Override
		public String toString() {
			return "{" + x + ", " + y + "}";
		}
	}
}
