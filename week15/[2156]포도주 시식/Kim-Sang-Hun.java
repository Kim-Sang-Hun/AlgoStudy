import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
DFS로 하면 시초나는 문제.
당연함 n이 10000까지 있음
점화식 도출 : 현재 잔을 마시지 않았을 때 최대값(dp[i][0]) = 전 잔을 마시지 않았거나, 연속 한 잔 마셨거나, 연속 두 잔 마셨을 때 중 가장 큰 값
현재 잔을 연속 한 잔째 마셨을 때 최대값(dp[i][1]) = 전 잔을 마시지 않았을 때의 값(dp[i-1][0]) + 현재 잔의 양
현재 잔을 연속 두 잔째 마셨을 때 최대값(dp[i][2]) = 전 잔을 마셨을 때의 값(dp[i-1][1]) + 현재 잔의 양
 */
public class JUN2156_포도주시식 {
    static int n;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n][3];
        arr[0] = Integer.parseInt(br.readLine());
        dp[0][1] = arr[0];
        for (int i = 1; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            dp[i][0] = Math.max(Math.max(dp[i-1][1], dp[i-1][2]), dp[i-1][0]);
            dp[i][1] = dp[i-1][0] + arr[i];
            dp[i][2] = dp[i-1][1] + arr[i];
        }
        System.out.println(Math.max(Math.max(dp[n-1][1], dp[n-1][2]), dp[n-1][0]));
    }
}
