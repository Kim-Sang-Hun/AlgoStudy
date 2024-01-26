import java.util.Scanner;
import java.util.TreeSet;

public class Main {
	
	static String[] arr;
	static int k;
	static TreeSet<String> set;
	
	public static void bfs(int idx, String str) {
		// idx가 k와 같다면 만들어진 str이 끝까지 완성된 문자열이라는 의미이므로 set에 저장해줍니다.
		if (idx == k) {
			set.add(str);
			return;
		}
		int lastNum = Character.getNumericValue(str.charAt(idx));
		// 부등호가 >인지 <인지 판단한 후 str의 마지막 문자를 비교하여 조건을 만족하는 값을 넣어줍니다.
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
		// 정렬된 set을 사용하기 위해 TreeSet을 사용했습니다.
		set = new TreeSet<>();
		for(int i = 0; i < 10; i++) {
			bfs(0, String.valueOf(i));
		}
		System.out.println(set.last());
		System.out.println(set.first());
	}
}
