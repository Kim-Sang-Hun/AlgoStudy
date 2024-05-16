import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1563_골목대장호석 {
    static int N, M, A, B, C;
    static int[][] costs;
    static boolean[] isVisited;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 교차로 개수
        M = Integer.parseInt(st.nextToken()); // 골목 개수
        A = Integer.parseInt(st.nextToken()); // 시작 교차로 번호
        B = Integer.parseInt(st.nextToken()); // 도착 교차로 번호
        C = Integer.parseInt(st.nextToken()); // 가진 돈
        costs = new int[N + 1][N + 1];
        isVisited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            costs[n1][n2] = cost;
            costs[n2][n1] = cost;
        }

        ans = Integer.MAX_VALUE;
        bt(A, 0, 0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void bt(int num, int maxCost, int totalCost) {
        if (totalCost > C) {
            return;
        }

        if (num == B) {
            ans = Math.min(maxCost, ans);
            return;
        }

        for (int i = 0; i <= N; i++) {
            if (costs[num][i] == 0 || isVisited[i]) {
                continue;
            }

            isVisited[i] = true;
            bt(i, Math.max(maxCost, costs[num][i]), totalCost + costs[num][i]);
            isVisited[i] = false;
        }

    }
}
