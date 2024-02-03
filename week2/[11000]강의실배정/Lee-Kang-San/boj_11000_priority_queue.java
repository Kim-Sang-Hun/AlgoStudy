package BOJ;

import java.io.*;
import java.util.*;

public class boj_11000_priority_queue {
	static class myClass implements Comparable<myClass> {
		int start, end, diff;
		myClass (int start, int end) {
			this.start = start;	
			this.end = end;
		}
		@Override
		public int compareTo(myClass o) {	// 강의 시작 시간 빠른 순
			return this.start - o.start;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int N; 					// 강의 수
	static int tempS, tempT;
	static PriorityQueue<myClass> mc;
	static int cnt;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());	// 입력
		mc = new PriorityQueue<>();
		for(int i=0; i<N; ++i) {				// 강의 입력 (시작시간, 종료시간, 차이)
			st = new StringTokenizer(br.readLine());
			tempS = Integer.parseInt(st.nextToken());
			tempT = Integer.parseInt(st.nextToken());
			mc.offer(new myClass(tempS, tempT));
		}
		solution();								// 풀이
		bw.write(cnt+"");						// 출력
		bw.flush();
	}

	private static void solution() {
		PriorityQueue<Integer> timeTable = new PriorityQueue<>();
		timeTable.offer(0);								// 초기화 (0시에 끝나는 강의)
		for(int i=0; i<N; ++i) {							// 모든 강의 접근 (시작시간 오름차순)
			if(mc.peek().start >= timeTable.peek()) {		// 시간표에서 가장 빠른 종료시간 <= 남은 강의 중 가장 빠른 시작시간이면 
				timeTable.poll();							// 시간표에 업데이트
				timeTable.offer(mc.poll().end);
				continue;
			}
			timeTable.offer(mc.poll().end);					// 없으면 그냥 시간표에 추가
		}
		cnt = timeTable.size();
	}
}

