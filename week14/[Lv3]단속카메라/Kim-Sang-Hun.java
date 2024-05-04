import java.util.*;
/*
끝나는 지점으로 오름차순 정렬한 다음
시작지점이 전에 카메라를 설치한 지점보다 뒤에 있다면
해당 차량의 끝나는 지점에 카메라를 설치한다.
++cnt를 하면 시초가 나지 않는데
cnt++를 하면 시초가 난다
그정도 차이인가...?
*/
class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(o -> o[1]));
        int cnt = 0;
        int pos = -30001;
        for (int[] cur : routes) {
            if (pos < cur[0]) {
                ++cnt;
                pos = cur[1];
            }
        }
        return cnt;
    }
}
