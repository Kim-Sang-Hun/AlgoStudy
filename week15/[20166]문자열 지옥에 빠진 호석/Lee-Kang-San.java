package BOJ;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <문자열 지옥에 빠진 호석> G4
 * 링크
 * https://www.acmicpc.net/problem/20166
 * 요약
 * 
 * 풀이
 * dfs
 * "신이 좋아하는 문자열은 중복될 수도 있다."
 * map 사용해서 동일값으로 시도한 경우 연산 없이 값 도출
 */
public class boj_20166 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, K; // 격자크기 N*M, 신이 좋아하는 문자열 개수 K
    static char[][] arr; // 격자

    static int[] di = { -1, 1, 0, 0, -1, -1, 1, 1 }; // 상 하 좌 우 좌상 우상 좌하 우하
    static int[] dj = { 0, 0, -1, 1, -1, 1, -1, 1 };

    static String godsPick; // 신이 좋아하는 문자열
    static int length; // godsPick의 길이
    static int count; // godsPick 만들 수 있는 경우의 수

    static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // 입력
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new char[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String str = br.readLine().trim();
            for (int j = 1; j <= M; j++) {
                arr[i][j] = str.charAt(j - 1);
            }
        }

        // 풀이
        for (int ct = 0; ct < K; ct++) {
            // init
            godsPick = br.readLine().trim();
            count = 0;
            length = godsPick.length();

            // 이미 경우의 수 구한 문자열이면 전에 구한 값 사용
            if (map.containsKey(godsPick)) {
                sb.append(map.get(godsPick)).append("\n");
                continue;
            }

            // 처음 경우의 수 구하는 문자열인 경우
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (arr[i][j] != godsPick.charAt(0)) // 시작부터 틀렸으면 continue
                        continue;
                    dfs(i, j, 1, length);
                }
            }

            map.put(godsPick, count);
            sb.append(count).append("\n");
        }

        // 출력
        bw.write(sb.toString());
        bw.flush();
    }

    private static void dfs(int i, int j, int currentLength, int targetLength) {
        if (currentLength == targetLength) {
            count++;
            return;
        }

        // 8방탐색, 다음 문자가 유망할때만 dfs 진행
        for (int d = 0; d < 8; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if (ni == N + 1)
                ni = 1;
            else if (ni == 0)
                ni = N;
            if (nj == M + 1)
                nj = 1;
            else if (nj == 0)
                nj = M;

            if (arr[ni][nj] != godsPick.charAt(currentLength))
                continue;
            dfs(ni, nj, currentLength + 1, targetLength);
        }
    }
}
