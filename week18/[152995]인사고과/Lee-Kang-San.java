package programmers;

import java.util.*;

/*
* 제목
* <인사고과> Lv.3
* 링크
* https://school.programmers.co.kr/learn/courses/30/lessons/152995
* 요약
인센티브 받는 사람들 중 완호의 등수 구하기
* 풀이
- 점수합으로 정렬
- 인센티브 못받는 놈들 필터링
	- 완호보다 낮은 등수(점수합 낮은 사원들) 고려할 필요 없음
	- 인센티브 수령 대상이라 하더라도 완호보다 낮은 등수일 것이기 때문
- 필터링 끝난 리스트에서 완호 찾기
* 메모리
* KB
* 시간
* ms
*/
class prog_152995 {
	public int solution(int[][] scores) {
        int answer = -1;
        int wanhoA = scores[0][0];
        int wanhoB = scores[0][1];
        
        ArrayList<int[]> list = new ArrayList<>(); // 돈 못 받는 놈들 걸러낸 리스트
        
        Arrays.sort(scores, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int n1 = o1[0] + o1[1];
				int n2 = o2[0] + o2[1];
				return n1 - n2;
			}
		});
        
        for(int i=0; i<scores.length; i++) {
            int a = scores[i][0];
            int b = scores[i][1];
            int sum = a+b;
            
            // 완호보다 점수 합 낮은 애들은 고려할 필요 없음
            if(sum < wanhoA + wanhoB) {
                continue;
            }
            
            boolean checked = true;
            for(int j=scores.length-1; j>=0 ;j--) {
                int aa = scores[j][0];
                int bb = scores[j][1];
                int sumsum = aa+bb;
                if(a < aa && b < bb) {
                    checked = false;
                    break;
                }
                if(sum >= sumsum) {
                    break;
                }
            }
            if(checked) {
                list.add(new int[]{a, b});
            }
        }
        
        Collections.sort(list, new Comparator<int[]>() {
            @Override
			public int compare(int[] o1, int[] o2) {
				int n1 = o1[0] + o1[1];
				int n2 = o2[0] + o2[1];
				return n2-n1;
			}
        });
        
        for(int i=0; i<list.size(); i++) {
            if(list.get(i)[0] == wanhoA && list.get(i)[1] == wanhoB) {
                answer = i+1;
                break;
            }
        }
        
        return answer;
    }
}
