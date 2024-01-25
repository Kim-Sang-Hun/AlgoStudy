import java.util.Scanner;
import java.util.TreeSet;

public class Main {
	
	static String[] arr;
	static int k;
	static TreeSet<String> set;
	
	public static void bfs(int idx, String str) {
		if (idx == k) {
			set.add(str);
			return;
		}
		int lastNum = Character.getNumericValue(str.charAt(idx));
		if (arr[idx].equals(">")) {
			for(int i = 0; i < 10; i++) {
				if (lastNum > i && !str.contains(String.valueOf(i))) {
					bfs(idx + 1, str + String.valueOf(i));
				}
			}
		} else {
			for(int i = 0; i < 10; i++) {
				if (lastNum < i && !str.contains(String.valueOf(i))) {
					bfs(idx + 1, str + String.valueOf(i));
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		k = s.nextInt();
		s.nextLine();
		arr = s.nextLine().split(" ");
		set = new TreeSet<>();
		for(int i = 0; i < 10; i++) {
			bfs(0, String.valueOf(i));
		}
		System.out.println(set.last());
		System.out.println(set.first());
	}
}
