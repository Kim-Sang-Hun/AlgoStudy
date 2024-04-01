package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN9465_스티커_김주희 {
	static int N, stickers[][], answer[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			// 입력
			N = Integer.parseInt(br.readLine());
			stickers = new int[2][N];
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					stickers[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(N == 1) {
				System.out.println(Math.max(stickers[0][N-1], stickers[1][N-1]));
				continue;
			}
			
			stickers[0][1] += stickers[1][0];
			stickers[1][1] += stickers[0][0];
			
			for (int i = 2; i < N; i++) {
				stickers[0][i] += Math.max(stickers[1][i-1], stickers[1][i-2]); // stickers[0][i-2]는 stickers[1][i-2]에 더해지는 게 무조건 크니까 고려 안 함
				stickers[1][i] += Math.max(stickers[0][i-1], stickers[0][i-2]); // 여기서 stickers[1][i-2]도 마찬가지
			}
			
			System.out.println(Math.max(stickers[0][N-1], stickers[1][N-1]));
		
		}
	}
}
