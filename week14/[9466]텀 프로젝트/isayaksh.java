import java.util.*;
import java.io.*;

public class isayaksh {
    
    private static int N, answer;
    private static int[] next;
    private static int[] visited;
    /*
     * -1 : 불가능
     *  0 : 초기값
     *  1 : 진행중
     *  2 : 싸이클
     */

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {

            N = Integer.parseInt(br.readLine());
        
            answer = 0;
            next = new int[N+1];
            visited = new int[N+1];

            st = new StringTokenizer(br.readLine());
            for(int n = 1; n < N+1; n++) {
                next[n] = Integer.parseInt(st.nextToken());
                // 혼자 하고 싶어하는 학생
                if(n == next[n]) {
                    visited[n] = 2;
                }
            }

            for(int n = 1; n < N+1; n++) {
                if(visited[n] != 0) continue;
                dfs(n);
                cover(n);
            }

            for(int n = 1; n < N+1; n++) {
                if(visited[n] != 2) answer++;
            }

            sb.append(answer).append("\n");

        }

        System.out.println(sb);

    }

    private static void dfs(int student) {
        // 싸이클이 존재(2)하거나, 싸이클이 불가능(-1)
        if(visited[student] == 2 || visited[student] == -1) return;
        else if(visited[student] == 1) visited[student] = 2;
        else if(visited[student] == 0) visited[student] = 1;
        dfs(next[student]);
    }

    private static void cover(int student) {
        if(visited[student] != 1) return;
        visited[student] = -1;
        cover(next[student]);
    }

}
