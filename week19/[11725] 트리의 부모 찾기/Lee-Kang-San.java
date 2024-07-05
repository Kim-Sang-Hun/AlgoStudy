package BOJ;

import java.util.*;
import java.io.*;

/*
* 제목
<트리의 부모 찾기> S2
* 링크
https://www.acmicpc.net/problem/11725
* 요약
트리의 루트가 1일 때, 각 노드의 부모 출력하기
* 풀이
루트를 시작으로 bfs 탐색
* 메모리
63732 KB
* 시간
540 ms
*/

public class boj_11725 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N; // 노드 개수 (1~N)
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>(); // 인접리스트
    static int[] parents; // 부모 표기 및 방문 여부 체크

    public static void main(String[] args) throws IOException {
        // 입력
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<Integer>());
        }

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            list.get(n1).add(n2);
            list.get(n2).add(n1);
        }
        parents = new int[N+1];

        // 풀이
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int num : list.get(cur)) {
                if(parents[num] == 0) {
                    parents[num] = cur;
                    q.offer(num);
                }
            }
        }

        // 출력
        for(int i=2; i<=N; i++) {
            sb.append(parents[i]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
