package SWEA.D3;

import java.io.*;
import java.util.*;

public class swea_5215 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();   
    static int T;									    // 테스트 케이스 수
    static int N, L;									// 재료 수, 제한 칼로리
    static int[][] ingredients;							// N개 재료, [0] 맛, [1] 칼로리
    static int bestBurgerTaste;
    public static void main (String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());    
    solution();
    bw.write(sb.toString());
    bw.flush();
    }
    
	private static void solution() throws IOException{
		for(int i=1; i<=T; i++) {
			st = new StringTokenizer(br.readLine());  
			N = Integer.parseInt(st.nextToken());   
			L = Integer.parseInt(st.nextToken());   
			ingredients = new int[N][2];
			for(int j=0; j<N; j++) {	// 재료 정보 입력
				st = new StringTokenizer(br.readLine());
				ingredients[j][0] = Integer.parseInt(st.nextToken());
				ingredients[j][1] = Integer.parseInt(st.nextToken());
			}
			getBurgers();								// 조합 찾기
			sb.append("#"+i+" "+bestBurgerTaste+"\n");
		}
	}

	private static void getBurgers() {
		bestBurgerTaste = -1;
		int cnt = 1<<N;	// subset 개수
		int tempTaste;
		int tempKcal;
		for(int i=0; i<cnt; ++i) {
			tempTaste = 0;
			tempKcal = 0;
			for(int pos = 0; pos < N; pos++) {			
				if((i & 1<<pos) != 0) {
					tempTaste += ingredients[pos][0];
					tempKcal += ingredients[pos][1];			
				}
			}
			if(tempKcal <= L) {
				bestBurgerTaste = Math.max(bestBurgerTaste, tempTaste);
			}
		}
	}
}
