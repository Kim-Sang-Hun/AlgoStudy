package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN17406 {
	static int N, M, K, arr[][], rotations[][], permutation[], answer;
	static boolean isSelected[];
	
	private static void rotate(int r, int c, int s, boolean flag) {
		for (int i = 0; i < s; i++) { // s개의 circle
			// 머리를 한 방향으로 해서 4개의 deque에 넣음
			Deque<Integer> left = new ArrayDeque<Integer>();
			Deque<Integer> bottom = new ArrayDeque<Integer>();
			Deque<Integer> right = new ArrayDeque<Integer>();
			Deque<Integer> top = new ArrayDeque<Integer>();
	
			for (int j = r - s + i; j < r + s - i; j++) left.addLast(arr[j][c-s+i]);
			for (int j = c - s + i; j < c + s - i; j++) bottom.addLast(arr[r+s-i][j]);
			for (int j = r + s - i; j > r - s + i; j--) right.addLast(arr[j][c+s-i]);
			for (int j = c + s - i; j > c - s + i; j--) top.addLast(arr[r-s+i][j]);

			// 회전 - flag가 true이면 시계방향, false이면 원위치
			if(flag) {
				left.addLast(bottom.pollFirst());
				top.addLast(left.pollFirst());
				right.addLast(top.pollFirst());
				bottom.addLast(right.pollFirst());
			}else {
				bottom.addFirst(left.pollLast());
				left.addFirst(top.pollLast());
				top.addFirst(right.pollLast());
				right.addFirst(bottom.pollLast());
			}
			
			// 배열에 반영
			for (int j = r - s + i; j < r + s - i; j++) arr[j][c-s+i] = left.pollFirst();
			for (int j = c - s + i; j < c + s - i; j++) arr[r+s-i][j] = bottom.pollFirst();
			for (int j = r + s - i; j > r - s + i; j--) arr[j][c+s-i] = right.pollFirst();
			for (int j = c + s - i; j > c - s + i; j--) arr[r-s+i][j] = top.pollFirst();
		}
	}
	
	private static void perm(int depth) {
		if(depth == K) {
			calc();
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			rotate(rotations[i][0], rotations[i][1], rotations[i][2], true); // 배열 돌리기
			perm(depth + 1);
			isSelected[i] = false;
			rotate(rotations[i][0], rotations[i][1], rotations[i][2], false); // 배열 원위치
		}
	}
	
	private static void calc() {
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= M; j++) {
				sum += arr[i][j];
			}
			answer = Math.min(answer, sum);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st1.nextToken());
			}
		}
		
		rotations = new int[K][3];
		for (int i = 0; i < K; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st2.nextToken());
			int c = Integer.parseInt(st2.nextToken());
			int s = Integer.parseInt(st2.nextToken());
			rotations[i] = new int[]{r, c, s};			
		}
		
		isSelected = new boolean[K];
		answer = Integer.MAX_VALUE;

		perm(0);

		System.out.println(answer);
		
	}

}
