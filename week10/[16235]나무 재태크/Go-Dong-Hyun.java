package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16235 {
	
	static int N, M, K;
	static int[][] winter;
	static int[][] arr;
	static int[][] tree;
	static int[][] summer;
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,1,-1,0,1};
	static PriorityQueue[][] treeQ;
//	static PriorityQueue[][] treeFall;
	static int[][] treeFall;
	static Queue<Integer> subQ;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		winter = new int[N][N];
		arr = new int[N][N];
		tree = new int[N][N];
		summer = new int[N][N];
		treeQ = new PriorityQueue[N][N];
//		treeFall = new PriorityQueue[N][N];		
		treeFall = new int[N][N];
		subQ = new ArrayDeque<Integer>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				winter[i][j] = Integer.parseInt(st.nextToken());
				arr[i][j] = 5;
				treeQ[i][j] = new PriorityQueue<Integer>();
//				treeFall[i][j] = new PriorityQueue<Integer>();
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			
			treeQ[x][y].add(z);
		}
		
		for (int i = 0; i < K; i++) {
			step1();
			step2();
			step3();
			step4();
		}
		
		int ans = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ans += treeQ[i][j].size();
			}
		}
		System.out.println(ans);
	}

	private static void step1() {
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (treeQ[i][j].isEmpty()) continue;
				
//				PriorityQueue<Integer> subQ = new PriorityQueue<>();
				
				while (!treeQ[i][j].isEmpty()) {
					int cur = (int) treeQ[i][j].poll();	//나무나이
					if (arr[i][j] < cur) {	//나무죽음
						summer[i][j] += cur/2;
					} else {	//안죽음
						arr[i][j] -= cur;
						cur++;
						subQ.add(cur);
					}
				}
				
				while (!subQ.isEmpty()) {
					treeQ[i][j].add(subQ.poll());
				}
			}
		}
	}

	private static void step2() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] += summer[i][j];
				summer[i][j] = 0;
			}
		}
	}

	private static void step3() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				while (!treeQ[i][j].isEmpty()) {
					int cur = (int) treeQ[i][j].poll();	//나무나이
					subQ.add(cur);
					if (cur % 5 != 0) continue;
					
					for (int k = 0; k < 8; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (!in_range(nx,ny)) continue;
						
//						treeFall[nx][ny].add(1);
						treeFall[nx][ny]++;
					}
					
				}
				while (!subQ.isEmpty()) {
					treeQ[i][j].add(subQ.poll());
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				while (treeFall[i][j] > 0) {
					treeFall[i][j]--;
					treeQ[i][j].add(1);
				}
			}
		}
	}

	private static void step4() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] += winter[i][j];
			}
		}
	}
	
	private static boolean in_range(int a, int b) {
		return 0 <= a && a < N && 0 <= b && b < N;
	}
}