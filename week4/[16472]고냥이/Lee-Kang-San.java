package algo_group_study;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <고냥이> G4
 * 링크
 * https://www.acmicpc.net/problem/16472
 * 요약
 * 주어진 영어 문자열에서 N개 문자로만 이루어진 연속된 부분문자열 중 가장 긴 경우 찾기 (1 <= N <= 26) 
 * 풀이
 * 투 포인터로 탐색
 */
public class boj_16742 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N; // 인식할 수 있는 알파벳 최대 개수 N
	static String str; // 입력받은 스트링
	static int[] letterCnt = new int[26];
	static int max = 0;
    public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		str = br.readLine();
		// 풀이
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}
	private static void solution() {
		int cnt = 0;
		for(int start = 0, end = 0; end < str.length(); end++) {
			if(letterCnt[str.charAt(end)-'a']++==0) {
				cnt++;
			}
			while (N < cnt && start < end) {
                if (--letterCnt[str.charAt(start++) - 'a'] == 0) {
                	cnt--;
                }
            }
			max = Math.max(max,  end-start+1);
		}
		sb.append(max+"");
	}
}
