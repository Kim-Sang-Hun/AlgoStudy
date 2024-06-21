package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <회장뽑기> G5
* 링크
* https://www.acmicpc.net/problem/2660
* 요약
* 
* 풀이
* bfs 완탐
* 메모리
* 14156KB
* 시간
* 132ms
*/
public class boj_2660 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int numOfPeople;
	static int[][] relationArr;

	static int minimumScore = Integer.MAX_VALUE;
	static ArrayList<Integer> bossList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		// 입력
		numOfPeople = Integer.parseInt(br.readLine());
		relationArr = new int[numOfPeople + 1][numOfPeople + 1];
		while (true) {
			st = new StringTokenizer(br.readLine());
			int person1 = Integer.parseInt(st.nextToken());
			int person2 = Integer.parseInt(st.nextToken());

			if (person1 == -1 && person2 == -1)
				break;

			relationArr[person1][person2] = 1;
			relationArr[person2][person1] = 1;
		}

		// 풀이
		for (int i = 1; i <= numOfPeople; i++) {
			bfs(i);
		}

		// 출력
		sb.append(minimumScore).append(' ').append(bossList.size()).append('\n');
		for(int num : bossList) {
			sb.append(num).append(' ');
		}
		
		bw.write(sb.toString());
		bw.flush();
	}

	private static void bfs(int start) {
		// bfs
		int[] friendCheck = new int[numOfPeople + 1];
		Arrays.fill(friendCheck, -1);
		friendCheck[start] = 0; // 자기 자신
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);

		while (!q.isEmpty()) {
			int current = q.poll();
			int currentScore = friendCheck[current] + 1;
			for (int i = 1; i <= numOfPeople; i++) {
				if (relationArr[current][i] == 1 && friendCheck[i] == -1) {
					q.offer(i);
					friendCheck[i] = currentScore;
				}
			}
		}

		Arrays.sort(friendCheck);
	
		// 모두와 친구일 때
		if (friendCheck[1] != -1) {
			// 기존 회장 후보 점수와 같다면
			if(minimumScore == friendCheck[numOfPeople]) {				
				bossList.add(start);
			// 기존 회장 후보들보다 더 쎈 후보라면 기존 회장 후보들 모두 제거 후 추가
			} else if(minimumScore > friendCheck[numOfPeople]) {
				minimumScore = friendCheck[numOfPeople];
				bossList = new ArrayList<>();
				bossList.add(start);
			}
		}
	}
}
