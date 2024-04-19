package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <개똥벌레> G5
* 링크
* https://www.acmicpc.net/problem/3020
* 요약
* 
* 풀이
* lower bound bs
*/

public class boj_3020 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, H;
	static ArrayList<Integer> top = new ArrayList<>();
	static ArrayList<Integer> bot = new ArrayList<>();

	static int minObstacle = Integer.MAX_VALUE;
	static int minCount = 0;

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken()); // 동굴길이 (짝수)
		H = Integer.parseInt(st.nextToken()); // 높이

		for (int i = 0; i < N / 2; i++) {
			bot.add(Integer.parseInt(br.readLine().trim()));
			top.add(Integer.parseInt(br.readLine().trim()));
		}

		// 풀이
		solution();

		// 출력
		sb.append(minObstacle).append(" ").append(minCount);
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		Collections.sort(bot); // 오름차순 정렬
		Collections.sort(top); // 오름차순 정렬

		for (int i = 1; i <= H; i++) {
			int botCount = binarySearch(bot, i);
			int topCount = binarySearch(top, H - i + 1);
//			System.out.println("H:" + i + " BOT:" + botCount + " TOP:" + topCount);
			if (minObstacle == botCount + topCount) {
				minCount++;
			} else if (minObstacle > botCount + topCount) {
				minObstacle = botCount + topCount;
				minCount = 1;
			}
		}
	}

	private static int binarySearch(ArrayList<Integer> list, int h) {
		int idx = Collections.binarySearch(list, h);
		if (idx < 0) { // h가 리스트에 없는 경우
			idx = (idx + 1) * -1;
			if (idx == list.size()) {// 삽입 위치가 리스트 끝 == 부술 수 있는 장애물이 없다
				return 0;
			} else {// 삽입 위치가 리스트 중간 == 해당 위치 이후의 장애물들을 부수면서 지나간다
				idx = setPos(list, idx, h);
				return (N / 2) - idx;
			}
		} else { // h가 리스트에 있는 경우
			idx = setPos(list, idx, h);
			return (N / 2) - idx;
		}
	}

	// lower bound
	// Collections.binarySearch() 는 리스트에 중복값 있을 경우 찾고자 하는 원소의 인덱스(또는 없을 경우 삽입 위치) 보장x
	// 그래서 가장 첫 위치로 옮겨줌 (값 있을 경우 가장 빠른 위치, 없을 경우 삽입 가능한 가장 빠른 위치)
	private static int setPos(ArrayList<Integer> list, int idx, int h) {
		while(idx >= 1 && list.get(idx-1)>=h) {
			idx--;
		}
		return idx;
	}
}
