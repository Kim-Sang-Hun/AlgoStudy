package algo_group_study;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <문자열 폭발> G4
 * 링크
 * https://www.acmicpc.net/problem/9935
 * 요약
 * 입력받은 문자열에서 주어진 폭발 문자열이 더이상 존재하지 않을 떄까지 삭제
 * 풀이
 * 문자열 탐색. 폭발 문자열에 마지막 글자와 동일할 때 이전에 글자들을 탐색하여 폭발 문자열인지 확인
 */
public class boj_9935 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

	static String str, bomb; // 문자열, 폭발 문자열
	static int strLen, bombLen;
	static char lastCharOfBomb;
	static Stack<Character> stack = new Stack<>();
	static boolean have; 
    public static void main(String[] args) throws IOException {
		// 입력
		str = br.readLine();
		bomb = br.readLine();
		strLen = str.length();
		bombLen = bomb.length();
		lastCharOfBomb = bomb.charAt(bombLen-1);
		// 풀이
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		for(int i=0; i<strLen; i++) {
			stack.push(str.charAt(i));
			// 스택 크기가 폭탄보다 크고, 폭탄의 마지막 문자와 동일하면 폭탄 여부 검사
			if(stack.size() >= bombLen && stack.peek()==lastCharOfBomb) {	
				if(isBomb(stack.size()-1)) {
					for(int j=0; j<bombLen; j++) 
						stack.pop();
				}
			}
		}
		if(stack.size()==0) sb.append("FRULA");
		else {
			while(!stack.isEmpty()) sb.append(stack.pop());
			sb = sb.reverse();
		}
	}

	private static boolean isBomb(int top) {
		int cnt=bombLen-1;
		for(int i=top; i>top-bombLen; i--) { // top부터 역방향으로 폭탄과 비교
			if(bomb.charAt(cnt--) != stack.get(i))
				return false;
		}
		return true;
	}
} 
