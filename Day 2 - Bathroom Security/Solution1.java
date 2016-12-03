import java.util.*;

public class Solution1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<String> instructions = new ArrayList<>();
		while (scanner.hasNextLine()) {
			instructions.add(scanner.nextLine());
		}
		System.out.println(getBathroomCode(instructions));
	}

	public static String getBathroomCode(List<String> instructions) {
		KeyPad keyPad = new KeyPad();
		String code = "";
		for (String instruction : instructions) {
			code += getNumber(keyPad, instruction);
		}
		return code;
	}

	private static class KeyPad {
		private static final int[][] KEYS = {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		};

		// x = rows, y = columns.
		private int x = 1, y = 1;

		public int getCurrentNumber() {
			return KEYS[this.x][this.y];
		}

		public void move(char direction) {
			if (direction == 'U') {
				if (this.x > 0) {
					this.x--;
				}
			} else if (direction == 'D') {
				if (this.x < 2) {
					this.x++;
				}
			} else if (direction == 'L') {
				if (this.y > 0) {
					this.y--;
				}
			} else if (direction == 'R') {
				if (this.y < 2) {
					this.y++;
				}
			}
		}
	}

	private static int getNumber(KeyPad keyPad, String instruction) {
		for (char direction : instruction.toCharArray()) {
			keyPad.move(direction);
		}
		return keyPad.getCurrentNumber();
	}
}
