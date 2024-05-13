import java.io.*;
import java.util.*;

public class isayaksh {
	
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] toys = Arrays.stream(br.readLine().split(" "))
        		.mapToInt(Integer::parseInt)
        		.toArray();
        
        int answer = Integer.MAX_VALUE;
        Deque<Integer> deque = new ArrayDeque<Integer>();
        
        for(int n = 0; n < N; n++) {
        	
        	if(toys[n] != 1) continue;
        	
        	deque.add(n);
        	if(deque.size() >= K) {
        		answer = Math.min(answer, n - deque.poll() + 1);
        	}
        }
        
        System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
       
    }

}
