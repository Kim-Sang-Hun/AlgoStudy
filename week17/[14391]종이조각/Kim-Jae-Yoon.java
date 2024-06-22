import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, m, answer;
	static int[][] a;
	static boolean[][] vis;
	
    static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	a = new int[n][m];
    	vis = new boolean[n][m];
    	for(int i = 0;i < n; ++i) {
    		String tmp = br.readLine();
    		for(int j = 0;j < m; ++j) {
       			a[i][j] = tmp.charAt(j) - '0';
    		}
    	}
    	answer = 0;
   	}
    
    static void calculate() {
    	int total = 0, lineSum = 0;
    	//가로, 세로 각 방향에 대해서 vis처리된 녀석으로 연속되는지 파악하여 결과 합산을 해준다.
    	for(int i = 0;i < n; ++i) {
    		lineSum = 0;
    		for(int j = 0;j < m; ++j) {
    			if(vis[i][j]) lineSum = lineSum * 10 + a[i][j];
    			else {
    				total += lineSum;
    				lineSum = 0;
    			}
    		}
    		total += lineSum;
    	}
    	for(int i = 0;i < m; ++i) {
    		lineSum = 0;
    		for(int j = 0;j < n; ++j) {
    			if(!vis[j][i]) lineSum = lineSum * 10 + a[j][i];
    			else {
    				total += lineSum;
    				lineSum = 0;
    			}
    		}
    		total += lineSum;
    	}
    	answer = Math.max(answer, total);
    }
    
    static void dfs(int x, int y) {
    	//다 뒤져봤을 때
    	if(x == n) {
    		calculate();
    		return;
    	}
    	if(y == m) {
    		dfs(x + 1, 0);
    		return;
    	}
    	//vis을 false랑 true만 사용해도 되는 점은, x==n과 y==m여부로 초과 상태를 발생시키거나 중복 상태를 발생시키지 않기 때문이다.
    	//아래 혹은 오른쪽으로만 진행하기에 가능한 이야기
    	//col 사용 시 true
    	vis[x][y] = true;
    	dfs(x, y + 1);
    	//row 사용 시 false
    	vis[x][y] = false;
    	dfs(x, y + 1);
    }

    static void solution() {
    	dfs(0, 0);
    	System.out.println(answer);
    }
    
    public static void main(String[] args) throws IOException {
   		input();
   		solution();
    }
}
