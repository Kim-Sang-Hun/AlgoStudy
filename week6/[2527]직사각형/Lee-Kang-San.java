import java.io.*;
import java.util.*;
/*
 * 제목
 * <직사각형> S1
 * 링크
 * https://www.acmicpc.net/problem/2527
 * 요약
 * 입력으로 주어진 두 사각형이 겹치는 정도 파악하기 (면, 선, 점, X)
 * 풀이
 * 구현
 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int x1, y1, p1, q1, x2, y2, p2, q2; // (xy) 직사각형 좌하단 꼭짓점, (pq) 직사각형 우상단 꼭짓점
    public static void main(String[] args) throws IOException {
		for(int i=0; i<4; i++) {
	    	// 입력 
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			p1 = Integer.parseInt(st.nextToken());
			q1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			p2 = Integer.parseInt(st.nextToken());
			q2 = Integer.parseInt(st.nextToken());
			// 풀이
			char c = solution();
			// 출력
			sb.append(c+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static char solution() {
		if(p1<x2 || p2<x1 || q1<y2 || q2<y1)
			return 'd';	// 공통부분 없음
		else if(x1==p2 && y1==q2 || x1==p2 && q1==y2 ||	p1==x2 && y1==q2 ||	p1==x2 && q1==y2)
			return 'c'; // 점
		else if(y1==q2 || q1==y2 || x1==p2 || p1==x2)
			return 'b'; // 선
		else 
			return 'a'; // 면
	}
}
