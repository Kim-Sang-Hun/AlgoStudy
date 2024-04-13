import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1번은 1분, 2번은 2분, m번은 m분에 움직이기 시작
 * 각자 목표 편의점 다름
 * 
 * 사람은 1분에 3가지 행동
 * 1. 최단거리로 움직임. 위 왼 오 아래 우선순위로 움직임.
 * 이때 최단거리는 도달까지 거치는 칸의 수가 최소가 되는 거리
 * 2. 편의점에 도착한다면 해당 편의점에서 멈추고, 해당 편의점 칸은 지나갈 수 없음.
 * 단, 사람들이 모두 이동한 후 해당 칸을 지나갈 수 없게 됨.
 * 3. 자신이 가고싶은 편의점과 가장 가까이에 있는 베이스캠프로 들어감.
 * 베이스캠프가 여러가지라면 그 중 행이 작은, 행이 같으면 열이 작은 베이스 캠프로 들어감.
 * 이때 이동시간은 소요되지 않음.
 * 이때부터 다른 사람들은 해당 베이스캠프가 있는 칸을 지나갈 수 없음. 마찬가지로 모두 이동한 후부터.
 * */
public class Main {
	static int N, M, map[][], time, cnt;
	static Person[] persons;
	static boolean cannotGo[][];
	static int dr[] = new int[]{-1,0,0,1};
	static int dc[] = new int[]{0,-1,1,0};
	static Queue<int[]> nextBlock = new LinkedList<>();
	static List<int[]> baseCamps = new ArrayList<>();
	static PriorityQueue<Square> pq = new PriorityQueue<>();
	
	
	static class Square implements Comparable<Square>{
		int r, c, dist;

		public Square(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}

		@Override
		public int compareTo(Square o) {
			if(this.dist == o.dist && this.r == o.r) return this.c - o.c;
			else if(this.dist == o.dist) return this.r - o.r;
			return this.dist - o.dist;
		}
		
		
		
		
	}
	
	static class Person{
		int r, c, targetR, targetC;
		boolean arrived;

		public Person(int targetR, int targetC) {
			this.targetR = targetR;
			this.targetC = targetC;
		}

		@Override
		public String toString() {
			return "Person [r=" + r + ", c=" + c + ", targetR=" + targetR + ", targetC=" + targetC + "]";
		}
		

	}
	
	
	
	private static int chooseDir(int startR, int startC, int targetR, int targetC) {
		Queue<int[]> q = new LinkedList<>();
		boolean visited[][] = new boolean[N+1][N+1];
		
		visited[startR][startC] = true;
		// 처음 방향 저장하기 위해
		for (int i = 0; i < 4; i++) {
			int nr = startR + dr[i];
			int nc = startC + dc[i];
			
			if(nr < 1 || nr > N || nc < 1 || nc > N || cannotGo[nr][nc] || visited[nr][nc] ) continue;
			
			visited[nr][nc] = true;
			q.add(new int[] {nr, nc, i});
		}
		
		int answer = -1;
		// bfs 탐색
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			if(cur[0] == targetR && cur[1] == targetC) {
				answer = cur[2];
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if(nr < 1 || nr > N || nc < 1 || nc > N || cannotGo[nr][nc] || visited[nr][nc] ) continue;
				
				visited[nr][nc] = true;
				q.add(new int[] {nr, nc, cur[2]});
			}
		}
		
