import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2230_수고르기 {
    static int N, M;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);

        int minDif = Integer.MAX_VALUE;
        int front = 0;
        int end = 0;
        while (front < N && end < N) {
            int dif = nums[end] - nums[front];

            if (dif < M) {
                end++;
            } else {
                front++;
                minDif = Math.min(minDif, dif);
            }

        }

        System.out.println(minDif);

    }
}
