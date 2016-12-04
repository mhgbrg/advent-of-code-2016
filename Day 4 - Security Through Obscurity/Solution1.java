import java.util.*;
import java.util.stream.*;
import java.util.regex.*;

public class Solution1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pattern p = Pattern.compile("(.*)-(\\d+)\\[(.+)\\]");
        List<Room> rooms = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Matcher m = p.matcher(line);
            m.matches();
            String name = m.group(1);
            int sector = Integer.parseInt(m.group(2));
            String hash = m.group(3);
            rooms.add(new Room(name, sector, hash));
        }

        int sum = rooms.stream()
            .filter(room -> room.isRealRoom())
            .mapToInt(room -> room.sector)
            .sum();

        System.out.println(sum);
    }

    private static class Room {
        String name;
        int sector;
        String hash;

        public Room(String name, int sector, String hash) {
            this.name = name;
            this.sector = sector;
            this.hash = hash;
        }

        public String toString() {
            return String.format("Room[name=%s, sector=%d, hash=%s]", name, sector, hash);
        }

        public boolean isRealRoom() {
            return this.hash.equals(this.hash());
        }

        private String hash() {
            char[] chars = this.name.replace("-", "").toCharArray();
            Map<Character, Integer> counts = new HashMap<>();
            for (char c : chars) {
                int count = counts.getOrDefault(c, 0);
                counts.put(c, count + 1);
            }

            List<Map.Entry<Character, Integer>> list = new ArrayList<>(counts.entrySet());
            Collections.sort(list, (entry1, entry2) -> {
                int comparison = Integer.compare(entry2.getValue(), entry1.getValue());
                if (comparison != 0) {
                    return comparison;
                }
                return Character.compare(entry1.getKey(), entry2.getKey());
            });

            return list.subList(0, 5).stream()
                .map(entry -> entry.getKey() + "")
                .collect(Collectors.joining(""));
        }
    }
}
