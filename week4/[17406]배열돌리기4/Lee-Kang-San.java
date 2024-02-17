package algo_group_study;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <배열 돌리기 4> G4
 * 링크
 * https://www.acmicpc.net/problem/17406
 * 요약
 * 배열 연산 구현
 * 풀이
 * 순열로 모든 경우 탐색 후 최소값 출력
 */
public class boj_17406 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M, K; // N*M 배열, 연산횟수 K
    static int[][] arr; 
    static int[][] rcs; // 연산
    static boolean isVisit[];
    static int minArrVal = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
		// 입력
        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N+1][M+1];
        rcs = new int[K][3];    //[0] r, [1] c, [2] s
        isVisit = new boolean[K];
        for(int i=1; i<=N; i++) {    // 배열 입력
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<K; i++) {    // K개 연산 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                rcs[i][j] = Integer.parseInt(st.nextToken());
            }
        }
		// 풀이
        perm(arr, 0);
        // 출력
        sb.append(minArrVal+"");
		bw.write(sb.toString());
		bw.flush();
	}
    
	private static void perm(int[][] curArr, int depth) {
		if(depth == K) {
			minArrVal = Math.min(minArrVal, getValueOfArr(curArr));
		}
		for(int i=0; i<K; i++) {
			if(isVisit[i]) continue;	// 이미 수행한 연산이면 continue
			// 가지치기 필요
			isVisit[i] = true;
			int[][] nextArr = rotateArr(curArr, rcs[i][0], rcs[i][1], rcs[i][2]);
			perm(nextArr, depth+1);
			isVisit[i] = false;
		}
	}

	private static int[][] rotateArr(int[][] curArr, int r, int c, int s) {
		// 연산 위한 복사본 생성
		int[][] nextArr = new int[N+1][M+1];	
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				nextArr[i][j] = curArr[i][j];
			}
		}
		// rcs에 따라 nextArr 회전
		for(int k=1; k<=s; k++) {	// 중심(r,c) 기준으로 s줄 회전
			int temp = curArr[r-k][c-k];
			// 왼쫄줄 up
			for(int i=r-k+1; i<=r+k; i++) {
				nextArr[i-1][c-k] = curArr[i][c-k];
			}
			// 밑줄 <<
			for(int j=c-k+1; j<=c+k; j++) {
				nextArr[r+k][j-1] = curArr[r+k][j];
			}
			// 오른쪽줄 down
			for(int i=r+k-1; i>=r-k; i--) {
				nextArr[i+1][c+k] = curArr[i][c+k];
			}
			// 윗줄 >>
			for(int j=c+k-1; j>=c-k; j--) {
				nextArr[r-k][j+1] = curArr[r-k][j];
			}
			nextArr[r-k][c-k+1] = temp;
		}
		return nextArr;
	}

	private static int getValueOfArr(int[][] arr) {
		int sum;
	    int arrVal = Integer.MAX_VALUE;
		for(int i=1; i<=N; i++) {
			sum = 0;
			for(int j=1; j<=M; j++) sum += arr[i][j];
			arrVal = Math.min(arrVal, sum);
		}
		return arrVal;
	}


	
}
