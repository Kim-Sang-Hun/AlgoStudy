import java.io.*;
import java.util.*;
/*
 * 제목
 * <가장 가까운 세 사람의 심리적 거리>
 * 링크
 * https://www.acmicpc.net/problem/20529
 * 요약
 * N명 중 가장 심리적 거리 합이 작은 3명 구하기
 * 풀이
 * 비둘기집, nC3
 */
public class boj_20529 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int T;
	static int N;
	static String[] mbti;	// 입력 받은 N명의 mbti
	static int[][] distance;	// 
	static int[] threeGuys = new int[3];	// nC3으로 주어진 3명의 index
	static int min;
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; ++i) {								// 테스트케이스 수 만큼 반복
			// 입력
			N = Integer.parseInt(br.readLine());				// N : mbti 수 (사람 수)
			mbti = new String[N];		
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; ++j)	mbti[j] = st.nextToken();	// N개 mbti 입력
			// 풀이
			if(N>32) {	// 사람 수가 32명이 넘어간다 == 3명 이상 같은 mbti가 존재 == 거리 0
				sb.append(0+"\n");
				continue;
			}
			distance = new int[N][N];
			min = Integer.MAX_VALUE;
			setDistanceArr();
			solution(0, 0);
			sb.append(min+"\n");
		}
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}
	
	private static void solution(int depth, int start) {
		if(depth == 3) {
			min = Math.min(min, threeGuysDistance());
			return;
		}
		for(int i=start; i<N; ++i) {	// nC3
			threeGuys[depth] = i;
			solution(depth+1, i+1);
		}
	}

	private static int mbtiToInteger(String string) {
		int x = 0;
		// EI (8) / SN (4) / TF (2) / JP (1) / 01
		if(string.charAt(0)=='E') x+=8;
		if(string.charAt(1)=='S') x+=4;
		if(string.charAt(2)=='T') x+=2;
		if(string.charAt(3)=='J') x+=1;
		return x;
	}

	static void setDistanceArr() { // N명 간 심리적 거리 
		int[] integerMbti = new int[N];
		for(int i=0; i<N; i++) {
			integerMbti[i] = mbtiToInteger(mbti[i]);
		}
		for(int i=0; i<N; i++) distance[i][i] = 0;
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				distance[i][j] = integerMbti[i] ^ integerMbti[j]; // 검출된 1 개수 만큼이 두 사람 간의 거리
				switch (distance[i][j]) {
					case 0 : ; break; 					// 0000	
					case 8 :							// 1000
					case 4 :							// 0100
					case 2 :							// 0010
					case 1 : distance[i][j]=1; break;	// 0001
					case 12 :							// 1100
					case 10 :							// 1010
					case 9 :							// 1001
					case 6 :							// 0110
					case 5 :							// 0101
					case 3 : distance[i][j]=2; break;	// 0011
					case 14 :							// 1110
					case 13 :							// 1101
					case 11 :							// 1011
					case 7 : distance[i][j]=3; break;	// 0111
					case 15 : distance[i][j]=4;			// 1111
				}
				distance[j][i] = distance[i][j];
			}
		}
	}
	
	static int threeGuysDistance() {
		return distance[threeGuys[0]][threeGuys[1]]+
				distance[threeGuys[1]][threeGuys[2]]+
				distance[threeGuys[0]][threeGuys[2]];
	}
}