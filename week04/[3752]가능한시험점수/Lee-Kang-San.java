package algo_group_study;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <가능한 시험 점수> D4
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWHPkqBqAEsDFAUn
 * 요약
 * 문제의 개수가 주어질 때, 학생들이 받을 수 있는 시험점수의 경우의 수 구하기
 * 풀이
 * set의 원소들에 하나씩 더하면서 집합 생성
 */
public class swea_3752 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int T; // 테스트케이스 수
	static int N; // 각 tc마다 주어지는 문제 수
	static int[] score; // i번 문제의 점수 [i]
	
    public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			// 입력
			N = Integer.parseInt(br.readLine());
			score = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) score[i] = Integer.parseInt(st.nextToken());
			// 풀이
			Arrays.sort(score);
			int cnt = solution();
			// 출력
			sb.append("#"+tc+" "+cnt+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static int solution() {
		Set<Integer> set = new HashSet<>();
		set.add(0);
		for(int i=0; i<N; i++) {
			List<Integer> list = new ArrayList<>(set);
			for(int j=0; j< list.size(); j++) {
				set.add(list.get(j)+score[i]);
			}
		}
		return set.size();
	}
}
