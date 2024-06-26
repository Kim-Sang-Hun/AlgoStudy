package programmers;

import java.util.*;

/*
* 제목
* <호텔 대실> Lv.2
* 링크
* https://school.programmers.co.kr/learn/courses/30/lessons/155651
* 요약
강의실 배정 문제
* 풀이
입실 시간으로 정렬
pq1에서 하나씩 poll 후 가장 빠른 퇴실 시간과 비교.
입실 시간이 가장 빠른 퇴실 시간보다 늦으면 pq2에서 하나 poll 후 offer.
입실 시간이 가장 빠른 퇴실 시간보다 빠르면 그냥 offer.
* 메모리
* KB
* 시간
* ms
*/
class prog_155651 {
	public int solution(String[][] book_time) {
		int answer = 0;

		PriorityQueue<String[]> pq1 = new PriorityQueue<>(new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				// 입실 시간
				StringTokenizer st = new StringTokenizer(o1[0]);
				int sh1 = Integer.parseInt(st.nextToken(":"));
				int sm1 = Integer.parseInt(st.nextToken(":"));

				st = new StringTokenizer(o2[0]);
				int sh2 = Integer.parseInt(st.nextToken(":"));
				int sm2 = Integer.parseInt(st.nextToken(":"));

				// 입실 시간 빠른 순 정렬
				if (sh1 == sh2) {
					return sm1 - sm2;
				} else {
					return sh1 - sh2;
				}
			}
		});

		for (int i = 0; i < book_time.length; i++) {
			pq1.add(book_time[i]);
		}

		while (!pq1.isEmpty()) {
			System.out.println(Arrays.toString(pq1.poll()));
		}

		for (int i = 0; i < book_time.length; i++) {
			pq1.add(book_time[i]);
		}

		PriorityQueue<int[]> pq2 = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					return o1[1] - o2[1];
				}
				return o1[0] - o2[0];
			}
		});

		while (!pq1.isEmpty()) {
			String[] current = pq1.poll();
			int sHour, sMin, eHour, eMin;
			StringTokenizer st = new StringTokenizer(current[0]);
			sHour = Integer.parseInt(st.nextToken(":"));
			sMin = Integer.parseInt(st.nextToken(":"));

			st = new StringTokenizer(current[1]);
			eHour = Integer.parseInt(st.nextToken(":"));
			eMin = Integer.parseInt(st.nextToken(":"));

			eMin = eMin + 10;
			if (eMin >= 60) {
				eHour += 1;
				eMin %= 60;
			}

			if (pq2.isEmpty()) { // 모든 방이 비었을 경우
				pq2.add(new int[] { eHour, eMin });
			} else { // 사용 중인 방이 있으면 비교 필요
				int[] fastest = pq2.peek();
				int fHour = fastest[0];
				int fMin = fastest[1];

				// 가장 빨리 비는 방보다 현재 손님 입실 시간이 더 빠른 지 확인
				if (fHour < sHour || fHour == sHour && fMin <= sMin) {
					pq2.poll(); // 있으면 해당 방에 새로운 손님 배정
				}
				pq2.add(new int[] { eHour, eMin });
			}
			answer = Math.max(answer, pq2.size());
		}
		return answer;
	}
}
