package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <텀 프로젝트> G3
* 링크
* https://www.acmicpc.net/problem/9466
* 요약
* 한팀 : 사이클이거나, 1인팀이거나
* 풀이
* dfs +stack
*/
public class boj_9466 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n; // 학생 수
	static int[] favorite; // 같이 팀 하고 싶은 사람 저장
	static boolean[] visit; // 방문 여부
	static Stack<Integer> stack = new Stack<>();
	
	static int cnt;

	public static void main(String[] args) throws IOException {
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 0; tc < testCase; tc++) {
			// 입력
			n = Integer.parseInt(br.readLine().trim());
			favorite = new int[n+1];

			st = new StringTokenizer(br.readLine().trim());
			for (int i = 1; i <= n; i++) {
				favorite[i] = Integer.parseInt(st.nextToken());
			}

			// 풀이
			visit = new boolean[n + 1]; 
			cnt = n;
			for (int i = 1; i <= n; i++) {
				if(visit[i])
					continue;
				stack.removeAllElements();
				visit[i] = true;
				stack.add(i);
				dfs(i, i, 0);
			}

			// 출력 : 프로젝트 팀에 속하지 못한 학생들의 수
			sb.append(cnt).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	static void dfs(int start, int current, int depth) {
		int next = favorite[current];
		// 더이상 진행 못하면 종료
		if(visit[next]) {
			// next(==start)가 path 안에 있으면 next 나올 때까지 cnt--
			if(stack.contains(next)) {
				while(stack.peek() != next) {
					stack.pop();
					cnt--;
				}
				stack.pop();
				cnt--;
			}
 			return; 
		} else {
			visit[next] = true;
			stack.add(next);
			dfs(start, next, depth+1);
		}
	}
}
