package BOJ;
import java.io.*;
import java.util.*;

/*
* 제목
* <단속카메라> Lv3
* 링크
* https://school.programmers.co.kr/learn/courses/30/lessons/42884
* 요약
* 
* 풀이
* greedy 
*/
public class prog_42884 {
//	차량의 대수는 1대 이상 10,000대 이하입니다.
//	routes에는 차량의 이동 경로가 포함되어 있으며 routes[i][0]에는 i번째 차량이 고속도로에 진입한 지점, routes[i][1]에는 i번째 차량이 고속도로에서 나간 지점이 적혀 있습니다.
//	차량의 진입/진출 지점에 카메라가 설치되어 있어도 카메라를 만난것으로 간주합니다.
//	차량의 진입 지점, 진출 지점은 -30,000 이상 30,000 이하입니다.
	public int solution(int[][] routes) {
        int flagCount = 0;
    	int numOfCar = routes.length;
    	
    	// 진출 지점 기준으로 정렬
    	Arrays.sort(routes,(o1, o2)->{
    		return o1[1] - o2[1];
    	});
    	
    	// 카메라 진출 지점에 놓기 
    	int currentFlagPos = routes[0][1];
    	flagCount++;
    	
    	for(int i=1; i<numOfCar; i++) { 
    		// 다음 차량 진입~진출 사이에 카메라 위치하면 continue
    		if(routes[i][0] <= currentFlagPos && currentFlagPos <= routes[i][1]) {
    			continue;
    		// 가장 최근에 놓은 카메라 위치 < 다음 차량 진입 위치면 다음 차량 진출 위치에 새 카메라 놓기 
    		} else if(currentFlagPos < routes[i][0]) {
    			currentFlagPos = routes[i][1]; 
    			flagCount++;
    		}
    	}
        return flagCount;
    }
}
