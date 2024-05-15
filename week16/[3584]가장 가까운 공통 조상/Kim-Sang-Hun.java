import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
a, b의 가장 가까운 공통 조상을 찾는다.
먼저 a의 부모로 계속 가며 visited처리해주고
b의 부모로 계속 가며 visited된 곳이 가장 가까운 공통 조상일 것이다.
메모리 25300kb, 시간 253ms
 */

public class JUN3584_가장가까운공통조상 {
    static int n, answer;
    static int[] arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(st.nextToken());
        for (int T = 0; T < t; T++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n + 1];
            visited = new boolean[n + 1];
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                arr[child] = parent;
            }
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            findParent(a);
            findParent(b);
            sb.append(answer).append(System.lineSeparator());
        }
        System.out.println(sb);
    }

    public static void findParent(int num) {
        if (visited[num]) {
            answer = num;
            return;
        }
        visited[num] = true;
        if (arr[num] == 0) {
            return;
        }
        findParent(arr[num]);
    }
}
