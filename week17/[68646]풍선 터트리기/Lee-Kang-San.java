package programmers;

import java.util.*;

/*
* 제목
* <풍선 터트리기> Lv.3
* 링크
* https://school.programmers.co.kr/learn/courses/30/lessons/68646?language=java
* 요약
n개의 풍선. a[0]~a[n-1]
풍선 선택 시 숫자 큰 풍선 삭제(작은 거 삭제 == 특수 스킬은 1번만 가능)
남을 수 있는 숫자들의 개수 리턴
* 풀이
양 끝(0, n-1) 풍선은 언제나 생존 가능. 양 끝 제외 1개 남을 때까지 터트린 후 마지막 비교하면 되니까. (마지막 비교에서 필요 시 특수 스킬 사용)
1~n-2 풍선에 대해선 좌우 최소값 정보 필요. 기준 풍선 제외 양 옆에서 1개씩 남겼을 때, 둘 다 기준 풍선보다 작으면 특수 스킬 1번 밖에 못쓰니까 절대 남을 수 없는 풍선.
* 메모리
* KB
* 시간
* ms
*/
class prog_68646 {
	public int solution(int[] a) {
		int answer = 2; // 양 끝은 항상 잔존 가능

		int lmin = a[0];

		// 오른쪽 끝에서부터 rmin 값 구하기
		int rminArr[] = new int[a.length];
		rminArr[a.length - 2] = a[a.length - 1];
		for (int i = a.length - 3; i >= 1; i--) {
			rminArr[i] = Math.min(rminArr[i + 1], a[i + 1]);
		}

		for (int i = 1; i < a.length - 1; i++) {
			// 좌, 우 최솟값 중 하나라도 기준값 이상이면 잔존 가능
			if (lmin >= a[i] || rminArr[i] >= a[i]) {
				answer++;
			}

			// 다음 조회 위해 lmin 갱신
			if (lmin > a[i]) {
				lmin = a[i];
			}
		}
		return answer;
	}
}
