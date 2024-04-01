package week4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int result[], answer;
    static Princess[] p;
	
    static class Princess{
        int r,c,check;
        
        Princess(int r, int c, int check){
            this.r = r;
            this.c = c;
            this.check= check;
            }
    }
    
    private static void combi(int depth, int start){
        if(depth==7){
            int sum = 0;
            for(int i:result){
                sum += p[i].check;
            }
            // 합이 4보다 크면 인접한지 확인
            if (sum >=4) bfs();
           return;
        }
        
        for(int i = start; i<25 ; i++){
            result[depth]=i;
            combi(depth+1,i+1);
        }
    }
    
    private static void bfs(){
        Deque<Princess> q = new ArrayDeque<>();
        boolean visited[] = new boolean[25];
        q.addLast(p[result[0]]);
        visited[result[0]] = true;
        int cnt = 1;
        
        while(!q.isEmpty()){
            Princess now = q.pollFirst();
            
            for(int i : result){
                int nr = p[i].r;
                int nc = p[i].c;
                
                if(Math.abs(nr-now.r) + Math.abs(nc-now.c) == 1 && !visited[i]) {// 인접하면
                    q.addLast(p[i]);
                    visited[i] = true;
                    cnt++;
                }      
            }
        }
        if(cnt == 7) answer++;
        
    }
					

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        p = new Princess[25];
		int tmp = 0;
		for (int i = 0; i < 5; i++) {
			String input = br.readLine();
			for (int j = 0; j < 5; j++) {
				char c = input.charAt(j);
				if (c == 'S') {
                    p[tmp] = new Princess(i,j,1);
				}else{
                    p[tmp] = new Princess(i,j,0);
                }
				
				tmp++;
			}
		}
        
        result = new int[7];
        combi(0,0);

		System.out.println(answer);
		
	}

}
