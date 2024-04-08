package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <단절점과 단절선> S1
* 링크
* https://www.acmicpc.net/problem/14675
* 요약
* tree 성질 숙지하기
* 풀이
* 입력이 트리임이 보장
* -> leaf 는 단절점이 될 수 없다.
* -> branch 는 단절점이다.
* -> 주어진 입력이 tree 임이 보장되므로 모든 edge는 단절선이다.
*/
public class boj_14675 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N; // 정점 개수
	static ArrayList<ArrayList<Integer>> list; // 인접리스트
	static int m; // 질의 개수

	public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine().trim());
		list = new ArrayList<>();
		for (int i = 0; i <= N; i++)
			list.add(new ArrayList<>());
		
		// 인접리스트
		for (int i = 1; i <= N - 1; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}

		// 풀이
		m = Integer.parseInt(br.readLine().trim());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int queryType = Integer.parseInt(st.nextToken());
			boolean ans;
			if (queryType == 1) {
				int nodeNum = Integer.parseInt(st.nextToken());
				ans = isSingleTreeWithoutNode(nodeNum);
			} else { // queryType==2
				int edgeNum = Integer.parseInt(st.nextToken());
				ans = isSingleTreeWithoutEdge(edgeNum);
			}
			if (ans)
				sb.append("yes\n");
			else
				sb.append("no\n");
		}

		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static boolean isSingleTreeWithoutEdge(int edgeNum) {
		// 트리이므로 모든 edge가 단절선임
		return true;
	}

	private static boolean isSingleTreeWithoutNode(int node) {
		// 연결된 노드가 1개다 == leaf
		if(list.get(node).size()>1) return true; // 단절점임
		return false; // 단절점 아님
	}
}
