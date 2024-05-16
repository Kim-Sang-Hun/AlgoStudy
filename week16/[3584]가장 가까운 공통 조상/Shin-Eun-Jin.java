import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3584_가장가까운공통조상 {
    static int N;
    static int[] parent;
    static boolean[] isVisited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            parent = new int[N + 1];
            isVisited = new boolean[N + 1];

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());

                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                parent[B] = A;
            }

            // 공통 조상 찾기
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            dfs(num1);
            dfs(num2);

        }

        System.out.println(sb);

    }

    static void dfs(int node) {

        if (isVisited[node]) {
            sb.append(node).append("\n");
            return;
        }

        isVisited[node] = true;

        if (node == 0) {
            return;
        }

        dfs(parent[node]);
    }
}
