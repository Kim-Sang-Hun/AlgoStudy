import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int n, size = (int)1e9;
	static List<Integer>[] friends;
	static List<Integer> answer = new ArrayList<>();
    
	//플로이드 워셜이 정석이겠지만,n <= 50이므로 n * O(n + m)으로 풀어도 되니까 기본 탐색 기법을 사용
    static void input() throws IOException {
    	n = Integer.parseInt(br.readLine());
    	friends = new ArrayList[n + 1];
    	for(int i = 1;i <= n; ++i) {
    		friends[i] = new ArrayList<>();
    	}
    	while(true) {
    		st = new StringTokenizer(br.readLine());
    		int you = Integer.parseInt(st.nextToken());
    		int me = Integer.parseInt(st.nextToken());
    		if(you == -1) break;
    		friends[you].add(me);
    		friends[me].add(you);
    	}
    }
    
    static void search(int start) {
    	Deque<Integer> q = new ArrayDeque<>();
    	int[] vis = new int[n + 1];
    	Arrays.fill(vis, (int)1e9);
    	q.add(start);
    	vis[start] = 1;
    	int max = 1;
    	while(!q.isEmpty()) {
    		int cur = q.poll();
    		for(int nxt : friends[cur]) {
    			if(vis[nxt] <= vis[cur] + 1) continue;
    			vis[nxt] = vis[cur] + 1;
    			max = Math.max(max, vis[nxt]);
    			q.add(nxt);
    		}
    	}
    	--max;
    	if(size > max) {
    		size = max;
    		answer.clear();
    	} if(size == max) {
    		answer.add(start);
    	}
    }
    
    static void solution() {
    	for(int i = 1; i <= n; ++i) {
    		search(i);
    	}
    	sb.append(size).append(" ").append(answer.size()).append('\n');
    	Collections.sort(answer);
    	for(int i : answer) {
    		sb.append(i).append(' ');
    	}
    	sb.trimToSize();
    	System.out.println(sb);
    }
    
    public static void main(String[] args) throws IOException {
   		input();
   		solution();
    }
}
