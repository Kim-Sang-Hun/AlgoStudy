import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9466_텀프로젝트 {
    static int n;
    static int[] nums;
    static boolean[] visited;
    static boolean[] finished;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());
            nums = new int[n + 1];
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];
            count = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }

            System.out.println(n - count);
        }
    }

    static void dfs(int current) {
        visited[current] = true;
        
        int next = nums[current];
        
        if (!visited[next]) {
            dfs(next);
        } else if (!finished[next]) {
            for (int i = next; i != current; i = nums[i]) {
                count++;
            }
            count++; 
        }
        
        finished[current] = true;
    }
}