		return answer;
	}
	
	
	
	private static void movetoC() {
		for (int i = 1; i <= M; i++) {
			 if(persons[i].r == 0 || persons[i].c == 0 || persons[i].arrived) continue; // 아직 진입 안 했거나 도착했다면
			 
			 int dir = chooseDir(persons[i].r, persons[i].c, persons[i].targetR, persons[i].targetC);
			 
			 if(dir != -1) {
				 persons[i].r += dr[dir];
				 persons[i].c += dc[dir];
			 }
			 
		}
		
	}
	
	private static void arriveC() {
		for (int i = 1; i <= M; i++) {
			if(persons[i].arrived) continue;
			if(persons[i].r == persons[i].targetR && persons[i].c == persons[i].targetC) {
				cnt--; // 사람 수 체크
				persons[i].arrived = true;
				
				nextBlock.add(new int[] {persons[i].targetR, persons[i].targetC}); // 다음번에 막을 좌표 저장
			}
		}
	}
	
	private static Square bfs(int startR, int startC, int targetR, int targetC) {
		Queue<Square> q = new LinkedList<>();
		boolean visited[][] = new boolean[N+1][N+1];
		
		q.add(new Square(startR,startC,0));
		visited[startR][startC] = true;
		
		Square answer = null;
		// dfs 탐색
		while(!q.isEmpty()) {
			Square cur = q.poll();
			
			if(cur.r == targetR && cur.c == targetC) {
				answer = new Square(targetR, targetC, cur.dist); // 최단거리 저장
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(nr < 1 || nr > N || nc < 1 || nc > N || cannotGo[nr][nc] || visited[nr][nc] ) continue;
				
				visited[nr][nc] = true;
				q.add(new Square(nr, nc, cur.dist+1));
			}
		}
		
		return answer;
		
		
		
		
//		Queue<int[]> q = new LinkedList<>();
//		boolean visited[][] = new boolean[N+1][N+1];
//		
//		q.add(new int[] {startR,startC,0});
//		visited[startR][startC] = true;
//		
//		int answer = 0;
//		// dfs 탐색
//		while(!q.isEmpty()) {
//			int[] cur = q.poll();
//			
//			if(cur[0] == targetR && cur[1] == targetC) {
//				answer = cur[2]; // 최단거리 저장
//				break;
//			}
//			
//			for (int i = 0; i < 4; i++) {
//				int nr = cur[0] + dr[i];
//				int nc = cur[1] + dc[i];
//				
//				if(nr < 1 || nr > N || nc < 1 || nc > N || cannotGo[nr][nc] || visited[nr][nc] ) continue;
//				
//				visited[nr][nc] = true;
//				q.add(new int[] {nr, nc, cur[2]+1});
//			}
//		}
//		
//		return answer;
		
	}
	
	private static void movetoB() {
		for (int i = 1; i <= M; i++) {
			if(time > M) continue;
			if(time != i) continue;
			if(persons[i].arrived) continue;
			
			int tempMin = Integer.MAX_VALUE;
			
			pq.clear();
//			int[] dists = new int[baseCamps.size()];
//			
//			for (int j = 0; j < baseCamps.size(); j++) {
//				dists[j] = Integer.MAX_VALUE;
//			}
//			
			
			for (int j = 0; j < baseCamps.size(); j++) {
				if(cannotGo[baseCamps.get(j)[0]][baseCamps.get(j)[1]]) continue;

				//dists[j] = bfs(persons[i].targetR, persons[i].targetC, baseCamps.get(j)[0], baseCamps.get(j)[1]);
				pq.add(bfs(persons[i].targetR, persons[i].targetC, baseCamps.get(j)[0], baseCamps.get(j)[1]));
				//if(tempMin > dists[j]) tempMin = dists[j];
			}
			
			
//			int answer = -1; // 목표하는 베이스캠프
//			for (int j = 0; j < baseCamps.size(); j++) {
//				if(tempMin == dists[j]) {
//					answer = j;
//					break;
//				}
//			}
			
			
			
//			persons[i].r = baseCamps.get(answer)[0];
//			persons[i].c = baseCamps.get(answer)[1]; // 베이스캠프로 이동
			
			Square answer = pq.poll();
			persons[i].r = answer.r;
			persons[i].c = answer.c;
			
			//nextBlock.add(new int[] {baseCamps.get(answer)[0], baseCamps.get(answer)[1]}); // 다음번에 막을 좌표 저장
			nextBlock.add(new int[] {answer.r, answer.c}); // 다음번에 막을 좌표 저장
		}
		
	}
	
	private static void block() {
		while(!nextBlock.isEmpty()) {
			int[] cur = nextBlock.poll();
			
			cannotGo[cur[0]][cur[1]] = true;

		}
	}

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	cnt = M;
    	map = new int[N+1][N+1];
    	cannotGo = new boolean[N+1][N+1];
    	for (int i = 1; i <= N; i++) {
    		st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) {
					baseCamps.add(new int[] {i,j});
				}
			}
		}
    	
    	
//    	for (int i = 0; i < map.length; i++) {
//    		System.out.println(Arrays.toString(map[i]));
//    	}
    	
    	persons = new Person[M+1];
    	for (int i = 1; i <= M; i++) {
    		st = new StringTokenizer(br.readLine());
			persons[i] = new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
    	
//    	for (int i = 1; i <= M; i++) {
//    		System.out.println(persons[i]);
//    	}
//    	

    	time = 1;
    	while(true) {
//    		System.out.println("============================");
//    		System.out.println(time);
//    		System.out.println(cnt);
//    		System.out.println("============================");
    		
    		
    		// 사람들 모두 본인이 가고싶은 편의점 방향으로 1칸 움직인다.
    		movetoC();
    		
    		// 만약 해당 편의점에 도착한다면 해당 편의점에 멈추게 되고, 다른 사람들은 모두 이동한 뒤부터 이 칸을 지나갈 수 없다.
    		arriveC();
    		
    		// 3번
    		movetoB();
    		
    		// 막아야하는 곳 막기
    		block();
    		

    		
//	        for (int i = 1; i <= M; i++) {
//	    		System.out.println(persons[i]);
//	    	}
//	        System.out.println();
//	        
//	    	for (int i = 1; i <= N; i++) {
//	    		System.out.println(Arrays.toString(cannotGo[i]));
//	    	}
//	    	System.out.println();
    		
    		if(cnt <= 0) break;
    		time++;
    	}
    	
    	System.out.println(time);
    }

}
