
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class SW1225 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = 10;
		
		for (int t = 1; t <= T; t++) {
			// 1,2,3,4,5,1,2,3 이렇게 배열에서 값을 빼는데
			//5사이클 돌때마다 모든값들이 15씩 빠짐
			//그래서 15씩 나눈값에 최소값을 구해서
			//전체 배열값에 15*(div-1)씩 빼서 다시 돌려주면 값금방찾음
			int N = sc.nextInt();
			
			int[] arr = new int[8];
			
			for (int i = 0; i < 8; i++) {
				arr[i] = sc.nextInt();
			}
			
			int div = 999999;
//			Integer.min(a, b)
			
			for (int sub_div : arr) {
				div = Integer.min(div, sub_div/15)-1;
			}
			
			Deque<Integer> deque = new LinkedList<>();
			
			for (int i = 0; i < arr.length; i++) {
				int dq = arr[i] - (15*div);
				deque.add(dq);
			}
			int idx = 0;
			
			while (true) {
				idx += 1;
				int left = deque.removeFirst();
				int right = left - idx;
				if (right <= 0) {
					deque.add(0);
					break;
				} else {
					deque.add(right);
				}
				idx = idx % 5;
			}
			System.out.print("#" + t+ " ");
			for (Integer dq : deque) {
				System.out.print(dq + " ");
			}
			System.out.println("");
		}
	}	
}
