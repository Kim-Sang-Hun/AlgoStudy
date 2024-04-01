import java.io.*;
import java.util.*;
/*
 * 제목
 * <계란으로 계란치기> G5
 * 링크
 * https://www.acmicpc.net/problem/16987
 * 요약
 * N개의 계란을 한 번 씩 들어서 다른 계란 쳤을 때 최대한 많이 깨지는 경우 구하기
 * 풀이
 * dfs
 */
public class boj_16987 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static class Egg{
		int durability;
		int weight;
		public Egg(int durability, int weight) {
			this.durability = durability;
			this.weight = weight;		
		}		
	}
	static int N; // 계란 수
	static Egg[] egg;
	static boolean[] broken;
	static int maxBreak = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		egg = new Egg[N];
		broken = new boolean[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int durability = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			egg[i] = new Egg(durability, weight);
		}
		// 풀이
		dfs(0); // 계란 치기 시도 횟수
		// 출력
		sb.append(maxBreak);
		bw.write(sb.toString());
		bw.flush();
	}

	private static void dfs(int depth) {
		if(depth == N || eggCheck()<=1) { // 8번 쳤거나, 남은 계란이 1개 이하인 경우 계란치기 종료
			maxBreak = Math.max(maxBreak, N-eggCheck());
			return;
		}
		else if(broken[depth]) // 현재 선택된 계란이 깨진 계란이면 그 오른쪽 계란 선택
			dfs(depth+1);
		else {
			for(int i=0; i<N; i++) {
				if(broken[i]) continue; // 이미 깨진 계란이면 다음 계란으로
				if(i==depth) continue; // 손에 든 계란으로 셀프 꺠기 예외처리
				egg[i].durability -= egg[depth].weight;
				egg[depth].durability -= egg[i].weight;
				if(egg[i].durability <= 0) broken[i] = true;
				if(egg[depth].durability <= 0) broken[depth] = true;
				dfs(depth+1);
				egg[i].durability += egg[depth].weight;
				egg[depth].durability += egg[i].weight;
				broken[i] = false;
				broken[depth] = false;
			}	
		}
	}	
	
	// 모든 계란이 부서졌는지 확인 (안깨진 계란 개수 반환)
	private static int eggCheck() {
		int cnt = 0;
		for(boolean b : broken)	if(!b) cnt++; // 안꺠졌으면 cnt++
		return cnt;
	}
}
