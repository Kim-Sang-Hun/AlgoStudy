import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int[] arr = new int[N];
		int time = 0;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = s.nextInt();
		}
    //N이 1이면 하나의 값만 보면 되므로 바로 종료
		if (N == 1) {
			System.out.println(arr[0] > 1440 ? -1 : arr[0]);
			return;
		}
		Arrays.sort(arr);
    //종료조건 : 가장 많이 쌓인 곳만 눈이 남아있을 때
		while (arr[N - 2] > 0) {
      //가장 많이 쌓인 곳에서 그 다음 쌓인 곳의 눈만큼 제거 후 다시 정렬
			arr[N - 1] -= arr[N - 2];
			time += arr[N - 2];
			arr[N - 2] = 0;
			Arrays.sort(arr);
		}
		time += arr[N - 1];
		System.out.println(time > 1440 ? -1 : time);
	}
}
