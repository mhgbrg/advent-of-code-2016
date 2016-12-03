import java.util.*;

public class Solution1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int count = 0;
		while (scanner.hasNextLine()) {
			int[] triangle = Arrays.stream(scanner.nextLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
			if (isValidTriangle(triangle)) {
				count++;
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
