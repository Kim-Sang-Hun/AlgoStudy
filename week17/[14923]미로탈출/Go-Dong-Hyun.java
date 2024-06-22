package Algo_week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14923 {
	
	static class Node{
		int x;
		int y;
		int breakWall;
		int distance;
		public Node(int x, int y, int breakWall, int distance) {
			super();
			this.x = x;
			this.y = y;
			this.breakWall = breakWall;
			this.distance = distance;
		}
	}
	
	static int N,M;
	static int startX,startY,endX,endY;
	static int[][] arr;
	static boolean[][][] visited;
	static Queue<Node> q;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken())-1;
		startY = Integer.parseInt(st.nextToken())-1;
		st = new StringTokenizer(br.readLine());
		endX = Integer.parseInt(st.nextToken())-1;
		endY = Integer.parseInt(st.nextToken())-1;
		
		arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < line.length; j++) {
				arr[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		int result = bfs();
		System.out.println(result);
		
	}


	private static int bfs() {
		
		q = new ArrayDeque<>();
		visited = new boolean[N][M][2];
		q.add(new Node(startX, startY, 0, 0));
		visited[startX][startY][0] = true;
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.x == endX && node.y == endY) {
				return node.distance;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if (!in_range(nx,ny)) continue;
				
				if (arr[nx][ny] == 0 && !visited[nx][ny][node.breakWall]) {
					visited[nx][ny][node.breakWall] = true;
					q.add(new Node(nx, ny, node.breakWall, node.distance+1));
				}
				
				if (arr[nx][ny] == 1 && node.breakWall == 0 && !visited[nx][ny][1]) {
					visited[nx][ny][1] = true;
					q.add(new Node(nx, ny, 1, node.distance+1));
				}
			}
			
		}
		
		
		return -1;
	}


	private static boolean in_range(int a, int b) {
		return 0 <= a && a < N && 0 <= b && b < M;
	}
}