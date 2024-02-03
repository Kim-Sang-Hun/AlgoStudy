package BOJ;

import java.io.*;
import java.util.*;

public class boj_11000_array {
	static class myClass implements Comparable<myClass> {
		int start, end, diff;
		myClass (int start, int end, int diff) {
			this.start = start;	this.end = end;	this.diff = diff;
		}
		@Override
		public int compareTo(myClass o) {	// 강의 시작 시간 빠른 순, 같으면 시간 짧은 순
			if(this.start == o.start) return this.diff - o.diff;		
			return this.start - o.start;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int N; 					// 강의 수
	static int tempS, tempT, tempDiff;
	static myClass[] arr;
	static int cnt;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());	// 입력
		arr = new myClass[N];
		for(int i=0; i<N; ++i) {				// 강의 입력 (시작시간, 종료시간, 차이)
			st = new StringTokenizer(br.readLine());
			tempS = Integer.parseInt(st.nextToken());
			tempT = Integer.parseInt(st.nextToken());
			tempDiff = tempT - tempS;
			arr[i] = new myClass(tempS, tempT, tempDiff);
		}
		Arrays.sort(arr);						// 강의 시간 짧은 순으로 정렬
		solution();								// 풀이
		bw.write(cnt+"");						// 출력
		bw.flush();
	}

	private static void solution() {
		cnt = 0; 								// 현재 사용중인 강의실 수
		int[] timeLine = new int[N];			// N개 강의 -> 최악 시 최대 N개 강의실 가능, 종료시간 저장
		label:
		for(int i=0; i<N; ++i) {				// 모든 강의 접근
			for(int j=0; j<cnt; ++j) {			// i번째 강의의 시작시간 이상인 값(종료시간) 있는 지 탐색
				if(timeLine[j] <= arr[i].start) {	
					timeLine[j] = arr[i].end;	// 있으면 i번째 강의의 종료시간으로 업데이트 후 다음 강의로 continue
					continue label;
				}
			}
			timeLine[cnt] = arr[i].end;			// 없으면 강의실 사용+1 하고 종료시간 저장
			cnt++;
		}
	}
}