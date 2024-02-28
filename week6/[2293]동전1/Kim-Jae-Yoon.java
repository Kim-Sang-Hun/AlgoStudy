import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, k;
    static int[] dp, a;

    static void solution() {
        Arrays.sort(a);
        dp[0] = 1;
        for (int i = 0;i < n; ++i) {
          //어차피 10000 넘는 놈은 안 쓰니까. 이후 원소들은 생략해도 될 것
            if(a[i] > 10000) break;
          //직전 내용들로부터 x만큼 더해가며 점진적으로 값을 올린다.
            for(int j = a[i]; j <= k; ++j){
                dp[j] += dp[j - a[i]];
            }
        }
        System.out.println(dp[k]);
    }

    static void input() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        a = new int[n];
        dp = new int[10001];
        for (int i = 0; i < n; ++i) {
            a[i] = Integer.parseInt(br.readLine());
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
