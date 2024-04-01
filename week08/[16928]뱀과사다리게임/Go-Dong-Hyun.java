package Algo_week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16928 {
    
    static int N, M;
    static int[] visited;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[101]; // 크기를 101로 수정
        visited = new int[101]; // 크기를 101로 수정
        
        for (int i = 0; i < 101; i++) {
			arr[i] = i;
			visited[i] = 0;
		}

        for (int i = 0; i < N + M; i++) { // 사다리와 뱀의 개수만큼 반복
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[start] = end;
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        visited[1] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 1; i <= 6; i++) {
                int next = arr[cur + i];
                if (next <= 100 && visited[next] == 0) {
                	if (next == 100) 
                		return visited[cur]+1;
                	
                    if (arr[next] != 0) 
                        next = arr[next];


                    visited[next] = visited[cur] + 1;
                    q.add(next);
                }
            }
        }

        return -1;
    }
}
