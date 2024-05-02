import java.util.*;

class isayaksh {
    public int solution(int[][] routes) {
        
        int answer = 0;
        int start = 30000;
        int end = -30000;
        int N = routes.length;
        
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] c1, int[] c2) {
                if(c1[0] != c2[0]) return c1[0] - c2[0];
                return c1[1] - c2[1];
            }
        });
        
        for(int n = 0; n < N; n++) {
            
            if(start <= routes[n][0] && routes[n][0] <= end) {
                if(start < routes[n][0]) start = routes[n][0];
                if(routes[n][1] < end) end = routes[n][1];
            }
            
            if(end < routes[n][0]) {
                answer++;
                start = routes[n][0];
                end = routes[n][1];
            }
            
        }
        return answer;
    }
}
