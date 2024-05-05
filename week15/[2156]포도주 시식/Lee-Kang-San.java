package BOJ;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <포도주 시식> S1
 * 링크
 * https://www.acmicpc.net/problem/2156
 * 요약
 * 
 * 풀이
 * dp
 */
public class boj_2156 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n; // 포도주 잔의 개수 n

    static int[] wine; // 0~n-1 번 포도주의 양 (0~1000)
    static int[] dp;

    public static void main(String[] args) throws IOException {
        // 입력
        n = Integer.parseInt(br.readLine());
        wine = new int[n + 1];
        dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            wine[i] = Integer.parseInt(br.readLine().trim());
        }
        // 풀이
        dp();
        // 출력
        sb.append(dp[n]);
        bw.write(sb.toString());
        bw.flush();
    }

    private static void dp() {
        dp[0] = 0;
        dp[1] = wine[1];
        if (n == 1)
            return;
        dp[2] = dp[1] + wine[2];
        for (int i = 3; i <= n; i++) {
            // i 안먹기 / i-2먹고 i 먹기 / i-3, i-1 먹고 i 먹기 중 최대가 i번째까지 고려 시 최선
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wine[i], dp[i - 3] + wine[i - 1] + wine[i]));
        }
    }
}
