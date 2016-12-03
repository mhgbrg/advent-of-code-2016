import java.util.*;
import java.util.stream.*;

public class Solution2 {
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
		return instructions.stream()
			.map((instruction) -> getNumber(keyPad, instruction) + "")
			.collect(Collectors.joining(""));
	}

	private static class KeyPad {
		private static final char[][] KEYS = {
			{' ', ' ', '1', ' ', ' '},
			{' ', '2', '3', '4', ' '},
			{'5', '6', '7', '8', '9'},
			{' ', 'A', 'B', 'C', ' '},
			{' ', ' ', 'D', ' ', ' '},
		};

		// x = rows, y = columns.
		private int x = 2, y = 0;

		public char getCurrentNumber() {
			return KEYS[this.x][this.y];
		}

		private static boolean validPosition(int x, int y) {
			return x >= 0 && x <= 4 && y >= 0 && y <= 4 && KEYS[x][y] != ' ';
		}

		public void move(char direction) {
			if (direction == 'U') {
				if (validPosition(x - 1, y)) {
					this.x--;
				}
			} else if (direction == 'D') {
				if (validPosition(x + 1, y)) {
					this.x++;
				}
			} else if (direction == 'L') {
				if (validPosition(x, y - 1)) {
					this.y--;
				}
			} else if (direction == 'R') {
				if (validPosition(x, y + 1)) {
					this.y++;
				}
			}
		}
	}

	private static char getNumber(KeyPad keyPad, String instruction) {
		for (char direction : instruction.toCharArray()) {
			keyPad.move(direction);
		}
		return keyPad.getCurrentNumber();
	}
}
