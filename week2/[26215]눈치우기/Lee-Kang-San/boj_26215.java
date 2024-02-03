package BOJ;

import java.io.*;
import java.util.*;

public class boj_26215 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N;				// 집의 수
	static int[] snows;			// [i]번째 집 눈 높이
	static int time = 0;
	public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		snows = new int[N+1];	// [0] 제외
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; ++i) {
			snows[i] = Integer.parseInt(st.nextToken()); 
		}
		// 풀이
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}
	private static void solution() {
		Arrays.sort(snows);
		while(snows[N-1]!=0) {
			time+=snows[N-1];
			snows[N] -= snows[N-1];
			snows[N-1] = 0;
			Arrays.sort(snows);
		}
		time += snows[N];
		if(time > 1440)	time = -1;
		sb.append(time);
	}
}
