package BOJ;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <벽 부수고 이동하기 4> G2
 * 링크
 * https://www.acmicpc.net/problem/16946
 * 요약
 * 
 * 풀이
 * bfs
 * 빈칸(0)이면 방문 체크 하면서 이어진 0 개수 파악(cnt), 해당 구역과 닿은 벽 좌표 유지
 * bfs 종료 후 인접한 벽들에 cnt 더해주기
 * 벽(1)이면 해당 칸 ++
 */
public class boj_16946 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static class Node {
        int i, j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static int N, M;
    static int[][] arr, ans; // 입력 배열, 정답 배열
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        // 입력
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        ans = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(str.charAt(j) + "");
            }
        }

        // 풀이
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visit[i][j]) { // 이미 방문한 곳인 경우
                    continue;
                } else if (arr[i][j] == 0) { // 빈 칸인 경우
                    bfs(i, j);
                } else { // 벽인 경우
                    ans[i][j]++;
                    ans[i][j] %= 10;
                }
            }
        }

        // 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(ans[i][j]);
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static HashSet<Integer> set = new HashSet<>();
    static Queue<Node> q = new ArrayDeque<>();
    static int[] di = { -1, 1, 0, 0 };
    static int[] dj = { 0, 0, -1, 1 };

    private static void bfs(int si, int sj) {
        // init
        set.clear();

        // bfs
        Node start = new Node(si, sj);
        int cnt = 1;
        visit[si][sj] = true;
        q.add(start);

        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];
                if (!isIn(ni, nj)) { // 범위 밖 이면 continue
                    continue;
                }
                if (visit[ni][nj]) { // 이미 방문한 곳이면 continue
                    continue;
                }
                if (arr[ni][nj] == 1) { // 벽이면 set에 추가 후 continue
                    int num = ni * M + nj;
                    set.add(num);
                    continue;
                }
                Node next = new Node(ni, nj);
                cnt++;
                visit[ni][nj] = true;
                q.add(next);
            }
        }

        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            int num = it.next();
            int i = num / M;
            int j = num - (M * i);
            ans[i][j] += cnt;
            ans[i][j] %= 10;
        }
    }

    private static boolean isIn(int ni, int nj) {
        return 0 <= ni && ni < N && 0 <= nj && nj < M;
    }
}
