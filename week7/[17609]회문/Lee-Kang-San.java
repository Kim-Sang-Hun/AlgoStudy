package algo_group_study;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <회문> G5
 * 링크 
 * https://www.acmicpc.net/problem/17609
 * 요약
 * 입력받은 문자열이 팰린드롬이거나 문자 하나 제거해서 팰린드롬이 될 수 있는 지 판단
 * 풀이
 * 구현
 */
public class boj_17609 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine()); 
    	for(int tc=0; tc<T; tc++) {
    		// 입력
    		String str = br.readLine();
    		// 풀이
    		int diffCnt = isPalindrome(str);
    		// 출력
    		sb.append(diffCnt+"\n");
    	}
		bw.write(sb.toString());
		bw.flush();
	}
	private static int isPalindrome(String str) {
		int p=0, q=str.length()-1;
		while(p<q) {
			if(str.charAt(p) == str.charAt(q)) {
				p++;
				q--;
				continue;
			}
			// [p], [q]가 다른 경우 발생
			// case 0 : 짝수 길이 문자열 중앙에서 서로 다른 문자인 경우 (짝수길이인 경우 "~~[p][q]~~"
			if(str.length()%2==0 && str.length()/2==q) return 1;		
			// case 1 : p 1칸 증가
			int tempP = p+1;
			int tempQ = q;
			boolean case1 = true;
			while(tempP<tempQ) {
				if(str.charAt(tempP) != str.charAt(tempQ)) {
					case1 = false;
					break;
				}
				tempP++;
				tempQ--;
			}
			if(case1) return 1;
			// case 2 : q 1칸 감소
			tempP = p;
			tempQ = q-1;
			boolean case2 = true;
			while(tempP<tempQ) {
				if(str.charAt(tempP) != str.charAt(tempQ)) {
					case2 = false;
					break;
				}
				tempP++;
				tempQ--;
			}
			if(case2) return 1;
			return 2;
		}
		return 0;
	}
}
