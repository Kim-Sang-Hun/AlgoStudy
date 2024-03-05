package algo_group_study;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <트리> G4
 * 링크
 * https://www.acmicpc.net/problem/4803
 * 요약
 * 주어진 그래프에 몇 개의 트리가 있는 지 파악하기
 * 풀이
 * 서로소집합
 */
public class boj_4803 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static class Edge {
		int u, v;
		public Edge(int u, int v) {
			this.u = u;
			this.v = v;
		}
	}

	static int n, m; // 정점 개수 n, 간선 개수 m
	static Edge[] list; // 간선 리스트
	static int[] parents;
	
    public static void main(String[] args) throws IOException {
    	for(int tc=1; ;tc++) {
    		// 입력
    		st = new StringTokenizer(br.readLine());
    		n = Integer.parseInt(st.nextToken());
    		m = Integer.parseInt(st.nextToken());
    		if(n==0 && m==0) break;
    		list = new Edge[m];
    		for(int i=0; i<m; i++) {
    			st = new StringTokenizer(br.readLine());
    			int u = Integer.parseInt(st.nextToken());
    			int v = Integer.parseInt(st.nextToken());
    			list[i] = new Edge(u, v);
    		}
    		// 풀이
    		int cnt = unionFind();
    		// 출력
    		sb.append("Case "+tc+": ");
    		if(cnt==1) sb.append( "There is one tree.\n");
    		else if(cnt>1) sb.append("A forest of "+cnt+" trees.\n");
    		else sb.append("No trees.\n");
    	}
		bw.write(sb.toString());
		bw.flush();
	}

	private static int unionFind() {
		Stack<Integer> cycleChecker  = new Stack<>();
		Set<Integer> components = new HashSet<>();
		parents = new int[n+1];
		// make set
		for(int i=1; i<=n; i++) parents[i]=i;
		// 그래프 그리기
		for(int i=0; i<m; i++) {
			boolean isCycle = union(list[i].u, list[i].v);
			if(isCycle) { // 사이클 발생시키는 간선은 연결하지 않고 따로 관리
				cycleChecker.add(list[i].u); // 사이클 발생한 정점 u 스택에 넣고 기억해놓기
			}
		}
		
		// 각 정점에 대해 자신이 속한 트리의 대표자 노드로 부모 최신화
		for(int i=1; i<=n; i++) {
			find(i);
		}
		
		// 싸이클 발생한 집합의 정점들 부모 값 -1로 변경
		while(!cycleChecker.isEmpty()) {
			int v = cycleChecker.pop();
			int parentsIdx = parents[v];
			for(int i=1; i<=n; i++) {
				if(parents[i]==parentsIdx) parents[i]=-1;
			}
		}
		
		for(int i=1; i<=n; i++) {
			components.add(parents[i]);
		}

		int cnt = components.size();
		if(components.contains(-1)) cnt--; // -1 값 제외
		return cnt; 
	}

	private static boolean union(int u, int v) {
		int rootU = find(u);
		int rootV = find(v);
		if(rootU==rootV) return true;
		parents[rootV] = rootU;
		return false;
	}

	private static int find(int v) {
		if(parents[v]==v) return v;
		return parents[v] = find(parents[v]);
	}
}















