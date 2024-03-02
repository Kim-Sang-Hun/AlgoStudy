package Algo_week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ19237 {
	
	static int N,M,K,time;
	static int[][][] arr;
	static int[] sharkDir;
	static int[][][] sharkRank;
	static int ans;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws Exception {
		
		//ㅅㅂ 로직 맞는데 왜틀린지 모르겠음 
		//일단 제출할게요... 죄송..
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N][3];	//좌표x,y랑 rank
		sharkDir = new int[M+1];	//상어 현재 보고있는 방향
		sharkRank = new int[M+1][4][4];	//상어번호, 상하좌우일때, 가는방향
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j][0] = Integer.parseInt(st.nextToken());	//0에는 좌표x,y에다가 rank(번호)저장
				arr[i][j][1] = K;									//1에는 좌표랑 냄새시간저장
				arr[i][j][2] = 0;									//2에는 시간저장 ㅅㅂ
				if (arr[i][j][0] != 0) {
					q.add(new int[] {i,j,arr[i][j][0]});	//좌표x,y랑 rank q에 넣음
				}
			}
		}
		
		int[][] arrays = q.toArray(new int[q.size()][]);
		
		Arrays.sort(arrays, (a, b) -> {
            return a[2] - b[2]; 
        });

        q.clear();
        for (int[] item : arrays) {
            q.offer(item);
        }
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			sharkDir[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		for (int i = 1; i <= M; i++) {	//M번째 상어
			for (int j = 0; j < 4; j++) {	//상하좌우일때
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {	//가는방향
					sharkRank[i][j][k] = Integer.parseInt(st.nextToken())-1;
				}
			}
		}
		
		
		time = 0;
		
		while (q.size() != 1) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int rank = arr[x][y][0];
			int curTime = arr[x][y][2];
			time = curTime+1;
			
			
			if (time == 1001) {
				System.out.println(-1);
				return;
			}
			
			int dir = sharkDir[rank];
			
			for (int i = 0; i < sharkRank[rank][dir].length; i++) {
				int idx = sharkRank[rank][dir][i];
				int nx = x + dx[idx];
				int ny = y + dy[idx];
				
				if (!in_range(nx,ny)) continue;
				
				if (arr[nx][ny][0] == 0 || arr[nx][ny][0] > arr[x][y][0] || arr[nx][ny][0] == arr[x][y][0]) {	//아무상어도 없거나, 나보다 랭크 낮거나, 나랑 같거나
					arr[nx][ny][0] = rank;
					arr[nx][ny][1] = K;
					arr[nx][ny][2] = arr[x][y][2] + 1;
					sharkDir[rank] = idx;
					q.add(new int[] {nx,ny,rank});
					break;
				} 
				
			}
			
		}
		
		System.out.println(time);
	}


	private static boolean in_range(int a, int b) {
		return 0 <= a && a < N && 0 <= b && b < N;
	}
}