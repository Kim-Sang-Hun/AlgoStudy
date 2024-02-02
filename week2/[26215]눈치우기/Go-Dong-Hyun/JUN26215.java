
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class JUN26215 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		List<Integer> arr = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			arr.add(sc.nextInt());
		}
		
		int cnt = 0;
		
		while (true) {
			
			if (arr.size() == 1) {
				cnt += arr.get(0);
				break;
			} else if (arr.size() == 0) {
				break;
			}
			
			Collections.sort(arr);
			
			arr.set(0, arr.get(0)-1);
			arr.set(arr.size()-1, arr.get(arr.size()-1)-1);
			
			if (arr.get(0) == 0) {
				arr.remove(0);
			}
			if (arr.get(arr.size()-1) == 0) {
				arr.remove(arr.size()-1);
			}
			
			cnt += 1;
		}
		if (cnt > 1440) {
			System.out.println(-1);
		} else {
			System.out.println(cnt);
		}
	}
}
