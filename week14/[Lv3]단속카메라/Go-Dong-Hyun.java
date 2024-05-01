package week14;

import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        
        Arrays.sort(routes, (o1,o2) ->
                   o1[1] == o2[1] ? o1[0]-o2[0] : o1[1]-o2[1]);
            
        // for (int i = 0; i < routes.length; ++i) {
        //     System.out.println(Arrays.toString(routes[i]));
        // }
        
        int now_idx = routes[0][1];
        
        for (int i = 1; i < routes.length; ++i) {
            if (now_idx >= routes[i][0]) continue;
            else {
                now_idx = routes[i][1];
                answer++;
            }
        }
        
        
        return answer;
    }
}