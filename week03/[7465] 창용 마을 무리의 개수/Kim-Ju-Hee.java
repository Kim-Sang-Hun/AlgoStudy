package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA7465 {
	static int N, M, answer;
	static boolean relations[][], visited[];
	static Set<Integer> included;
	
	private static void bfs(int start) {
		Stack<Integer> stack = new Stack<Integer>();
		
		stack.add(start);
		while(!stack.isEmpty()) {
			int now = stack.pop();
			
			for (int i = 1; i <= N; i++) {
				if(relations[now][i] && !included.contains(i)) {
					included.add(i);
					stack.add(i);
				}
			}
		}
		answer++;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			answer = 0;
			included = new HashSet<Integer>();
			
			relations = new boolean[N+1][N+1];
			for (int i = 0; i < M; i++) {
				StringTokenizer st1 = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st1.nextToken());
				int b = Integer.parseInt(st1.nextToken());
				relations[a][b] = true;
				relations[b][a] = true;
			}
			
			
			// search
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(relations[i][j] && !included.contains(i)) {
						included.add(i);
						bfs(i);
					}
				}
			}
			
			for (int i = 1; i <= N; i++) { // 포함 안 된 사람들도 하나의 무리임 ;;
				if(!included.contains(i)) answer++;
			}
			
			// 출력
			System.out.printf("#%d %d\n", tc, answer);
		}
	}
}
