package algo_group_study;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <게리맨더링 2> G3
 * 링크
 * https://www.acmicpc.net/problem/17779
 * 요약
 * 선거구 나누기
 * 풀이
 * 배열 좌표 계산 너무 싫다
 */
public class boj_17779 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[][] map; // 인구수 
	static int[][] temp; // 0~4 값으로 선거구 표시 0값->5번 선거구, 1~4값->해당 선거구
	static int totalCnt; // 총 인구수
	static int[] cnt; // 선거구 별 인구수 저장, [0]->5번 선거구 인구수, [1]~[4]:해당 선거구 인구수
	static int minDiff = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1]; // [0] 제외
		temp = new int[N+1][N+1]; // [0] 제외
		cnt = new int[5];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				totalCnt += map[i][j];
			}
		}
		// 풀이
		solution();
		// 출력
		sb.append(minDiff);
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		for(int y=2; y<=N-1; y++) { // 1. 기준점 정하기 (2, 1) ~ (N-2, N-1)
			for(int x=1; x<=N-2; x++) {
				for(int d1=1; y-d1>=1; d1++) { // 2. d1, d2 정하기
					for(int d2=1; y+d2<=N && x+d1+d2<=N; d2++) {
						cntArea(x, y, d1, d2);
					}
				}
			}
		}
	}
	

	private static void cntArea(int x, int y, int d1, int d2) {
		// 초기화
		for(int i=1; i<=N; i++)	Arrays.fill(temp[i], 0);
		Arrays.fill(cnt, 0);
		// 기준점, 경계 길이로 선거구 나누기
		for(int i=1, end = x+d1; i<y; i++) { // 1선거구
			if(i>=y-d1) end--;
			for(int j=1; j<=end; j++) {
				temp[i][j]=1;
			}
		} 
		for(int i=1, start = x+d1+1; i<=y-d1+d2; i++) { // 2선거구
			if(i>y-d1) start++;
			for(int j=start; j<=N; j++) {
				temp[i][j]=2;
			}
		} 
		for(int i=y, start = x-1; i<=N; i++) { // 3선거구
			for(int j=start; j>=0; j--) {
				temp[i][j]=3;
			}
			if(i<y+d2) start++;
		} 
		
		for(int i=y-d1+d2+1, start = x+d1+d2; i<=N; i++) { // 4선거구
			for(int j=start; j<=N; j++) {
				temp[i][j]=4;
			} 
			if(i<=y+d2) start--;
		} 
		
		for(int i=1; i<=N; i++) { // 선거구 별 인구수 계산
			for(int j=1; j<=N; j++) {
				if(temp[i][j]==1) { // 1번 선거구
					cnt[1] += map[i][j];
				} else if(temp[i][j]==2) {
					cnt[2] += map[i][j];
				} else if(temp[i][j]==3) {
					cnt[3] += map[i][j];
				} else if(temp[i][j]==4) {
					cnt[4] += map[i][j];
				} 
			}
		}
		cnt[0] = totalCnt - (cnt[1]+cnt[2]+cnt[3]+cnt[4]);
		Arrays.sort(cnt);
		int diff = cnt[4]-cnt[0];
		minDiff = Math.min(minDiff, diff);		
	}
}
