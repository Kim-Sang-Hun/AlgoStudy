package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ10597 {

    static char[] arr;
    static boolean[] visited;
    static int N, R;
    static int[] ans;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = br.readLine().toCharArray();
        N = arr.length;

        if (N < 10) {
            for (int i = 0; i < N; i++) {
                System.out.print(arr[i] + " ");
            }
            return;
        } else {
            R = 9 + (N - 9) / 2;
        }

        visited = new boolean[R + 1];
        ans = new int[N];

        dfs(0, 0);

    }

    private static void dfs(int idx, int depth) {

        if (idx == N) {
            for (int i = 0; i < N; i++) {
            	if (ans[i]== 0) break;
                System.out.print(ans[i] + " ");
            }
            System.exit(0);
        }

        int num = arr[idx] - '0';

        if (!visited[num]) {
            visited[num] = true;
            ans[depth] = num;
            dfs(idx + 1, depth + 1);
            visited[num] = false;
        }

        if (idx + 1 < N) {
            int num2 = num * 10 + arr[idx + 1] - '0';

            if (num2 >= 1 && num2 <= R && !visited[num2]) {
                visited[num2] = true;
                ans[depth] = num2;
                dfs(idx + 2, depth + 1);
                visited[num2] = false;
            }
        }
    }
}