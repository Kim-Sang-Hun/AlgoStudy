import java.util.*;
import java.io.*;
/*
  Title: 단속카메라
  Tier: Lv3
  Algorithm: Greedy
  Constraint: Undefined
*/
class Solution {
    public int solution(int[][] routes) {
        int answer = 0, cur = -30001;
        Arrays.sort(routes, new Comparator<>(){
            @Override
            public int compare(int[] a, int[] b){
                return a[1] - b[1];
            }
        });
        for(int[] route : routes){
            if(cur < route[0]){
                cur = route[1];
                ++answer;
            }
        }
        return answer;
    }
}
