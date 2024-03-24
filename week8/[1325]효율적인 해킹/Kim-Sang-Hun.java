import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 뭐 이딴 문제가 다있지?
// 암튼 신뢰관계를 반대로 생각해서 풀어야 하는 문제랍니다
// 연산을 최소화해야 하는 문제
// 밑에처럼 푸니까 9948ms 나왔어요
public class Main {

    static int N;
    static List<List<Integer>> canHack;
    static int[] count;
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        canHack = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            canHack.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            canHack.get(to).add(from);
        }
        count = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            visited = new int[N + 1];
            bfs(i);
        }
        StringBuilder sb = new StringBuilder();
        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (max < count[i])
                max = count[i];
        }
        for (int i = 1; i <= N; i++) {
            if (max == count[i]) sb.append(i + " ");
        }

        System.out.println(sb);
    }

    public static void bfs(int i) {
        visited[i] = 1;
        Queue<Integer> qu = new ArrayDeque<>();
        qu.add(i);

        while (!qu.isEmpty()) {
            int cur = qu.poll();
            for (int next : canHack.get(cur)) {
                if (visited[next] == 1) continue;
                visited[next] = 1;
                count[next]++;
                qu.add(next);
            }
        }
    }
}
