import java.util.*;

public class Solution1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] instructions = scanner.nextLine().split(", ");
		System.out.println(blocksAway(instructions));
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

		for (String instruction : instructions) {
			char direction = instruction.charAt(0);
			int steps = Integer.parseInt(instruction.substring(1));

			facing = facing.getNextDirection(direction);

			// Walk in new direction.
			switch (facing) {
				case NORTH:
					y -= steps;
					break;
				case EAST:
					x += steps;
					break;
				case SOUTH:
					y += steps;
					break;
				case WEST:
					x -= steps;
					break;
			}
		}

		return Math.abs(x) + Math.abs(y);
	}
}
