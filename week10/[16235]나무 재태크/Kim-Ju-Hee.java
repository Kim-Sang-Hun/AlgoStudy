package april1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN16235_나무재테크_김주희 {
	static int N, M, K, nutriMap[][], cnt;
	static Square[][] map;
	static int[] dr = new int[] {-1,-1,-1,0,0,1,1,1};
	static int[] dc = new int[] {-1,0,1,-1,1,-1,0,1};
	
	static class Tree implements Comparable<Tree>{
		int age;

		public Tree(int age) {
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
		
	}
	
	static class Square{
		int nutrients, summer;
		PriorityQueue<Tree> trees;
		Queue<Tree> temp;
		
		public Square(int nutrients) {
			this.nutrients = nutrients;
			this.trees = new PriorityQueue<>();
			this.temp = new LinkedList<>();
		}

		@Override
		public String toString() {
			return "Square [nutrients=" + nutrients + ", trees=" + trees + "]";
		}
		
	}
	
	private static void spring() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 나무가 자신의 나이만큼 양분을 먹고, 나이가 증가한다.
				// 하나의 칸에 여러 개의 나무가 있다면 나이가 어린 나무부터 양분을 먹는다.
				// 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 즉시 죽는다.
				
				while(!map[i][j].trees.isEmpty()) {
					Tree cur = map[i][j].trees.poll();
					
					if((map[i][j].nutrients < cur.age)) {// 나무가 양분을 먹을 수 없으면
						map[i][j].summer += cur.age/2;
						cnt--;
					}else {
						map[i][j].nutrients -= cur.age;
						cur.age++;
						map[i][j].temp.add(cur);
					}
					
				}
				
				while(!map[i][j].temp.isEmpty()) {
					map[i][j].trees.add(map[i][j].temp.poll());
				}
			}
		}
	}
	
	private static void summer() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j].nutrients += map[i][j].summer;
				map[i][j].summer = 0;
			}
		}
	}
	
	private static void fall() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 나무의 나이가 5의 배수이면 인접한 칸에 나이가 1인 나무가 생긴다
				while(!map[i][j].trees.isEmpty()) {
					Tree cur = map[i][j].trees.poll();
					
					if(cur.age % 5 == 0) {
						for (int k = 0; k < 8; k++) {
							int nr = i + dr[k];
							int nc = j + dc[k];
							
							if(nr < 1 || nr > N || nc < 1 || nc > N) continue;
							
							map[nr][nc].temp.add(new Tree(1));
							cnt++;
						}
					}
					
					map[i][j].temp.add(cur);
				}

			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				while(!map[i][j].temp.isEmpty()) {
					map[i][j].trees.add(map[i][j].temp.poll());
				}
			}
		}
	}
	
	private static void winter() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j].nutrients += nutriMap[i][j];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		nutriMap = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				nutriMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 양분 초기화
		map = new Square[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = new Square(5);
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			map[x][y].trees.add(new Tree(z));
		}
		
		cnt = M;
		while(K > 0) {
			K--;
			spring();
			summer();
			fall();
			winter();
		}
		
		System.out.println(cnt);

	}

}
