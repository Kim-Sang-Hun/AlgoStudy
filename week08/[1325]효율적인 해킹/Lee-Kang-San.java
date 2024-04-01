package BOJ;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <효율적인 해킹> S1
 * 링크
 * https://www.acmicpc.net/problem/1325
 * 요약
 * A B : A는 B를 신뢰한다 == B에서 -> A로 가는 경로 존재 
 * 풀이
 * bfs 탐색
 * 시간 더 줄여보려고 bfs를 main으로 빼면 오히려 시간 초과 나는데 왜 그러지.......
 */
public class boj_1325 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M; // N: 컴퓨터 개수, M: 신뢰관계 수
    static ArrayList<Integer>[] arr; // 인접리스트

    static Queue<Integer> q = new ArrayDeque<>(); // bfs용
    static int[] visited; // bfs용

    static int maxVisit = -1;
    static int[] res; // 결과 출력용
    static int pos = 0, cur = 0, cnt = 0; // 결과 시작 위치, 삽입 위치, 결과 개수

    public static void main(String[] args) throws IOException {
        // 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new ArrayList[N + 1]; // 1~N번 컴퓨터
        res = new int[N + 1];

        for (int i = 1; i <= N; ++i) {
            arr[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            arr[B].add(A);
        }

        // 풀이
        for (int i = 1; i <= N; ++i) { // 각 노드에서 출발하여 가장 많은 노드 방문하는 경우 찾기
            bfs(i);

        }
        // 출력
        for (int i = 1; i <= N; ++i) {
            if (res[i] == maxVisit)
                sb.append(i + " ");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private static void bfs(int start) {
        visited = new int[N + 1];
        q.offer(start);
        int vis = 1;
        int cur;
        visited[start] = 1;
        while (!q.isEmpty()) {
            cur = q.poll();
            for (int next : arr[cur]) {
                if (visited[next] == 1)
                    continue;
                ++vis;
                visited[next] = 1;
                q.offer(next);
            }
        }
        res[start] = vis;
        if (maxVisit < vis)
            maxVisit = vis;
    }
}
