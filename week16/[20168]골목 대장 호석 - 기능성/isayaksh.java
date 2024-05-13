import java.io.*;
import java.util.*;

public class isayaksh {
	
	private static int N, M, A, B, C;
	private static int[] weight;
	private static int[][] board;
	
	
	private static int answer = Integer.MAX_VALUE;
	
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        board = new int[N+1][N+1];
        
        int i1, i2, a;
        for(int m = 0; m < M; m++) {
        	st = new StringTokenizer(br.readLine());
        	i1 = Integer.parseInt(st.nextToken());
        	i2 = Integer.parseInt(st.nextToken());
        	a = Integer.parseInt(st.nextToken());
        	board[i1][i2] = a;
        	board[i2][i1] = a;
        }
        
        weight = new int[N+1];
        Arrays.fill(weight, Integer.MAX_VALUE);
        weight[A] = 0;
        
        dfs(A, C, 0);
       
        System.out.println(weight[B] != Integer.MAX_VALUE ? weight[B] : -1);
    }
    
    private static void dfs(int a, int C, int max) {
    	
    	if(a == B) return;
    	
    	for(int n = 1; n < N+1; n++) {
    		
    		// 1. 연결되어 있지 않은 경우
    		if(board[a][n] == 0) continue;
    		
    		int w = Math.max(max, board[a][n]);
    		
    		// 2. 최대 요금
    		if(weight[n] <= w) continue;
    		
    		// 3. 비용 부족
    		if(C < board[a][n]) continue;
    		
    		weight[n] = w;
    		
    		dfs(n, C - board[a][n], w);
    		
    	}
    	
    }

}
