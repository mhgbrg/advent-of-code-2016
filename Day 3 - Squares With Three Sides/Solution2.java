import java.util.*;

public class Solution2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int count = 0;

		int[][] triangles = new int[3][3];
		int position = 0;

		while (scanner.hasNextLine()) {
			String[] line = scanner.nextLine().split(" ");

			triangles[0][position] = Integer.parseInt(line[0]);
			triangles[1][position] = Integer.parseInt(line[1]);
			triangles[2][position] = Integer.parseInt(line[2]);

			position++;

			if (position == 3) {
				count += Arrays.stream(triangles).filter(Solution2::isValidTriangle).count();
				position = 0;
			}
		}
		System.out.println(count);
	}

	public static boolean isValidTriangle(int[] triangle) {
		// We ssume triangle only has three elements.
		Arrays.sort(triangle);
		return triangle[0] + triangle[1] > triangle[2];
	}
}
