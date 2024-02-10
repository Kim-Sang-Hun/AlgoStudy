import java.io.*;
import java.util.*;
/*
 * 제목
 * <창용 마을 무리 개수>
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWngfZVa9XwDFAQU&
 * 요약
 * N명. 서로 아는 관계이거나 몇 사람을 거쳐서 알 수 있는 관계라면 같은 무리일 때 몇 개의 무리인지?
 * 풀이
 * disjoin set
 */
public class swea_7465 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int T;
	static int N, M;
	static int temp1, temp2;
	static int unifi[];
	static Queue<int[]> q = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());		// 테스트 케이스 개수
		for(int i=1; i<=T; i++) {
		// 입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 사람 수
			M = Integer.parseInt(st.nextToken());	// 관계 수
			unifi = new int[N+1];	// i는 [i] 번 집합 소속		
			for(int j=0; j<M; j++) {	// 관계 입력 temp1 <-> temp2
				st = new StringTokenizer(br.readLine());
				temp1 = Integer.parseInt(st.nextToken());
				try {
					temp2 = Integer.parseInt(st.nextToken());
				} catch(NoSuchElementException e) {
					temp2 = temp1;
				}
				if(temp1 > temp2) q.offer(new int[]{temp2, temp1}); // 작은 수가 먼저 나오도록 큐에 관계 삽입
				else q.offer(new int[]{temp1, temp2}); 	
			}	
			for(int k=1; k<=N; k++) unifi[k]=k;		// 초기 소속 상태 (N개 무리)
		// 풀이
			int cnt=solution();
		// 출력
			sb.append("#"+i+" "+cnt+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	private static int solution() {
		int cnt; // 무리 개수
		while(!q.isEmpty()) {
			int[] rel = q.poll();
			int a = rel[0];
			int b = rel[1];
			union(a, b);
		}
		for(int i=1; i<=N; i++) 
			unifi[i] = find(i);
		Set<Integer> s = new HashSet<>();
		for(int i=1; i<=N; i++) s.add(unifi[i]);
		return s.size();
	}
	private static void union(int a, int b) {
		a = find(a);	// a의 루트
		b = find(b);	// b의 루트
		if(a==b)	return;	// 이미 같은 루트면 아무 작업x 
		unifi[b] = a;
	}
	private static int find(int x) {
		if(unifi[x]==x) return x;
		return find(unifi[x]);
	}	
}
