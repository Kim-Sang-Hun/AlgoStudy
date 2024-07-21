import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
메모리 28000 시간 392
투포인터로 풀어준다.
*/
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0, right = n - 1, min = Integer.MAX_VALUE;
        int a = 0, b = 0;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                a = arr[left];
                b = arr[right];
            }

            if (sum > 0) {
                right--;
            } else if (sum < 0) {
                left++;
            } else break;
        }
        System.out.println(a + " " + b);
    }
}
