package BOJ;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <정수 삼각형> S1
 * 링크
 * https://www.acmicpc.net/problem/1932
 * 요약
 * dp
 * 풀이
 * dp
 */
public class boj_1932 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] triangle;

    public static void main(String[] args) throws IOException {
        // 입력
        N = Integer.parseInt(br.readLine());
        triangle = new int[N + 1][];
        for (int i = N; i > 0; i--) {
            triangle[i] = new int[i];
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 풀이
        dp();
        // 출력
        bw.write(sb.toString());
        bw.flush();

    }

    private static void dp() {
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) {
                    triangle[i][j] += triangle[i - 1][j];
                } else if (j == triangle[i].length - 1) {
                    triangle[i][j] += triangle[i - 1][j - 1];
                } else {
                    triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int num : triangle[N]) {
            if (num > max)
                max = num;
        }
        sb.append(max);
    }
}
