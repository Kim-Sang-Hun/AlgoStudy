package BOJ;

import java.util.*;
import java.io.*;

/*
* 제목
<내려가기> G5
* 링크
https://www.acmicpc.net/problem/2096
* 요약
규칙에 따라 dp로 누적
최대, 최소 위해 각각의 배열 생성 (dpMax, dpMin)
* 풀이
dp
* 메모리
52200 KB
* 시간
436 ms
*/

public class boj_2096 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N; // N개의 줄
    static int[][] arr; // N개의 줄에 3개씩 저장되는 숫자 배열
    static int[][] arrMax;
    static int[][] arrMin;


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][3];
        arrMax = new int[N+1][3];
        arrMin = new int[N+1][3];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        int maxRes = dpMax();
        int minRes = dpMin();

        sb.append(maxRes).append(" ").append(minRes);
        bw.write(sb.toString());
        bw.flush();
    }


    private static int dpMax() {
        arrMax[1][0] = arr[1][0];
        arrMax[1][1] = arr[1][1];
        arrMax[1][2] = arr[1][2];
        for(int i=2; i<=N; i++) {
            arrMax[i][0] = Math.max(arrMax[i - 1][0], arrMax[i - 1][1]) + arr[i][0];
            arrMax[i][1] = Math.max(Math.max(arrMax[i - 1][0], arrMax[i - 1][1]), arrMax[i - 1][2]) + arr[i][1];
            arrMax[i][2] = Math.max(arrMax[i - 1][1], arrMax[i - 1][2]) + arr[i][2];
        }
        return Math.max(Math.max(arrMax[N][0], arrMax[N][1]), arrMax[N][2]);
    }

    private static int dpMin() {
        arrMin[1][0] = arr[1][0];
        arrMin[1][1] = arr[1][1];
        arrMin[1][2] = arr[1][2];
        for(int i=2; i<=N; i++) {
            arrMin[i][0] = Math.min(arrMin[i - 1][0], arrMin[i - 1][1]) + arr[i][0];
            arrMin[i][1] = Math.min(Math.min(arrMin[i - 1][0], arrMin[i - 1][1]), arrMin[i - 1][2]) + arr[i][1];
            arrMin[i][2] = Math.min(arrMin[i - 1][1], arrMin[i - 1][2]) + arr[i][2];
        }
        return Math.min(Math.min(arrMin[N][0], arrMin[N][1]), arrMin[N][2]);
    }
}
