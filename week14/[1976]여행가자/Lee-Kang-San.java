import java.io.*;
import java.util.*;

/*
* 제목
* <여행 가자> G4
* 링크
* https://www.acmicpc.net/problem/1976
* 요약
* 
* 풀이
* 플로이드 워셜
*/
public class boj_1976 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static final int INF = 123456789;

	static int N; // 도시의 수 1~N
	static int M; // 여행 계획에 속한 도시의 수

	static int[][] adjList;

	static ArrayList<Integer> plan = new ArrayList<>();

	public static void main(String[] args) throws IOException {
    //입력
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());

		adjList = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 1; j <= N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(i==j) {
					adjList[i][j] = 1; // 같은 도시로 여행가는 경우 고려
				}
				if (adjList[i][j] == 1) // 무향 그래프로 그려주기
					continue;
				if (temp == 0) {
					adjList[i][j] = INF;
				} else if(temp == 1) { 
					adjList[i][j] = 1;
					adjList[j][i] = adjList[i][j];
				}
			}
		}
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < M; i++) {
			plan.add(Integer.parseInt(st.nextToken()));
		}
    
    // 풀이
		for (int k = 1; k <= N; k++) {
			for (int p = 1; p <= N; p++) {
				for (int q = 1; q <= N; q++) {
					if (p == q)
						continue;
					if (adjList[p][q] > adjList[p][k] + adjList[k][q]) {
						adjList[p][q] = adjList[p][k] + adjList[k][q];
					}
				}
			}
		}

		boolean canGo = true;
		for (int i = 0; i < M - 1; i++) {
			if (adjList[plan.get(i)][plan.get(i + 1)] == INF) { // 여행 경로 2개씩 보면서 갈 수 있는 지 확인
				canGo = false;
				break;
			}
		}

    // 출력
		if (canGo) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
}
