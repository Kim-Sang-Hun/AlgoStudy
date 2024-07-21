package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
<N과 M (12)> S2
* 링크
https://www.acmicpc.net/problem/15666
* 요약
주어진 N개의 숫자들 중 M개의 숫자를 중복 있 게 선택할 때 비내림차순이 되는 모든 경우 출력하기
* 풀이
Set으로 중복 제거, 정렬, 중복선택(DFS)
* 메모리
14536 KB
* 시간
152 ms
*/

public class boj_15666 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int N, M; // 1 <= M <= N <= 8
    static Set<Integer> set; // N개의 숫자에서 중복 제거용
    static Integer[] arr; // N개 중 중복 제거된 숫자들 (값 1~10000)
    static int[] ans;
    static int length; // 중복 제거하고 남은 숫자 개수
    
    public static void main(String[] args) throws IOException {
    	// 입력
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	set = new HashSet<>();
    	st = new StringTokenizer(br.readLine());
    	for(int i=0; i<N; i++) {
    		int num = Integer.parseInt(st.nextToken()); 
    		set.add(num); // set에 넣어 중복 제거
    	}
    	
    	arr = set.toArray(new Integer[0]);

    	// 풀이
    	Arrays.sort(arr);    
    	length = arr.length; // 중복 제거하고 남은 숫자 개수
    	ans = new int[M];
    	dfs(0, 0);
    	
    	// 출력
    	bw.write(sb.toString());
    	bw.flush();
    	
    }

	private static void dfs(int start, int depth) {
		if(depth == M) {	
			for(int i=0; i<M; i++) {
				sb.append(ans[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		for(int i=start; i<length; i++) {
			ans[depth] = arr[i];
			dfs(i, depth+1);
		}
	}
}
