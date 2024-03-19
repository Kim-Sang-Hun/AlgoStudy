import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int[] teleport;
    static boolean[] vis;
    static class Info{
    	int cnt, num;
    	Info(int num, int cnt){
    		this.num = num;
    		this.cnt = cnt;
    	}
    }

    //배열 굳이 2차원으로 할 필요없이 1차원에서 가능하므로 간단하게 처리
    static void input() throws IOException{
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	teleport = new int[102];
    	vis = new boolean[102];
    	for(int i = 0;i < n; ++i) {
        	st = new StringTokenizer(br.readLine());
       		int from = Integer.parseInt(st.nextToken());
       		int to = Integer.parseInt(st.nextToken());
       		teleport[from] = to;
    	}
    	for(int i = 0;i < m; ++i) {
        	st = new StringTokenizer(br.readLine());
       		int from = Integer.parseInt(st.nextToken());
       		int to = Integer.parseInt(st.nextToken());
       		teleport[from] = to;
    	}
    }

    static void solution(){
    	Deque<Info> q = new ArrayDeque<>();
    	q.add(new Info(1, 0));
    	while(!q.isEmpty()) {
    		Info cur = q.poll();
    		if(cur.num == 100) {
    			System.out.println(cur.cnt);
    			return;
    		}
    		for(int i = 1;i <= 6; ++i) {
    			int nxt = cur.num + i;
    			if(nxt < 1 || nxt > 100) continue;
    			if(vis[nxt]) continue;
    			vis[nxt] = true;
                if(teleport[nxt] != 0){
                    nxt = teleport[nxt];
                    vis[nxt] = true;
                }
    			q.add(new Info(nxt, cur.cnt + 1));
    		}
    	}
    }

    public static void main(String[] args) throws IOException{
        input();
        solution();
    }
}
