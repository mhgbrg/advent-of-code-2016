import java.util.*;

public class Solution2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] instructions = scanner.nextLine().split(", ");
		System.out.println(blocksAway(instructions));
	}

	private static class Coordinate {
		int x, y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			} else if (obj == null || obj.getClass() != this.getClass()) {
				return false;
			}

			Coordinate other = (Coordinate) obj;
			return other.x == this.x && other.y == this.y;
		}

		public int hashCode() {
			return Objects.hash(x, y);
		}

		public String toString() {
			return String.format("(%d, %d)", this.x, this.y);
		}
	}

	private enum Direction {
		NORTH,
		EAST,
		SOUTH,
		WEST;

		private Direction left, right;

		static {
			NORTH.left = WEST;
			NORTH.right = EAST;
			EAST.left = NORTH;
			EAST.right = SOUTH;
			SOUTH.left = EAST;
			SOUTH.right = WEST;
			WEST.left = SOUTH;
			WEST.right = NORTH;
		}

		public Direction getNextDirection(char direction) {
			if (direction == 'L') {
				return this.left;
			} else if (direction == 'R') {
				return this.right;
			}
			return null;
		}
	}

	public static int blocksAway(String[] instructions) {
		Direction facing = Direction.NORTH;
		int x = 0, y = 0;

		Set<Coordinate> visitedCoordinates = new HashSet<>();
		visitedCoordinates.add(new Coordinate(x, y));

		for (String instruction : instructions) {
			char direction = instruction.charAt(0);
			int steps = Integer.parseInt(instruction.substring(1));

			facing = facing.getNextDirection(direction);

			int dx = 0, dy = 0;

			switch (facing) {
				case NORTH:
					dy = -1;
					break;
				case EAST:
					dx = 1;
					break;
				case SOUTH:
					dy = 1;
					break;
				case WEST:
					dx = -1;
					break;
			}

			for (int stepsTaken = 0; stepsTaken < steps; stepsTaken++) {
				x += dx;
				y += dy;
				Coordinate coordinate = new Coordinate(x, y);
				if (visitedCoordinates.contains(coordinate)) {
					return Math.abs(x) + Math.abs(y);
				}
				visitedCoordinates.add(coordinate);
			}
		}

		return -1;
	}
}
